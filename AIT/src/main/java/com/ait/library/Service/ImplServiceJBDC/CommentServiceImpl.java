package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServiceComment;
import com.ait.library.model.Comment;
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
public class CommentServiceImpl implements ServiceComment {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Comment> findAll() {
        String sql =" SELECT * FROM Comment WHERE isDelete=0 ORDER BY Comment.idComment DESC";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Comment> result = new ArrayList<Comment>();
        for (Map<String, Object> row : rows) {
            Comment comment = new Comment();
            comment.setIdComment((int) row.get("idComment"));
            comment.setName((String) row.get("nameComment"));
            comment.setContentComment((String)row.get("contentComment"));
            result.add(comment);
        }
        return result;

    }

    @Override
    public List<Comment> findAllByIdBook(int id) {
            String sql =" SELECT * FROM Comment WHERE isDelete=0 and idBook =?";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,id);
            List<Comment> result = new ArrayList<Comment>();
            for (Map<String, Object> row : rows) {
                Comment comment = new Comment();
                comment.setIdComment((int) row.get("idComment"));
                comment.setName((String) row.get("nameComment"));
                comment.setContentComment((String)row.get("contentComment"));
                result.add(comment);
            }
            return result;
    }

    @Override
    public void save(Comment comment) {
        String sql = "  INSERT INTO Comment (nameComment,contentComment) VALUES " +
                "(?,?)";
        System.out.println(comment.getName()+ comment.getName()+"loi o day");
        jdbcTemplate.update(sql,new Object[]{comment.getName(),comment.getContentComment()});
    }

    @Override
    public Comment findById(int id) {
        String sql = " SELECT * FROM Comment WHERE isDelete =0 AND  idComment = ?";
        RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
        Comment comment = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return comment;
    }

    @Override
    public void update(Comment comment) {
        String sql = "UPDATE Author SET name=?,contentComment =? WHERE idComment=?";
        jdbcTemplate.update(sql,new Object[]{comment.getName(),comment.getContentComment(),
                comment.getIdComment()});
    }

    @Override
    public void remove(int id) {
        String sql = "UPDATE Author SET isDelete=1   WHERE idComment= ?";
        jdbcTemplate.update(sql,id);
    }
}
