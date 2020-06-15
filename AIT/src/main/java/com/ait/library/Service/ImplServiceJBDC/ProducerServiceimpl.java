package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServicerProducer;
import com.ait.library.model.Category;
import com.ait.library.model.Producer;
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
public class ProducerServiceimpl implements ServicerProducer {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Producer> findAll() {
        String sql =" SELECT * FROM Producer WHERE isDelete=0";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Producer> result = new ArrayList<Producer>();
        for (Map<String, Object> row : rows) {
            Producer producer = new Producer();
            producer.setIdproducer((int) row.get("idProducer"));
            producer.setNameProducer((String) row.get("nameProducer"));
            result.add(producer);
        }
        return result;

    }

    @Override
    public void save(Producer producer) {
        String sql = "  INSERT INTO producer (nameProducer) VALUES " +
                "(?);";
        jdbcTemplate.update(sql,producer.getNameProducer());
    }

    @Override
    public Producer findById(int id) {
        String sql = " SELECT * FROM Producer WHERE isDelete =0 AND  idproducer = ?";
        RowMapper<Producer> rowMapper = new BeanPropertyRowMapper<Producer>(Producer.class);
        Producer producer = jdbcTemplate.queryForObject(sql, rowMapper, id);
        System.out.println("");
        return producer;
    }

    @Override
    public void update(Producer producer) {
        String sql = "UPDATE Producer SET nameproducer=? WHERE idProducer=?";
        jdbcTemplate.update(sql,new Object[]{producer.getNameProducer(),
              producer.getIdproducer()});
        System.out.println("test update");

    }

    @Override
    public void remove(int id) {
        String sql = "UPDATE Producer SET isDelete=1   WHERE idProducer= ?";
        jdbcTemplate.update(sql,id);
    }
}
