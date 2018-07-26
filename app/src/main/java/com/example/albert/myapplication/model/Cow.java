package com.example.albert.myapplication.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Cow {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String breed;
    private String colour;
    private String age;
    private String birthday;
    private String mother;
    private String father;
    private String csvgraph;

    public Cow() {
    }

    public Cow(int id, String breed, String colour, String age, String  birthday, String mother, String father, String csvgraph) {
        this.id = id;
        this.breed = breed;
        this.colour = colour;
        this.age = age;
        this.birthday = birthday;
        this.mother = mother;
        this.father = father;
        this.csvgraph = csvgraph;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String  getBirthday() {
        return birthday;
    }

    public void setBirthday(String  birthday) {
        this.birthday = birthday;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getCsvgraph() {
        return csvgraph;
    }

    public void setCsvgraph(String csvgraph) {
        this.csvgraph = csvgraph;
    }

    @Override
    public String toString() {
        return "Cow{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", colour='" + colour + '\'' +
                ", age='" + age + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mother='" + mother + '\'' +
                ", father='" + father + '\'' +
                ", csvgraph='" + csvgraph + '\'' +
                '}';
    }
}
