package lfn.java;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Date;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Connection {
    private static int status;
    private String host;
    private int port;
    private Socket socket;
    private StringBuffer path = new StringBuffer();

    public Connection(String url) throws IOException {
        StringTokenizer s = new StringTokenizer(url, "/:");
        s.nextToken();
        this.host = s.nextToken();
        this.port = Integer.parseInt(s.nextToken());
        this.socket = new Socket(host, port);
        while (s.hasMoreTokens()) {
            String t = s.nextToken();
            this.path.append("/").append(t);
        }
    }

    public static int getStatus() {
        // System.out.println(status);
        return status;
    }

    // POST PUT
    public static String send(Object method, String url, String headers, String body) throws IOException {
        StringBuffer newHeaders = format(headers);
        StringBuffer newBody = format(body);

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Log.push("--> Request\n" + d.format(date) + "\n" + method
                + " " + url + "\n" + newHeaders + newBody + "\n");

        Connection link = new Connection(url);
        OutputStreamWriter streamWriter = new OutputStreamWriter((link.socket.getOutputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
        String tt;
        if (method.toString().equals("POST")) {
            tt = "POST " + link.path + " HTTP/1.1\r\n"
                    + "Host: " + link.host + "\r\n"
                    + "Content-Length: " + (newBody.length() + 1) + "\r\n"
                    + newHeaders + "\r\n";
        } else {
            tt = "PUT " + link.path + " HTTP/1.1\r\n"
                    + "Host: " + link.host + "\r\n"
                    + "Content-Length: " + (newBody.length() + 1) + "\r\n"
                    + newHeaders + "\r\n";
        }
        bufferedWriter.write(tt);
        bufferedWriter.write("\r\n");
        bufferedWriter.write(newBody.toString());
        bufferedWriter.write("\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();

        BufferedInputStream inputStream = new BufferedInputStream(link.socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String line;
        StringBuffer res = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("HTTP")) {
                StringTokenizer s = new StringTokenizer(line, " ");
                s.nextToken();
                status = Integer.parseInt(s.nextToken());
            }
            if (line.startsWith("Content-Length")) {
                StringTokenizer s = new StringTokenizer(line, " ");
                s.nextToken();
                int resLength = 0;
                resLength = Integer.parseInt(s.nextToken());
                res.append(line).append("\n");
                line = bufferedReader.readLine();
                res.append(line).append("\n");
                char[] buf = new char[resLength + 2];
                bufferedReader.read(buf, 0, resLength + 2);
                String f = formatResult(buf);
                res.append(f).append("\n");
                break;
            } else {
                res.append(line).append("\n");
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
        link.socket.close();

        Log.push("<-- Response\n" + res.toString() + "\n");
        return res.toString();

    }

    // GET DELETE
    public static String send(Object method, String url) throws IOException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String t = "--> Request\n" + d.format(date) + "\n" + method
                + " " + url + "\n\n";
        Log.push(t);

        Connection link = new Connection(url);
        OutputStreamWriter streamWriter = new OutputStreamWriter((link.socket.getOutputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
        String tt;
        if (method.toString().equals("GET")) {
            tt = "GET " + link.path + " HTTP/1.1\r\n"
                    + "Host: " + link.host + "\r\n";
        } else {
            tt = "DELETE " + link.path + " HTTP/1.1\r\n"
                    + "Host: " + link.host + "\r\n";
        }
        bufferedWriter.write(tt);
        bufferedWriter.write("\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();

        BufferedInputStream inputStream = new BufferedInputStream(link.socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String line;
        StringBuffer res = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("HTTP")) {
                StringTokenizer s = new StringTokenizer(line, " ");
                s.nextToken();
                status = Integer.parseInt(s.nextToken());
            }
            if (line.startsWith("Content-Length")) {
                StringTokenizer s = new StringTokenizer(line, " ");
                s.nextToken();
                int resLength = 0;
                resLength = Integer.parseInt(s.nextToken());
                res.append(line).append("\n");
                line = bufferedReader.readLine();
                res.append(line).append("\n");
                char[] buf = new char[resLength + 2];
                bufferedReader.read(buf, 0, resLength + 2);
                String f = formatResult(buf);
                res.append(f).append("\n");
                break;
            } else {
                res.append(line).append("\n");
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
        link.socket.close();

        Log.push("<-- Response\n" + res.toString() + "\n");
        return res.toString();
    }

    private static String formatResult(char[] buf) {
        String s = String.valueOf(buf);
        s = s.replace("{", "{\n    ");
        s = s.replace("}", "\n}");
        s = s.replace("\",", "\",\n    ");
        return s;
    }

    public static StringBuffer format(String ss) {
        StringBuffer result = new StringBuffer();
        StringTokenizer s = new StringTokenizer(ss, "\n");
        while (s.hasMoreTokens()) {
            String t = s.nextToken();
            if (!t.startsWith("// ")) {
                result.append(t).append("\n");
            }
        }
        return result;
    }
}
