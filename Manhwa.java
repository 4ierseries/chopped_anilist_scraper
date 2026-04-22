package org.example.manhwadatascraper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.codeborne.selenide.WebDriverRunner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

public class Manhwa {
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

    public Manhwa(int id, double rating, int progress) {

        this.id = id;
        this.rating = rating;
        this.progress = progress;
    }

    //SCRAPE INFO using id
    public void ScrapeInfo() {
        //load series page
        Selenide.open("https://anilist.co/manga/" + id);

        // random starter code this should help
        Document doc = Jsoup.parse(WebDriverRunner.getWebDriver().getPageSource());
        String jsonLd = doc.select("script[type=application/ld+json]").first().html();
        JsonObject json = JsonParser.parseString(jsonLd).getAsJsonObject();
        JsonObject entity = json.getAsJsonObject("mainEntity");

         title = entity.get("name").getAsString();
         description = entity.get("description").getAsString();
         genres = new Gson().fromJson(entity.getAsJsonArray("genre"), String[].class);
         altTitles = new Gson().fromJson(entity.getAsJsonArray("alternateName"), String[].class);
         origin = entity.get("countryOfOrigin").getAsString();
         image = entity.get("image").getAsString();



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
        String result = "title: " + title
                +"\nalternative titles: ";
        for(int i =0; i < altTitles.length; i++) { result+=" " + altTitles[i]; }
        result+="\n";
        result+="country of origin: " + origin;
        result+="\ndescription: " + description + "\n";
        result+=image;
        result+="\n\nSALMAS rating: " + (double)rating/10 + "/10" + " and read " + progress + " chapters.";

        return result;
    }



    public static void main(String[] args) {

    }
}
