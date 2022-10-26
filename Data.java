/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jianing
 */
public class Data {

    private Map<Integer, Double> numbers;
    private Map<String, Map<Integer, Double>> values;

    public Data() {
        this.numbers = new HashMap<>();
        this.values = new HashMap<>();
    }

    public void addValues(String party, Map<Integer, Double> numbers) { // big Map
        values.put(party, numbers);
    }

    public Map<Integer, Double> addNumbers(String year, String rate) { // small Map
        double rateNumber = Double.valueOf(rate);
        int numberYear = Integer.valueOf(year);
        numbers.put(numberYear, rateNumber);
        return numbers;
    }

    public Map<String, Map<Integer, Double>> readData() throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in);

        try (Scanner doc = new Scanner(Paths.get("partiesdata.tsv"))) {

            //1. First line contents
            String firstLine = doc.nextLine();
            String[] firstLinePieces = firstLine.split("	");

            //2. Other lines
            while (doc.hasNextLine()) {
                String line = doc.nextLine();
                System.out.println("line: " + line);
                String[] linePieces = line.split("	");

                String partyName = linePieces[0];

                Map<Integer, Double> keepTrack = new HashMap<>();
                for (int i = 1; i < linePieces.length; i++) {
                    if (linePieces[i].equals("-")) {
                        addNumbers(firstLinePieces[i], "0");
                    } else {
                        addNumbers(firstLinePieces[i], linePieces[i]);
                    }
                }
                
                for (int each : numbers.keySet()) { // need to copy the values, rather than references
                    keepTrack.put(each, Double.valueOf(numbers.get(each)));
                }
                
                addValues(partyName, keepTrack);

//                for (String each : values.keySet()) {
//                    System.out.println("party: " + each + ": " + values.get(each));
//                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
          
        return values;
    }

//    private List<String> readFirstLine()
}
