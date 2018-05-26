/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

/**
 *
 * @author Admin
 */
public class Item {

    private Double similarity;
    private String name;

    public Item(Double similarity, String name) {
        this.similarity = similarity;
        this.name = name;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
