package com.example.albert.myapplication.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraphWithPoints {

    private List<GraphOnePoint> listOfGraphSteps = new ArrayList<>();
    private String csvgraph;
    private boolean readyToDraw = true;

    public boolean isReadyToDraw() {
        return readyToDraw;
    }

    public GraphWithPoints(String csvgraph) {
        if (csvgraph == null) {
            readyToDraw = false;
            return;
        }

        if (csvgraph.startsWith(",")) {
            csvgraph = csvgraph.substring(1);
        }
        String[] graphs = csvgraph.split(",");
        for (int i = 0; i < graphs.length; i = i + 8) {
            Integer milkYield = Integer.valueOf(graphs[i + 1]);
            Integer MOG = Integer.valueOf(graphs[i + 3]);
            Integer weight = Integer.valueOf(graphs[i + 5]);
            if (milkYield == -1) milkYield = interpolate(i, 8 - 1, graphs);
            if (MOG == -1) MOG = interpolate(i, 8 - 3, graphs);
            if (weight == -1) weight = interpolate(i, 8 - 1, graphs);
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = parser.parse(graphs[i + 7]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            listOfGraphSteps.add(new GraphOnePoint(date, milkYield, MOG, weight));
        }
        this.csvgraph = csvgraph;
    }

    public Integer interpolate(Integer i, Integer step, String[] graphs) {
        if (i > 8) {
            return Integer.parseInt(graphs[i - step]);
        } else return 0;
    }


    public String getCsvgraph() {
        return csvgraph;
    }

    public void setCsvgraph(String csvgraph) {
        this.csvgraph = csvgraph;
    }

    public List<GraphOnePoint> getListOfGraphSteps() {
        return listOfGraphSteps;
    }

    public void setListOfGraphSteps(List<GraphOnePoint> listOfGraphSteps) {
        this.listOfGraphSteps = listOfGraphSteps;
    }
}