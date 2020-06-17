package com.ait.library.Service.ServiceJBDC;


import com.ait.library.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ServiceComment {
    List<Comment> findAll();
    List<Comment> findAllByIdBook(int id);
    void save (Comment comment);
    Comment findById(int id);
    void update(Comment comment );
    void remove (int id);
}

