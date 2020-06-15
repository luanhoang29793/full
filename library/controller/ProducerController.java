package com.ait.library.controller;

import com.ait.library.Service.ImplServiceJBDC.CategoryServiceImpl;
import com.ait.library.Service.ImplServiceJBDC.ProducerServiceimpl;
import com.ait.library.model.Category;
import com.ait.library.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@RestController
public class ProducerController {
    @Autowired
    private ProducerServiceimpl producerService;
    @GetMapping("/producer")
    public List<Producer> categoriesAll() {

        return producerService.findAll();
    }
    //    -------------------Retrieve All Customers--------------------------------------------------------
    @RequestMapping(value = "/producer/", method = RequestMethod.GET)
    public ResponseEntity<List<Producer>> listAllProducer() {
        List<Producer> producers = producerService.findAll();
        if (producers.isEmpty()) {
            return new ResponseEntity<List<Producer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Producer>>(producers, HttpStatus.OK);
    }
    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/producer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producer> getProducer(@PathVariable("id") int id) {
        Producer producer = producerService.findById(id);
        if (producer == null) {
            return new ResponseEntity<Producer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producer>(producer, HttpStatus.OK);
    }
    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/producer/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createProducer(@RequestBody Producer producer, UriComponentsBuilder ucBuilder) {

        producerService.save(producer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/producer/create/{id}").buildAndExpand(producer.getIdproducer()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/producer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Producer> updateProducer(@PathVariable("id") int idProducer, @RequestBody Producer producer) {

        Producer producer1 = producerService.findById(idProducer);

        if (producer1 == null) {
            return new ResponseEntity<Producer>(HttpStatus.NOT_FOUND);
        }

        producer1.setNameProducer(producer.getNameProducer());
        producerService.update(producer1);
        return new ResponseEntity<Producer>(producer1, HttpStatus.OK);
    }
    //------------------- Delete a Customer --------------------------------------------------------
    @RequestMapping(value = "/producer/remove/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Producer> removeCategory(@PathVariable("id") int id, @RequestBody Producer producer) {

        Producer producer1 = producerService.findById(id);

        if (producer1 == null) {
            return new ResponseEntity<Producer>(HttpStatus.NOT_FOUND);
        }


        producerService.remove(producer1.getIdproducer());
        return new ResponseEntity<Producer>(producer1, HttpStatus.OK);
    }
}
