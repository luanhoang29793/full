package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServiceCategory;
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
public class CategoryServiceImpl implements ServiceCategory {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Category> findAll() {
        String sql =" SELECT * FROM Category WHERE isDelete=0";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Category> result = new ArrayList<Category>();
        for (Map<String, Object> row : rows) {
            Category category = new Category();
            category.setIdCategory((int) row.get("idCategory"));
            category.setNameCategory((String) row.get("nameCategory"));
            result.add(category);
        }
        return result;

    }

    @Override
    public void save(Category category) {
        String sql = "  INSERT INTO Category (nameCategory) VALUES " +
                "(?);";
        jdbcTemplate.update(sql,category.getNameCategory());
    }

    @Override
    public Category findById(int id) {
        String sql = " SELECT * FROM Category WHERE isDelete =0 AND  idCategory = ?";
        RowMapper<Category> rowMapper = new BeanPropertyRowMapper<Category>(Category.class);
        Category category = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return category;
    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE Category SET nameCategory=? WHERE idCategory=?";
        jdbcTemplate.update(sql,new Object[]{category.getNameCategory(),
                category.getIdCategory()});
    }

    @Override
    public void remove(int id) {
        String sql = "UPDATE Category SET isDelete=1   WHERE idCategory= ?";
        jdbcTemplate.update(sql,id);
    }
}
