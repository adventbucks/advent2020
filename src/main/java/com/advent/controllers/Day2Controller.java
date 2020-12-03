package com.advent.controllers;


import com.advent.data.PasswordEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@EnableSwagger2
public class Day2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Day2Controller.class);

    private static final String DAY = "day2";

    @RequestMapping(value = "/day2", method = RequestMethod.POST)
    public Map<String, String> day2(@RequestBody List<String> passwordValues) {

        Map<String, String> returnList = new LinkedHashMap<>();
        List<PasswordEntry> entries = passwordValues.stream().map(line -> new PasswordEntry(line)).collect(Collectors.toList());

        returnList.put("oldCompany", String.valueOf(entries.stream().filter(entry -> entry.isValidOldCompany()).count()));
        returnList.put("newCompany", String.valueOf(entries.stream().filter(entry -> entry.isValidCurrentCompany()).count()));

        return returnList;
    }
}
