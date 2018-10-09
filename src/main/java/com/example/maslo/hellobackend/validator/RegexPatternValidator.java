package com.example.maslo.hellobackend.validator;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexPatternValidator {

    public String isValid(String checRegEx) {
        try {
            Pattern.compile(checRegEx);
        } catch (PatternSyntaxException exception) {
            return exception.getDescription();
        }
        return null;
    }
}
