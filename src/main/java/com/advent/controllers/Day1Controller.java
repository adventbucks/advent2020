package com.advent.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping(value = "/day1", method = RequestMethod.POST)
    public ExpenseReport calculateExpenseReport(@RequestBody List<String> initialValues) {

        long startTime = System.nanoTime();
        logger.info("We are in day1");
        List<Integer> values = initialValues.stream().mapToInt(s -> Integer.valueOf(s)).boxed().collect(Collectors.toList());
        Collections.sort(values);
        ExpenseReport report = new ExpenseReport(0, 0);
        for (int i = 0; i < values.size(); ) {
            int currentValue = values.remove(i).intValue();
            logger.debug("The value is {}: ", currentValue);
            Optional<Integer> otherValue = values.stream().parallel().filter(value -> value + currentValue == GOAL).findFirst();

            if (otherValue.isPresent()) {
                logger.info("The current value is; {} and the other value is: {}", currentValue, otherValue.get().intValue());
                report.setSingleValue(currentValue * otherValue.get().intValue());
            }
        }

        values = initialValues.stream().mapToInt(s -> Integer.valueOf(s)).boxed().collect(Collectors.toList());
        Collections.sort(values);
        for (int i = 0; i < values.size() - 2; ) {
            int firstValue = values.remove(i).intValue();
            int secondValue = values.remove(i).intValue();
            int multiSum = firstValue + secondValue;
            //logger.info("The fist value is: {} and the second value is: {} and the sum is: {}", firstValue, secondValue, multiSum);
            List<Integer> remainingValues = new ArrayList<>(values);

            if (multiSum < GOAL) {
                //logger.info("We are looking for a third value");
                Optional<Integer> otherValue = values.stream().parallel().filter(value -> value + multiSum == GOAL).findFirst();

                if (otherValue.isPresent()) {
                    logger.info("The current value is; {} and the first value is: {} and the last value is: {}", firstValue, secondValue, otherValue.get().intValue());
                    report.setMultiValue(firstValue * secondValue * otherValue.get().intValue());
                }
            }
        }

        long stopTime = System.nanoTime();
        logger.info("This shit took us {} nano seconds to calculate", stopTime - startTime);
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
