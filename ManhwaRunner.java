package org.example.manhwadatascraper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;


public class ManhwaRunner
{
    // create a private instance variable to store an ArrayList of Cereal objects
    private ArrayList<Manhwa> manhwas = new ArrayList<Manhwa>();

    private static ArrayList<String[]> data = new ArrayList<String[]>();

    public ManhwaRunner(String fileName)
    {
        // instantiate the ArrayList
        manhwas = new ArrayList<Manhwa>();
        data = new ArrayList<String[]>();
        int count = 0;

        try {
        FileReader file = new FileReader(fileName);
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine())
            {
                String myStr = scan.nextLine();

                // use the split method to parse the data into an array of
                //   String objects
                String[] splitt = myStr.split(",");

                /*System.out.println(splitt[3]);
                System.out.println(splitt[5]);
                System.out.println(splitt[6]);*/

                String[] info = {splitt[3], splitt[5], splitt[6]};
                data.add(info);



            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF);

        Configuration.browserSize = "1280x800";
        Configuration.headless = true;
        Selenide.open("https://anilist.co");

        String fileName = "gdpr_data.csv";
        ManhwaRunner cr = new ManhwaRunner(fileName);

        ArrayList<Manhwa> Manhwas = new ArrayList<Manhwa>();

        //************************************************************
        // Other possible ids (but no ratings/progress, im lazy) 105398
        //119257
        //86964
        //126297
        //123573
        //107521
        //119521
        //140407
        //118408
        //132144
        //144957
        //151512
        //143056
        //104973
        //117540
        //121991
        //136331
        //146139
        //159930
        //130429
        //113488
        //130229
        //116382
        //137304
        //120385
        //141958
        //121565
        //111075
        //110472
        //149483
        //128521
        //141013
        Manhwa new_entry = new Manhwa(119521, 90, 202);
        new_entry.ScrapeInfo();

        System.out.println(new_entry);


        Selenide.closeWebDriver();

    }
}
