package io.github.untildawn.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpMenuRegex {
    password("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@%$#&*()_]).{8,}$");
    private String regex;
    SignUpMenuRegex(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input){
        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()){
            return matcher;
        }
        return null;
    }
}
