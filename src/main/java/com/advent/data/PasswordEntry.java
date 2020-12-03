package com.advent.data;

import org.apache.commons.lang3.StringUtils;

public class PasswordEntry {

    private int min;
    private int max;
    private String requiredCharacter;
    private String password;

    public PasswordEntry(String line) {
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