package lfn.java;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Log {
    private static List<String> list = new ArrayList<>();
    private static String n = "";

    public static void push(String s) {
        n = s;
        list.add(s);
    }

    public static String latest() {
        return n;
    }

    public static StringBuffer all() {
        StringBuffer t = new StringBuffer();
        for (Object s:list) {
            t.append(s);
        }
        return t;
    }

    public static String save() {
        try {
            String path = "D:\\logs.txt";
            File f = new File(path);
            PrintStream p = new PrintStream(new FileOutputStream(f));
            for (Object s : list) {
                p.append(s.toString());
            }
            return "File saved to "+path;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static void clean() {
        list.clear();
    }
}
