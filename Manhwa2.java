package org.example.manhwadatascraper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

public class Manhwa2 {
    // my info! will pull from csv
    private int id;
    private double rating;
    private int progress;

    // general series info. pull from scraper thing
    public String title;
    private String description;
    private String[] genres;
    private String[] altTitles;
    private String image;
    private String origin;

    public Manhwa2(int id, double rating, int progress) {

        this.id = id;
        this.rating = rating;
        this.progress = progress;
    }

    //SCRAPE INFO using id
    public void ScrapeInfo() throws Exception {
        /*HttpClient.newHttpClient() — creates the client that sends requests, like opening a browser
        HttpRequest.newBuilder() — builds the request, you set the URL, headers, and body here
        client.send(request, HttpResponse.BodyHandlers.ofString()) — actually sends it and gets the response back as a String
        JsonParser.parseString(response.body()) — turns the raw JSON string into a navigable object
        json.getAsJsonObject("data").getAsJsonObject("Media") — drills into the JSON like json["data"]["Media"]
        media.get("averageScore").getAsInt() — pulls a specific field out as the type you want*/

        String query = """
            query ($id: Int) {
              Media (id: $id, type: MANGA) {
                id
                title { romaji english native }
                description
                genres
                coverImage { large }
                countryOfOrigin
                averageScore
                synonyms
              }
            }
        """;




    }

    public int getId() { return this.id; }
    public double getRating() { return this.rating; }
    public int getProgress() { return this.progress; }

    public String getTitle() { return this.title; }
    public String getDesc() { return this.description; }
    public String[] getGenres() { return this.genres; }
    public String[] getAltTitles() { return this.altTitles; }
    public String getImage() { return this.image; }
    public String getOrigin() { return this.origin; }

    public String toString() {
        return "id: " + id + "\nrating: " + rating + "\nprogress: " + progress;
    }



    public static void main(String[] args) throws Exception {
        Manhwa2 m = new Manhwa2(100470, 8.0, 60);
        m.ScrapeInfo();
        System.out.println(m.getTitle());
        System.out.println(m.getGenres()[0]);
    }
}
