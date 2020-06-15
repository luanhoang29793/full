package com.ait.library.controller;

import com.ait.library.Service.ImplServiceJBDC.AuthorServiceImpl;
import com.ait.library.Service.ImplServiceJBDC.CategoryServiceImpl;
import com.ait.library.Service.ImplServiceJBDC.ProducerServiceimpl;
import com.ait.library.model.Author;
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
public class AuthorController {
    @Autowired
    private AuthorServiceImpl authorService;
    @GetMapping("/author")
    public List<Author> authorAll() {

        return authorService.findAll();
    }
    //    -------------------Retrieve All Customers--------------------------------------------------------
    @RequestMapping(value = "/author/", method = RequestMethod.GET)
    public ResponseEntity<List<Author>> listAllAuthor() {
        List<Author> authors = authorService.findAll();
        if (authors.isEmpty()) {
            return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }
    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> getCategory(@PathVariable("id") int id) {
        Author author = authorService.findById(id);
        if (author == null) {
            return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }
    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/author/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createCategory(@RequestBody Author author, UriComponentsBuilder ucBuilder) {

        authorService.save(author);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/author/create/{id}").buildAndExpand(author.getIdAuthor()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Author> updateCustomer(@PathVariable("id") int id, @RequestBody Author author) {

        Author author1 = authorService.findById(id);

        if (author1 == null) {
            return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
        }

        author1.setNameAuthor(author.getNameAuthor());
        author1.setNation(author.getNation());
        author1.setInformation(author.getInformation());
        authorService.update(author1);
        return new ResponseEntity<Author>(author1, HttpStatus.OK);
    }
    //------------------- Delete a Customer --------------------------------------------------------
    @RequestMapping(value = "/author/remove/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Author> removeCategory(@PathVariable("id") int id, @RequestBody Author author) {

        Author author1 = authorService.findById(id);

        if (author1 == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
        }


        authorService.remove(author1.getIdAuthor());
        return new ResponseEntity<Author>(author1, HttpStatus.OK);
    }
}
