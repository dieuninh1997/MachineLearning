/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

import java.util.ArrayList;
import java.util.List;
import vn.edu.vnu.uet.nlp.segmenter.UETSegmenter;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sentence s1 = new Sentence("làm thế nào để sửa máy ủi?");
        Sentence s2 = new Sentence("em không cần lựa chọn thứ hai");
        List<Sentence> lstSen;
        lstSen = new ArrayList<>();
        lstSen.add(s1);
        lstSen.add(s2);

        //Segment text using UETSegmenter+
        String modelsPath = "models";
        UETSegmenter segmenter = new UETSegmenter(modelsPath); // construct the segmenter
        lstSen.stream().map((sentence) -> {
            sentence.setSentence(segmenter.segment(sentence.getSentence()));
            return sentence;
        }).forEachOrdered((sentence) -> {
            System.out.println("Sentence="+sentence.getSentence());
        });

        System.out.println("List sentence ="+lstSen);
        Converter converter = new Converter(lstSen);
        List<List<Double>> vecList = converter.toWord2Vec();
        SimilarCalculator calculator = new SimilarCalculator();
        int size = vecList.size();
        for (int i = 0; i < size - 1; ++i) {
            for (int j = i + 1; j < size; ++j) {
                System.out.println(i + "(" + vecList.get(i) + ")\n vs \n " + j + "(" + vecList.get(j) + ")\n => diff = "
                        + calculator.euclideanDistance(vecList.get(i), vecList.get(j)));
            }
        }
    }

}
