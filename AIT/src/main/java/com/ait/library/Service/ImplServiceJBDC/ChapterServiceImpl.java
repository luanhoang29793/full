package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServiceChapter;
import com.ait.library.model.Category;
import com.ait.library.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ChapterServiceImpl implements ServiceChapter {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Chapter> findAll() {
        String sql =" SELECT * FROM Chapter WHERE isDelete=0";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Chapter> result = new ArrayList<Chapter>();
        for (Map<String, Object> row : rows) {
            Chapter chapter = new Chapter();
            chapter.setIdchapter((int) row.get("idChapter"));
            chapter.setNameChapter((String) row.get("nameChapter"));
            chapter.setContentChapter((String) row.get("contentChapter"));
            chapter.setIdBook((int) row.get("idBook"));

            result.add(chapter);
        }
        return result;

    }

    @Override
    public List<Chapter> findChapterByBookID(int id) {
        String sql =" select chapter.idChapter,chapter.idBook, chapter.nameChapter,Author.nameAuthor,Book.nameBook from (chapter inner join Book on chapter.idBook = Book.idBook) inner join Author on Author.idAuthor = Book.idAuthor where chapter.idBook = ? And Chapter.isDelete=0";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,id);
        List<Chapter> result = new ArrayList<Chapter>();
        for (Map<String, Object> row : rows) {
            Chapter chapter = new Chapter();
            chapter.setIdchapter((int) row.get("idChapter"));
            chapter.setNameChapter((String) row.get("nameChapter"));
            chapter.setNameBook((String) row.get("nameBook"));
            chapter.setNameAuthor((String) row.get("nameAuthor"));
            chapter.setIdBook((int) row.get("idBook"));
            result.add(chapter);
        }
        return result;

    }

    @Override
    public void save(Chapter chapter) {
        String sql = "  INSERT INTO Chapter (nameChapter,contentChapter) VALUES " +
                "(?,?);";
        jdbcTemplate.update(sql,new Object[]{chapter.getNameChapter(),
                chapter.getContentChapter()});
    }

    @Override
    public void saveChapterForBook(int id,Chapter chapter) {

        String sql = "  INSERT INTO Chapter (nameChapter,contentChapter,idBook) VALUES " +
                "(?,?,?);";
        jdbcTemplate.update(sql,new Object[]{chapter.getNameChapter(),
                chapter.getContentChapter(),id});

    }

    @Override
    public Chapter findById(int id) {
        String sql = " select chapter.contentChapter, chapter.idChapter ,chapter.idBook, chapter.nameChapter,Author.nameAuthor,Category.nameCategory,Category.idCategory,Book.nameBook from ((chapter inner join Book on chapter.idBook = Book.idBook) inner join Author on Author.idAuthor = Book.idAuthor) inner join Category on Category.idCategory = Book.idCategory where chapter.idChapter = ? And Chapter.isDelete=0";
        RowMapper<Chapter> rowMapper = new BeanPropertyRowMapper<Chapter>(Chapter.class);
        Chapter chapter = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return chapter;
    }

    @Override
    public void update(Chapter chapter) {
        String sql = "UPDATE Chapter SET nameChapter=?, contentChapter=? WHERE idChapter=?";
        jdbcTemplate.update(sql,new Object[]{chapter.getNameChapter(),chapter.getContentChapter(),
                chapter.getIdChapter()});
    }

    @Override
    public void remove(int id) {
        String sql = "UPDATE Chapter SET isDelete=1   WHERE idChapter= ?";
        jdbcTemplate.update(sql,id);
    }

}
