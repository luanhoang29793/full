package com.ait.library.Service.ServiceJBDC;

import com.ait.library.model.Charts;

import java.util.List;

public interface ServiceCharts {
    List<Charts> findAll();
    List countByMonth( int month);

}
