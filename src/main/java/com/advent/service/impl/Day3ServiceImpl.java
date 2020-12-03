package com.advent.service.impl;

import com.advent.data.Slope;
import com.advent.service.Day3Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day3ServiceImpl implements Day3Service {

    private static final Logger logger = LoggerFactory.getLogger(Day3Service.class);

    public int calculateTreesInMap(List<String> map, Slope slope) {

        int currentLine = 0;
        int currentColumn = 0;

        int rise = slope.getRise();
        int run = slope.getRun();

        int vertical = (map.size()-1) / rise;

        for (int i = 0; i < vertical; i++) {
            //expand the string to fit
            //TODO: instead of expanding, do some kind of mod bullshit to find the place in the surrent string
            map.add(currentLine + rise, expandStringPatternToLength(map.remove(currentLine + rise), currentColumn + run+1));
            map.add(currentLine + rise, updateCharacterAtPosition(map.remove(currentLine + rise), currentColumn + run));
            currentLine += rise;
            currentColumn += run;
        }

        logger.debug("The map is now: \n");
        map.forEach(line -> logger.debug(line + "\n"));

        return calculateTreesInMap(map);
    }

    private int calculateTreesInMap(List<String> map){
        return (int)map.stream().filter(line -> StringUtils.contains(line, "X")).count();
    }

    private String expandStringPatternToLength(String inputString, int length) {
        int repeat = length / inputString.length();
        return inputString.repeat(repeat + 1);
    }

    private String updateCharacterAtPosition(String inputString, int position) {
        char[] chArray = inputString.toCharArray();
        char ch = inputString.charAt(position);
        if (ch == '#') {
            chArray[position] = 'X';
        } else {
            chArray[position] = 'O';
        }
        return String.valueOf(chArray);
    }

}
