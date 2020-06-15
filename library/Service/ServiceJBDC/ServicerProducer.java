package com.ait.library.Service.ServiceJBDC;

import com.ait.library.model.Category;
import com.ait.library.model.Producer;

import java.util.List;

public interface ServicerProducer {
    List<Producer> findAll();
    void save (Producer producer);
    Producer findById( int id);
    void update(Producer producer );
    void remove (int id);
}
