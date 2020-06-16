package com.ait.library.controller;

import com.ait.library.Service.ImplServiceJBDC.AuthorServiceImpl;
import com.ait.library.Service.ImplServiceJBDC.ChartsServiceImpl;
import com.ait.library.model.Author;
import com.ait.library.model.Charts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ChartsController {
        @Autowired
        private ChartsServiceImpl chartsService;
        @GetMapping("/charts")
        public List<Charts> All() {

            return chartsService.findAll();
        }
    @RequestMapping(value = "/charts/", method = RequestMethod.GET)
    public ResponseEntity<List<Charts>> listAllAuthor() {
        List<Charts> charts = chartsService.findAll();
        if (charts.isEmpty()) {
            return new ResponseEntity<List<Charts>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Charts>>(charts, HttpStatus.OK);
    }
    @RequestMapping(value = "/charts/count/{month}", method = RequestMethod.GET)

    public ResponseEntity<List<Charts>> countByMonth(@PathVariable("month") Integer month) {
        List<Charts> charts = chartsService.countByMonth(month);
        if (charts.isEmpty()) {
            return new ResponseEntity<List<Charts>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Charts>>(charts, HttpStatus.OK);
    }

}
