package com.ait.library.Service.ServiceJBDC;

import com.ait.library.model.Book;
import com.ait.library.model.Category;

import java.util.List;

public interface ServiceBook {
    List<Book> findAll();
    void save (Book book);
    Book findById( int id);
    void update(Book book );
    void remove (int id);
}
