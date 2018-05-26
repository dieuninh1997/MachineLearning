/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

import java.util.List;

/**
 *
 * @author Admin
 */
public class TFIDFCalculator {

    /**
     * @param doc list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     *
     * tf (term frequency): tần suất xuất hiện của 1 từ trong 1 đoạn văn bản.
     * Với những đoạn văn bản có độ dài khác nhau, sẽ có những từ xuất hiện
     * nhiều ở những đoạn văn bản thay vì những đoạn văn bản ngắn. Vì thế tần
     * suất này thường ddouocj chia cho độ dài của đoạn văn bản như 1 phương
     * thức chuẩn hóa (normalization). tf(t)=f(t,d)/T với t: 1 từ trong đoạn văn
     * bản f(t,d) : tần suất xuất hiện của t trong đoạn văn bản T: tổng số từ
     * trong đoạn văn bản
     */
    public double tf(List<String> doc, String term) {
        double result = 0;
        result = doc.stream().filter((word) -> (term.equalsIgnoreCase(word))).map((_item) -> 1.0).reduce(result, (accumulator, _item) -> accumulator + 1);
//        System.out.println("Doc =" + doc + "; doc.size = " + doc.size());
        return result / doc.size();
       
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     *
     * idf (inverse document frequency): tính toán độ quan trọng của 1 từ. Khi
     * tính toán tf, mỗi từ đều quan trọng như nhau, nhưng có 1 số từ trong
     * tiếng Anh như : is, of, that... xuất hiện khá nhiều nhưng lại ít quan
     * trọng. Vì vậy chúng ta cần 1 số phương thức bù trừ những từ xuất hiện
     * nhiều lần và tăng độ quan trọng của những từ ít xuất hiện nhưng có ý
     * nghĩa đặc biệt cho một số đoạn văn bản hơn bằng cách tính idf
     * idf(t)=log(N/(t\in D: t\in d)) với N: tổng số đoạn văn bản {t \in D: t\in
     * d}: số văn bản chứa từ t
     *
     */
    private double idf(List<List<String>> docs, String term) {
        double n = 0;
//        System.out.println("docs = " + docs+";\n docs.size="+docs.size());

        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    /**
     * @param doc a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     *
     * tần số tf_idf (t)=tf(t) x idf(t)
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }
}
