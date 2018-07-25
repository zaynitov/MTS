package com.example.albert.myapplication.helpers;

import com.example.albert.myapplication.model.Cow;

import java.util.ArrayList;
import java.util.List;

public class ConvertorForListsRecView {
    private List<Cow> cows;

    public static List<String> convert(List<Cow> cows) {
        List<String> cowsForRecycleView = new ArrayList<>();
        cowsForRecycleView.add("#Бирка");
        cowsForRecycleView.add("Порода");
        cowsForRecycleView.add("Масть");
        cowsForRecycleView.add("Возраст");


        for (Cow cow : cows) {
            cowsForRecycleView.add(String.valueOf(cow.getId()));
            cowsForRecycleView.add(cow.getPoroda());
            cowsForRecycleView.add(cow.getMast());
            cowsForRecycleView.add(cow.getAge());
        }
        return cowsForRecycleView;
    }


}
