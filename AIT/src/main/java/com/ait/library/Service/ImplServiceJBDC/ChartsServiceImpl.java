package com.ait.library.Service.ImplServiceJBDC;

import com.ait.library.Service.ServiceJBDC.ServiceCharts;
import com.ait.library.model.Charts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ChartsServiceImpl implements ServiceCharts {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Charts> findAll() {
        String sql = "SELECT month(createDay) as month, COUNT ( month(createday)) AS amount FROM Book WHERE isDelete = 0 group by createDay";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Charts> result = new ArrayList<Charts>();
        for (Map<String, Object> row : rows) {
            Charts charts = new Charts();

            charts.setMonth((int) row.get("month"));
            charts.setCount((int) row.get("amount"));

            result.add(charts);
        }
        return result;
    }

    @Override
    public List countByMonth(int month) {
        String sql = "select  count(*) as count from Book where Month(createDay)=? and isDelete =0";
        List count = jdbcTemplate.queryForList(sql,month);
        return  count;
    }


}
