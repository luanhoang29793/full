package com.ait.library.Service.ServiceJBDC;

import com.ait.library.model.Book;
import com.ait.library.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Component
public interface ServiceBook {
    List<Book> findAll();
    void save (Book book);
    Book findById( int id);
    void update(Book book );
    void remove (int id);
    List<Book> BookByIDCategory(int id);
    List<Book> findAllDESC();
    List<Book> top6();


}
