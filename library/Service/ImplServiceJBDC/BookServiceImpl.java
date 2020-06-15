package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServiceBook;
import com.ait.library.model.Book;
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
public class BookServiceImpl implements ServiceBook {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Book> findAll() {
//        String sql =" Select Book.nameBook , Producer.nameProducer, Category.nameCategory , Author.nameAuthor " +
//                "from ((Book inner join Producer on Book.idProducer=Producer.idProducer)" +
//                "inner join Category on Book.idCategory = Category.idCategory)" +
//                "inner join Author on Book.idAuthor = Author.IdAuthorWHERE Book.isDelete=0";
        String sql = "Select Book.idBook,Book.nameBook, Producer.nameProducer , Category.nameCategory, Author.nameAuthor " +
                "             from ((Book inner join Producer on Book.idProducer=Producer.idProducer)" +
                "              inner join Category on Book.idCategory = Category.idCategory) " +
                "             inner join Author on Book.idAuthor = Author.IdAuthor WHERE Book.isDelete=0 ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Book> result = new ArrayList<Book>();
        for (Map<String, Object> row : rows) {
            Book book = new Book();
            book.setIdBook((int) row.get("idBook"));
            book.setNameBook((String) row.get("nameBook"));
            book.setNameAuthor((String) row.get("nameAuthor"));
            book.setNameCategory((String) row.get("nameCategory"));
            book.setNameproducer((String) row.get("nameProducer"));

            result.add(book);
        }
        return result;

    }

    @Override
    public void save(Book book) {
        String sql = "  INSERT INTO Book (nameBook, idCategory, idAuthor, idProducer) VALUES " +
                "(? ,? ,? , ?);";
        jdbcTemplate.update(sql,new Object[]{book.getNameBook(),book.getIdCategory(),book.getIdAuthor(), book.getIdProducer()});
    }

    @Override
    public Book findById(int id) {
        String sql = " Select Book.idBook,Book.nameBook, Producer.nameProducer , Category.nameCategory, Author.nameAuthor" +
                "      from ((Book inner join Producer on Book.idProducer=Producer.idProducer)" +
                "      inner join Category on Book.idCategory = Category.idCategory) " +
                "      inner join Author on Book.idAuthor = Author.IdAuthor WHERE Book.isDelete=0 AND  Book.idBook = ?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<Book>(Book.class);
        Book book = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return book;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE Category SET nameCategory=?, idCategory=?, idAuthor=?, iProducer=? WHERE idCategory=?";
        jdbcTemplate.update(sql,new Object[]{book.getNameBook(),book.getIdCategory(),book.getIdAuthor(),book.getIdProducer(),
                book.getIdBook()});
    }

    @Override
    public void remove(int id) {
        String sql = "UPDATE Book SET isDelete=1   WHERE idBook= ?";
        jdbcTemplate.update(sql,id);
    }
}
