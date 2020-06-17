package com.ait.library.controller;

import com.ait.library.Service.ImplServiceJBDC.CommentServiceImpl;
import com.ait.library.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    @GetMapping("/comment")
    public List<Comment> commentAll() {

        return commentService.findAll();
    }
    //    -------------------Retrieve All Customers--------------------------------------------------------
    @RequestMapping(value = "/comment/", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listComment() {
        List<Comment> comments = commentService.findAll();
        if (comments.isEmpty()) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }
    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> getComment(@PathVariable("id") int id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
    // comment by id Book

    @RequestMapping(value = "/commennt/commentByIdBook/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listCommentByIdbook(@PathVariable("id") int id) {
        List<Comment> comments = commentService.findAllByIdBook(id);
        if (comments.isEmpty()) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }
    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/comment/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createComment(@RequestBody Comment comment, UriComponentsBuilder ucBuilder) {

        commentService.save(comment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comment/create/{id}").buildAndExpand(comment.getIdComment()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Comment> updateComment(@PathVariable("id") int id, @RequestBody Comment comment) {

        Comment comment1 = commentService.findById(id);

        if (comment1 == null) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }

        comment1.setName(comment.getName());
        comment1.setContentComment(comment.getContentComment());
        commentService.update(comment1);
        return new ResponseEntity<Comment>(comment1, HttpStatus.OK);
    }
    //------------------- Delete a Customer --------------------------------------------------------
    @RequestMapping(value = "/comment/remove/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Comment> removeComment(@PathVariable("id") int id, @RequestBody Comment comment) {

        Comment comment1 = commentService.findById(id);

        if (comment1 == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }


        commentService.remove(comment1.getIdComment());
        return new ResponseEntity<Comment>(comment1, HttpStatus.OK);
    }
}
