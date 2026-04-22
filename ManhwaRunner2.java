package org.example.manhwadatascraper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ManhwaRunner2
{
    // create a private instance variable to store an ArrayList of Cereal objects
    private ArrayList<Manhwa2> manhwas = new ArrayList<Manhwa2>();

    private static ArrayList<String[]> data = new ArrayList<String[]>();

    public ManhwaRunner2(String fileName)
    {
        // instantiate the ArrayList
        manhwas = new ArrayList<Manhwa2>();
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


                String[] info = {splitt[3], splitt[5], splitt[6]};
                data.add(info);

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

    }



    public static void main(String[] args) {

        String fileName = "gdpr_data.csv";
        ManhwaRunner2 cr = new ManhwaRunner2(fileName);

        ArrayList<Manhwa2> manhwas = new ArrayList<Manhwa2>();

        for (int i = 1; i < data.size(); i++) {
            int id = Integer.parseInt(data.get(i)[0]);
            double rating = Double.parseDouble(data.get(i)[1]);
            int progress = Integer.parseInt(data.get(i)[2]);

            Manhwa2 new_entry = new Manhwa2(id, rating, progress);
            manhwas.add(new_entry);
        }

        System.out.println(manhwas.get(0));
    }
}
