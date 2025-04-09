package co.simplon.devbookapi.controllers.errors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomErrors {

    private final Map<String, Collection<String>> fieldsErrors = new HashMap<>();
    private final Collection<String> globalErrors = new ArrayList<>();

    public CustomErrors() {
    }

    public void addFieldError(String fieldName, String message) {
        Collection<String> messages = fieldsErrors.computeIfAbsent(fieldName, k -> new ArrayList<>());
        messages.add(message);
    }

    public void addGlobalError(String code) {
        globalErrors.add(code);
    }

    public Map<String, Collection<String>> getFieldsErrors() {
        return fieldsErrors;
    }

    public Collection<String> getGlobalErrors() {
        return globalErrors;
    }

    @Override
    public String toString() {
        return String.format("{fieldErrors=%s, globalErrors=%s}", fieldsErrors, globalErrors);
    }
}
