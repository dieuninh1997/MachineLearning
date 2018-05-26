/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class Converter {

    private final List<Sentence> sentences;
    //stop word : là những từ loại bỏ ra khỏi câu mà vẫn k làm mất đi ý nghĩa của câu
    private final Set<String> stopwordList = new HashSet<>();
    private static final String URL = "./vnstopword.txt";

    //câu sau khi đc loại bỏ các từ stopword
    private final Set<String> features = new HashSet<>();

    public Set<String> getFeatures() {
        //    System.out.println("splitText=" + splitText());
        return features;
    }

    public Converter(List<Sentence> sentences) {
        this.sentences = sentences;
        readStopWords();
        //   System.out.println("Convert sentences="+sentences);
        //    System.out.println("stopwordList=" + stopwordList);

    }

    /*
    this method is used to convert a word to vector
     */
    public List<List<Double>> toWord2Vec() {
        List<List<Double>> rs = new ArrayList<>();

        List<List<String>> documents = splitText();
        System.out.println("documents= " + documents);
        System.out.println("features= " + features);

        //  System.out.println("Documents = "+documents);
        TFIDFCalculator calculator = new TFIDFCalculator();
        documents.stream().map((docs) -> {
            //  System.out.println("Docs = "+docs);
            List<Double> item = new ArrayList<>();
            features.forEach((word) -> {
                item.add(calculator.tfIdf(docs, documents, word));
            });
            return item;
        }).forEachOrdered((item) -> {
            System.out.println("item = " + item);
            rs.add(item);
        });
        //   System.out.println("feartures = "+features);
        return rs;
    }

    /*
    this method is used to read stop word
     */
    private void readStopWords() {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(URL))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stopwordList.add(line.toLowerCase().trim());
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }


    /*
    this method is used to 
     */
    private List<List<String>> splitText() {
        List<List<String>> rs = new ArrayList<>();
        sentences.stream().map((sentence) -> {
            List<String> item = new ArrayList<>();
            String[] a = sentence.getSentence().toLowerCase().split("[^_a-záàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]+");
//            System.out.println("a =" +a);
            for (String i : a) {
                if (!stopwordList.contains(i)) {
                    item.add(i);
                    features.add(i);
                }
            }
            return item;
        }).forEachOrdered((item) -> {
            rs.add(item);
        });
        return rs;
    }
}
