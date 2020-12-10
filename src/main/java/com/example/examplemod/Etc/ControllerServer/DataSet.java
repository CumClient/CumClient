package com.example.examplemod.Etc.ControllerServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSet {
    List<String> data = new ArrayList<>();
    public DataSet append(String key, String value) {
        data.add(key+":"+value);
        return this;
    }
    public String format() {
        return String.join("\n",data);
    }
    public static Map<String, String> parse(String input) throws InvalidFormatException {
        Map<String, String> end = new HashMap<>();
        for(String s : input.split("\n")) {
            try {
                String[] dataset = s.split(":");
                end.put(dataset[0], dataset[1]);
            } catch (Exception ex) {
                throw new InvalidFormatException();
            }
        }
        return end;
    }

}
class InvalidFormatException extends Exception {
    public InvalidFormatException() {
        super("Invalid format of dataset provided.");
    }
}
