package pl.edu.pjatk.MPR_Project.service;

import org.springframework.stereotype.Component;

@Component
public class StringUtilsService  {

    public String toUpperCase(String str) {
        return str.toUpperCase();
    }
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }
}
