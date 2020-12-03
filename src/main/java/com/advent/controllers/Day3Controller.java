package com.advent.controllers;

import com.advent.data.Slope;
import com.advent.service.Day3Service;
import com.advent.utility.DatasetGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Component
public class Day3Controller {

    private static final Logger logger = LoggerFactory.getLogger(Day3Controller.class);

    private static final String DAY = "day3";

    @Autowired
    @Qualifier("datasetGetter")
    private DatasetGetter datasetGetter;

    @Autowired
    private Day3Service day3Service;

    @RequestMapping(value = "/day3", method = RequestMethod.POST)
    public List<String> day3(@RequestBody List<String> passwordValues) {

        List<String> myList = datasetGetter.getStringListDataset(DAY);

        return new ArrayList<String>();

    }


    @RequestMapping(value = "/day3", method = RequestMethod.GET)
    public List<String> day3FromFile() {
        Slope slope = new Slope(1, 3);

        List<String> map = datasetGetter.getStringListDataset(DAY);

        return getMultipleFromSlopes(map, Arrays.asList(slope));
    }

    @RequestMapping(value = "/day3multi", method = RequestMethod.GET)
    public List<String> day3FromFileMultipleSlopes() {

        List<Slope> slopes = new ArrayList<>();

        slopes.add(new Slope(1, 1));
        slopes.add(new Slope(1, 3));
        slopes.add(new Slope(1, 5));
        slopes.add(new Slope(1, 7));
        slopes.add(new Slope(2, 1));

        List<String> map = datasetGetter.getStringListDataset(DAY);

        return getMultipleFromSlopes(map, slopes);

    }

    private List<String> getMultipleFromSlopes(List<String> map, List<Slope> slopes) {
        long trees = 1;
        for (Slope slope : slopes) {
            int t = day3Service.calculateTreesInMap(new ArrayList<>(map), slope);
            logger.info("The number of trees on slope rise: {} run: {} is {}", slope.getRise(), slope.getRun(), t);
            trees = trees * t;
            logger.info("The number of trees is: {}", day3Service.calculateTreesInMap(new ArrayList<>(map), slope));
        }

        return Arrays.asList(String.valueOf(trees));
    }

}
