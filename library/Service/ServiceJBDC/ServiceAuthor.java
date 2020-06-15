package com.ait.library.Service.ServiceJBDC;

import com.ait.library.model.Author;
import com.ait.library.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ServiceAuthor {
    List<Author> findAll();
    void save (Author author);
    Author findById( int id);
    void update(Author author );
    void remove (int id);
}
