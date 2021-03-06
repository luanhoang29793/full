package com.ait.library.controller;

import com.ait.library.Service.ImplServiceJBDC.AuthorServiceImpl;
import com.ait.library.Service.ImplServiceJBDC.BookServiceImpl;
import com.ait.library.Service.ImplServiceJBDC.ProducerServiceimpl;
import com.ait.library.model.Author;
import com.ait.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@RestController
public class BookController {
    @Autowired
    private BookServiceImpl bookService;
    @GetMapping("/book")
    public List<Book> bookAll() {

        return bookService.findAll();
    }
    //    -------------------Retrieve All Customers--------------------------------------------------------
    @RequestMapping(value = "/book/", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAllBook() {
        List<Book> books = bookService.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }
    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }
    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/book/create/", method = RequestMethod.POST)
    public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {

        bookService.save(book);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/book/create/{id}").buildAndExpand(book.getIdBook()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {

        Book book1 = bookService.findById(id);

        if (book1 == null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        book1.setNameBook(book.getNameBook());
        book1.setIdAuthor(book.getIdAuthor());
        book1.setIdCategory(book.getIdCategory());
        book1.setIdProducer(book.getIdProducer());

        bookService.update(book1);
        return new ResponseEntity<Book>(book1, HttpStatus.OK);
    }
    //------------------- Delete a Customer --------------------------------------------------------
    @RequestMapping(value = "/book/remove/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> removeCategory(@PathVariable("id") int id, @RequestBody Book book) {

        Book book1 = bookService.findById(id);

        if (book1 == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }


        bookService.remove(book1.getIdBook());
        return new ResponseEntity<Book>(book1, HttpStatus.OK);
    }
}
