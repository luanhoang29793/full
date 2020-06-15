package com.ait.library.controller;

import com.ait.library.Service.ImplServiceJBDC.CategoryServiceImpl;
import com.ait.library.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @GetMapping("/category")
    public List<Category> categoriesAll() {

        return categoryService.findAll();
    }
    //    -------------------Retrieve All Customers--------------------------------------------------------
    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> listAllCategory() {
        List<Category> category = categoryService.findAll();
        if (category.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
    }
    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable("id") int idCategory) {
        Category category = categoryService.findById(idCategory);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {

        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/category/create/{id}").buildAndExpand(category.getIdCategory()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCustomer(@PathVariable("id") int id, @RequestBody Category category) {

        Category category1 = categoryService.findById(id);

        if (category1 == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        category1.setNameCategory(category.getNameCategory());
        category1.setInformation(category.getInformation());

        categoryService.update(category1);
        return new ResponseEntity<Category>(category1, HttpStatus.OK);
    }
    //------------------- Delete a Customer --------------------------------------------------------
    @RequestMapping(value = "/category/remove/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> removeCategory(@PathVariable("id") int id, @RequestBody Category category) {
        System.out.println("Updating Customer " + id);

        Category category1 = categoryService.findById(id);

        if (category1 == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }


        categoryService.remove(category1.getIdCategory());
        return new ResponseEntity<Category>(category1, HttpStatus.OK);
    }
}
