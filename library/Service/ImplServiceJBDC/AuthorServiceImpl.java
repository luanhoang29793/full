package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServiceAuthor;
import com.ait.library.model.Author;
import com.ait.library.model.Category;
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
public class AuthorServiceImpl implements ServiceAuthor {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Author> findAll() {
        String sql =" SELECT * FROM Author WHERE isDelete=0";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Author> result = new ArrayList<Author>();
        for (Map<String, Object> row : rows) {
            Author author = new Author();
            author.setIdAuthor((int) row.get("idAuthor"));
            author.setNameAuthor((String) row.get("nameCategory"));
            result.add(author);
        }
        return result;

    }

    @Override
    public void save(Author author) {
        String sql = "  INSERT INTO Author (nameAuthor) VALUES " +
                "(?);";
        jdbcTemplate.update(sql,author.getNameAuthor());
    }

    @Override
    public Author findById(int id) {
        String sql = " SELECT * FROM Author WHERE isDelete =0 AND  idAuthor = ?";
        RowMapper<Author> rowMapper = new BeanPropertyRowMapper<Author>(Author.class);
        Author author = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return author;
    }

    @Override
    public void update(Author author) {
        String sql = "UPDATE Author SET nameAuthor=? WHERE idAuthor=?";
        jdbcTemplate.update(sql,new Object[]{author.getNameAuthor(),
                author.getIdAuthor()});
    }

    @Override
    public void remove(int id) {
        String sql = "UPDATE Author SET isDelete=1   WHERE idAuthor= ?";
        jdbcTemplate.update(sql,id);
    }
}
