package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServiceBook;
import com.ait.library.model.Book;
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
        String sql = "Select Book.idBook,Book.nameImage,Book.nameBook,Book.summaryBook, Category.nameCategory, Author.nameAuthor,Book.idCategory,Book.idAuthor " +
                "             from ((Book inner join Category on Book.idCategory = Category.idCategory) inner join Author on Book.idAuthor = Author.IdAuthor) WHERE Book.isDelete=0 ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Book> result = new ArrayList<Book>();
        for (Map<String, Object> row : rows) {
            Book book = new Book();
            book.setIdBook((int) row.get("idBook"));
            book.setNameBook((String) row.get("nameBook"));
            book.setNameAuthor((String) row.get("nameAuthor"));
            book.setNameImage((String) row.get("nameImage"));
            book.setNameCategory((String) row.get("nameCategory"));
            book.setSummaryBook((String) row.get("summaryBook"));
            book.setIdCategory((int) row.get("idCategory"));
            book.setIdAuthor((int) row.get("idAuthor"));


            result.add(book);
        }
        return result;

    }

    @Override
    public void save(Book book) {
        String sql = "  INSERT INTO Book (nameBook, idCategory, idAuthor, summaryBook,nameImage, idProducer) VALUES (? ,? ,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{book.getNameBook(),book.getIdCategory(),book.getIdAuthor(), book.getSummaryBook(),book.getNameImage(), 1});
    }

    @Override
    public Book findById(int id) {
        String sql = "Select Book.idBook,Book.nameImage,Book.nameBook,Book.summaryBook, Category.nameCategory, Author.nameAuthor,Book.idCategory,Book.idAuthor from ((Book inner join Category on Book.idCategory = Category.idCategory) inner join Author on Book.idAuthor = Author.IdAuthor) WHERE Book.isDelete=0 AND  Book.idBook = ?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<Book>(Book.class);
        Book book = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return book;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE Book SET nameBook=?, idCategory=?, idAuthor=?, summaryBook=?, idProducer=? ,nameImage =? WHERE idBook=?";
        jdbcTemplate.update(sql,new Object[]{book.getNameBook(),book.getIdCategory(),book.getIdAuthor(),book.getSummaryBook(),1,book.getNameImage(),
                book.getIdBook()});
    }

    @Override
    public void remove(int id) {
        String sql = "UPDATE Book SET isDelete=1   WHERE idBook= ?";
        jdbcTemplate.update(sql,id);
    }
    @Override
    public List<Book> findAllDESC() {
        String sql = "Select TOP 13 Book.idBook,Book.nameBook,Book.nameImage, Category.nameCategory, Author.nameAuthor, Book.summaryBook" +
                "                    from ((Category inner join Book on Book.idCategory = Category.idCategory) " +
                "                   inner join Author on Book.idAuthor = Author.IdAuthor) WHERE Book.isDelete=0 ORDER BY Book.idBook DESC";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Book> result = new ArrayList<Book>();
        for (Map<String, Object> row : rows) {
            Book book = new Book();
            book.setIdBook((int) row.get("idBook"));
            book.setNameBook((String) row.get("nameBook"));
            book.setNameImage((String) row.get("nameImage"));
            book.setNameAuthor((String) row.get("nameAuthor"));
            book.setNameCategory((String) row.get("nameCategory"));
            book.setNameProducer((String) row.get("nameProducer"));
            book.setSummaryBook((String) row.get("summaryBook"));
            result.add(book);
        }
        return result;
    }
    @Override
    public List<Book> top6() {
        String sql = "Select TOP 10 Book.idBook,Book.nameImage,Book.nameBook, Category.nameCategory, Author.nameAuthor, Book.summaryBook" +
                "                    from ((Category inner join Book on Book.idCategory = Category.idCategory) " +
                "                   inner join Author on Book.idAuthor = Author.IdAuthor) WHERE Book.isDelete=0 ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Book> result = new ArrayList<Book>();
        for (Map<String, Object> row : rows) {
            Book book = new Book();
            book.setIdBook((int) row.get("idBook"));
            book.setNameBook((String) row.get("nameBook"));
            book.setNameAuthor((String) row.get("nameAuthor"));
            book.setNameCategory((String) row.get("nameCategory"));
            book.setNameProducer((String) row.get("nameProducer"));
            book.setSummaryBook((String) row.get("summaryBook"));
            book.setNameImage((String) row.get("nameImage"));

            result.add(book);
        }
        return result;
    }
    @Override
    public List<Book> BookByIDCategory(int id) {
        String sql = "select book.idBook, Book.nameImage, Book.nameImage,category.IdCategory,book.namebook,Book.summaryBook," +
                " author.nameAuthor,author.idAuthor, Category.nameCategory from ((book inner join Author on book.idAuthor = Author.IdAuthor)" +
                "inner join Category on Book.idCategory = Category.idCategory) where book.idCategory= ? and book.isDelete =0 ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,id);
        List<Book> result = new ArrayList<Book>();
        for (Map<String, Object> row : rows) {
            Book book = new Book();
            book.setIdBook((int) row.get("idBook"));
            book.setIdCategory((int) row.get("idCategory"));
            book.setIdAuthor((int) row.get("idAuthor"));
            book.setNameBook((String) row.get("nameBook"));
            book.setNameImage((String) row.get("nameImage"));
            book.setNameAuthor((String) row.get("nameAuthor"));
            book.setNameCategory((String) row.get("nameCategory"));
            book.setNameProducer((String) row.get("nameProducer"));
            book.setSummaryBook((String) row.get("summaryBook"));
            result.add(book);
        }
        return result;
    }
}
