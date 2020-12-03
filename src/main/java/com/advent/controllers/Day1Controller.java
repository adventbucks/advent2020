package com.advent.controllers;

import com.advent.service.Day1Service;
import com.advent.utility.DatasetGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@EnableSwagger2
public class Day1Controller {

    private static final Logger logger = LoggerFactory.getLogger(Day1Controller.class);

    private static final int GOAL = 2020;

    private static final String DAY = "day1";

    @Autowired
    @Qualifier("datasetGetter")
    private DatasetGetter datasetGetter;

    @Autowired
    private Day1Service day1Service;

    @RequestMapping(value = "/day1", method = RequestMethod.POST)
    public ExpenseReport calculateExpenseReport(@RequestBody List<String> initialValues) {

        long startTime = System.nanoTime();
        logger.info("We are in {}}", DAY);

        ExpenseReport report = new ExpenseReport(0, 0);

        report.setSingleValue(day1Service.getExpenseReportDoubleSum(initialValues, GOAL));

//        values = initialValues.stream().mapToInt(s -> Integer.valueOf(s)).boxed().collect(Collectors.toList());
//        Collections.sort(values);
//        for (int i = 0; i < values.size() - 2; ) {
//            int firstValue = values.remove(i).intValue();
//            int secondValue = values.remove(i).intValue();
//            int multiSum = firstValue + secondValue;
//            //logger.info("The fist value is: {} and the second value is: {} and the sum is: {}", firstValue, secondValue, multiSum);
//            List<Integer> remainingValues = new ArrayList<>(values);
//
//            if (multiSum < GOAL) {
//                //logger.info("We are looking for a third value");
//                Optional<Integer> otherValue = values.stream().parallel().filter(value -> value + multiSum == GOAL).findFirst();
//
//                if (otherValue.isPresent()) {
//                    logger.info("The current value is; {} and the first value is: {} and the last value is: {}", firstValue, secondValue, otherValue.get().intValue());
//                    report.setMultiValue(firstValue * secondValue * otherValue.get().intValue());
//                }
//            }
//        }

        long stopTime = System.nanoTime();
        logger.info("This shit took us {} nano seconds to calculate", stopTime - startTime);
        return report;
    }

    @RequestMapping(value = "/day1", method = RequestMethod.GET)
    public ExpenseReport calculateExpenseReportFromFile() {

        ExpenseReport report = new ExpenseReport(0,0);
        List<String> values = datasetGetter.getStringListDataset(DAY);
        report.setSingleValue(day1Service.getExpenseReportDoubleSum(values, GOAL));

        return report;
    }


        private class ExpenseReport {

        @JsonProperty
        int singleValue;
        @JsonProperty
        int multiValue;

        public ExpenseReport(int singleValue, int multiValue) {
            this.singleValue = singleValue;
            this.multiValue = multiValue;
        }

        public int getSingleValue() {
            return singleValue;
        }

        public void setSingleValue(int singleValue) {
            this.singleValue = singleValue;
        }

        public int getMultiValue() {
            return multiValue;
        }

        public void setMultiValue(int multiValue) {
            this.multiValue = multiValue;
        }
    }

}
