package lfn.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private static List<DataModel> list = new ArrayList<>();

    public static String add(DataModel data) {
        ObjectMapper mapper = new ObjectMapper();
        data.setId();
        data.setDate();
        list.add(data);
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
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    public static String find(String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (DataModel data : list) {
                if (data.getId().equals(id)) {
                    return mapper.writeValueAsString(data);
                }
            }
            return "{\"status\":\"NOT FOUND\"}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    public static String update(DataModel data, String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (DataModel d : list) {
                if (d.getId().equals(id)) {
                    d.setDate();
                    d.setUser(data.getUser());
                    d.setMessage(data.getMessage());
                    return mapper.writeValueAsString(d);
                }
            }
            return "{\"status\":\"NOT FOUND\"}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    public static String remove(String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (DataModel data : list) {
                if (data.getId().equals(id)) {
                    list.remove(data);
                    return mapper.writeValueAsString(data);
                }
            }
            return "{\"status\":\"NOT FOUND\"}";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }
}
