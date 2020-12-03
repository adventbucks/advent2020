package com.advent.service.impl;

import com.advent.controllers.Day1Controller;
import com.advent.service.Day1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Day1ServiceImpl implements Day1Service {

    private static final Logger logger = LoggerFactory.getLogger(Day1ServiceImpl.class);

    public int getExpenseReportDoubleSum(List<String> initialValues, int goal) {

        List<Integer> values = initialValues.stream().mapToInt(s -> Integer.valueOf(s)).boxed().collect(Collectors.toList());
        Collections.sort(values);

        for (int i = 0; i < values.size(); ) {
            int currentValue = values.remove(i).intValue();
            logger.debug("The value is {}: ", currentValue);
            Optional<Integer> otherValue = values.stream().parallel().filter(value -> value + currentValue == goal).findFirst();

            if (otherValue.isPresent()) {
                logger.info("The current value is; {} and the other value is: {}", currentValue, otherValue.get().intValue());
                return currentValue * otherValue.get().intValue();
            }
        }
        return 0;
    }

    public int getExpenseReportTripleSum(List<String> initialValues, int goal) {

        List<Integer> values = initialValues.stream().mapToInt(s -> Integer.valueOf(s)).boxed().collect(Collectors.toList());
        Collections.sort(values);

        values = initialValues.stream().mapToInt(s -> Integer.valueOf(s)).boxed().collect(Collectors.toList());
        Collections.sort(values);
        for (int i = 0; i < values.size() - 2; ) {
            int firstValue = values.remove(i).intValue();
            int secondValue = values.remove(i).intValue();
            int multiSum = firstValue + secondValue;
            //logger.info("The fist value is: {} and the second value is: {} and the sum is: {}", firstValue, secondValue, multiSum);
            List<Integer> remainingValues = new ArrayList<>(values);

            if (multiSum < goal) {
                //logger.info("We are looking for a third value");
                Optional<Integer> otherValue = values.stream().parallel().filter(value -> value + multiSum == goal).findFirst();

                if (otherValue.isPresent()) {
                    logger.info("The current value is; {} and the first value is: {} and the last value is: {}", firstValue, secondValue, otherValue.get().intValue());
                    return firstValue * secondValue * otherValue.get().intValue();
                }
            }
        }


        return 0;
    }


}
