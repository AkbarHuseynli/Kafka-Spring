package com.example.model.email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;
@Service
public class EmailValidator implements Predicate<String> {
    private final Pattern regexPattern =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
    @Override
    public boolean test(String email) {
        return regexPattern.matcher(email).find();
    }
}
