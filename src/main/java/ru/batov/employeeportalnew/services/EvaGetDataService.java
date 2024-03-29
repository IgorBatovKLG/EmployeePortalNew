package ru.batov.employeeportalnew.services;

import org.springframework.stereotype.Service;
import ru.batov.employeeportalnew.GetCookies;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class EvaGetDataService {

    public String getStringJson(String jsonIn){
        String jsonOut = "";
        GetCookies getCookies = new GetCookies();
        try {
            String json = jsonIn;

            HttpClient client = HttpClient.newHttpClient();

            HashMap<String, String> cookies = getCookies.getCookies();
           // HashMap<String, String> cookies = null; //todo загрузка куков в запрос
            ArrayList keyCookies = new ArrayList(cookies.keySet());
            String Cookie = keyCookies.get(0) +"="+ cookies.get(keyCookies.get(0)) +"; "+ keyCookies.get(1) +"="+ cookies.get(keyCookies.get(1));

            HttpRequest request  = HttpRequest.newBuilder().uri(new URI("http://dbs/eva/Search/SearchData"))
                    .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0")
                    .header("Content-Type","application/json; charset=utf-8")
                    .header("Cookie", Cookie)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            jsonOut = response.body().toString();


        } catch (URISyntaxException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonOut;
    }
}
