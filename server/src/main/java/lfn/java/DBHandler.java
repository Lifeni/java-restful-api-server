package lfn.java;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class DBHandler {
    private static MongoCollection<Document> collection;

    public static void run() {
        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("java");
            collection = database.getCollection("test");
            System.out.println("Connect to Datebase.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void add(DataModel data) {
        run();
        try {
            Document document = new Document("id", data.getId())
                    .append("date", data.getDate())
                    .append("user", data.getUser())
                    .append("message", data.getMessage());
            // System.out.println(document);
            // System.out.println(collection);
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Document> find() {
        run();
        List<Document> documents = new ArrayList<Document>();
        try {
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document document = mongoCursor.next();
                document.remove("_id");
                documents.add(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    public static Document find(String id) {
        run();
        try {
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document document = mongoCursor.next();
                if (document.get("id").equals(id)) {
                    document.remove("_id");
                    return document;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document update(String id, DataModel temp) {
        run();
        try {
            collection.updateOne(Filters.eq("id", id),
                        Updates.combine(Updates.set("id", id),
                            Updates.set("date", temp.getDate()),
                            Updates.set("user", temp.getUser()),
                            Updates.set("message", temp.getMessage())));
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document document = mongoCursor.next();
                if (document.get("id").equals(id)) {
                    document.remove("_id");
                    return document;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document remove(String id) {
        run();
        try {
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document document = mongoCursor.next();
                if (document.get("id").equals(id)) {
                    collection.deleteOne(Filters.eq("id", id));
                    document.remove("_id");
                    return document;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}