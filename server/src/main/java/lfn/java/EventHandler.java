package lfn.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    public static String add(DataModel data) {
        ObjectMapper mapper = new ObjectMapper();
        data.setId();
        data.setDate();
        DBHandler.add(data);
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    public static String find() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(DBHandler.find());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    public static String find(String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Document document = DBHandler.find(id);
            if (document == null) {
                return "{\"status\":\"NOT FOUND\"}";
            }
            return mapper.writeValueAsString(document);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    public static String update(DataModel data, String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            DataModel temp = data;
            temp.setDate();
            Document document = DBHandler.update(id, temp);
            if (document == null) {
                return "{\"status\":\"NOT FOUND\"}";
            }
            return mapper.writeValueAsString(document);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    public static String remove(String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Document document = DBHandler.remove(id);
            if (document == null) {
                return "{\"status\":\"NOT FOUND\"}";
            }
            return mapper.writeValueAsString(document);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }
}
