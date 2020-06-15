package com.ait.library.Service.ServiceJBDC;

import com.ait.library.model.Category;
import com.ait.library.model.Chapter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ServiceChapter {
    List<Chapter> findAll();
    List<Chapter> findChapterByBookID(int id);
    void save (Chapter chapter);
    void saveChapterForBook(  int id,Chapter chapter);
    Chapter findById( int id);
    void update(Chapter chapter );
    void remove (int id);
}

