package com.example.albert.myapplication.helpers;

import java.util.Date;

public class GraphOnePoint {
    private Date date;
    private Integer milkYield;
    private Integer MOG;
    private Integer weight;

    public GraphOnePoint() {
    }

    public GraphOnePoint(Date date, Integer milkYield, Integer MOG, Integer weight) {
        this.date = date;
        this.milkYield = milkYield;
        this.MOG = MOG;
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMilkYield() {
        return milkYield;
    }

    public void setMilkYield(Integer milkYield) {
        this.milkYield = milkYield;
    }

    public Integer getMOG() {
        return MOG;
    }

    public void setMOG(Integer MOG) {
        this.MOG = MOG;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
