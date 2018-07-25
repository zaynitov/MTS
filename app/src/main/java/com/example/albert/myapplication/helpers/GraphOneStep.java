package com.example.albert.myapplication.helpers;

import java.util.Date;

public class GraphOneStep {
    private Date date;
    private Integer nadoi;
    private Integer MOG;
    private Integer weight;

    public GraphOneStep() {
    }

    public GraphOneStep(Date date, Integer nadoi, Integer MOG, Integer weight) {
        this.date = date;
        this.nadoi = nadoi;
        this.MOG = MOG;
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNadoi() {
        return nadoi;
    }

    public void setNadoi(Integer nadoi) {
        this.nadoi = nadoi;
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
