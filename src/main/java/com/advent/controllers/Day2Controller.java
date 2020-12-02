package com.advent.controllers;


import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
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

    @RequestMapping(value = "/day2", method = RequestMethod.POST)
    public Map<String, String> day2(@RequestBody List<String> passwordValues) {

        Map<String, String> returnList = new LinkedHashMap<>();
        List<PasswordEntry> entries = passwordValues.stream().map(line -> new PasswordEntry(line)).collect(Collectors.toList());

        returnList.put("oldCompany", String.valueOf(entries.stream().filter(entry -> entry.isValidOldCompany()).count()));
        returnList.put("newCompany", String.valueOf(entries.stream().filter(entry -> entry.isValidCurrentCompany()).count()));

        return returnList;
    }


    class PasswordEntry {

        private int min;
        private int max;
        private String requiredCharacter;
        private String password;

        private PasswordEntry(String line) {
            String[] parts = StringUtils.split(line, " ");
            min = Integer.valueOf(StringUtils.split(parts[0], "-")[0]).intValue();
            max = Integer.valueOf(StringUtils.split(parts[0], "-")[1]).intValue();
            requiredCharacter = StringUtils.remove(parts[1], ":");
            password = parts[2];
        }


        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getRequiredCharacter() {
            return requiredCharacter;
        }

        public void setRequiredCharacter(String requiredCharacter) {
            this.requiredCharacter = requiredCharacter;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isValidOldCompany() {
            int occurrences = StringUtils.countMatches(password, requiredCharacter);
            if (min <= occurrences && occurrences <= max) {
                return true;
            }
            return false;
        }

        public boolean isValidCurrentCompany() {
            if (StringUtils.equals(requiredCharacter, String.valueOf(password.charAt(min - 1))) && !StringUtils.equals(requiredCharacter, String.valueOf(password.charAt(max - 1)))) {
//                logger.info("The line is: {}-{} {}: {}", min,max,requiredCharacter, password);
                return true;
            }
            if (!StringUtils.equals(requiredCharacter, String.valueOf(password.charAt(min - 1))) && StringUtils.equals(requiredCharacter, String.valueOf(password.charAt(max - 1)))) {
                return true;
            }
            return false;
        }

    }


}
