package com.ait.library.Service.ServiceJBDC;

import com.ait.library.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ServiceCategory {
    List<Category> findAll();
    void save (Category category);
    Category findById( int id);
    void update(Category category );
    void remove (int id);
}
