package org.example.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestHelpers {

    public static Page getPage(int pageNumber) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.)SerializationFeature
        StringBuffer response = new StringBuffer();
        Page page = new Page();
        String url = String.format("https://jsonmock.hackerrank.com/api/articles?page=%s", pageNumber);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("GET request not working");
        }
        page = mapper.readValue(response.toString(), Page.class);
        return page;
    }

    public static String getPageRaw(int pageNumber) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.)SerializationFeature
        StringBuffer response = new StringBuffer();
        Page page = new Page();
        String url = String.format("https://jsonmock.hackerrank.com/api/articles?page=%s", pageNumber);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


        } else {
            System.out.println("GET request not working");
        }
        return response.toString();

    }
}
