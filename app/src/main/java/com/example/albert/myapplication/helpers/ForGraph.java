package com.example.albert.myapplication.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForGraph {

    private List<GraphOneStep> listOfGraphSteps = new ArrayList<>();
    private String csvgraph;
    private boolean readyToGraph = true;

    public boolean isReadyToGraph() {
        return readyToGraph;
    }

    public ForGraph(String csvgraph) {
        if (csvgraph == null) {
            readyToGraph = false;
            return;
        }

        if (csvgraph.startsWith(",")) {
            csvgraph = csvgraph.substring(1);
        }
        String[] graphs = csvgraph.split(",");
        for (int i = 0; i < graphs.length; i = i + 8) {
            System.out.println(graphs.length);
            System.out.println(graphs[0]+graphs[1]+graphs[2]+graphs[3]+graphs[4]+
            graphs[5]+graphs[6]);

            Integer nadoi = Integer.valueOf(graphs[i + 1]);
            Integer MOG = Integer.valueOf(graphs[i + 3]);
            Integer weight = Integer.valueOf(graphs[i + 5]);
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            Date date=null;
            try {
                date = parser.parse(graphs[i + 7]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            listOfGraphSteps.add(new GraphOneStep(date, nadoi, MOG, weight));

        }


        this.csvgraph = csvgraph;


    }


    public String getCsvgraph() {
        return csvgraph;
    }

    public void setCsvgraph(String csvgraph) {
        this.csvgraph = csvgraph;
    }

    public List<GraphOneStep> getListOfGraphSteps() {
        return listOfGraphSteps;
    }

    public void setListOfGraphSteps(List<GraphOneStep> listOfGraphSteps) {
        this.listOfGraphSteps = listOfGraphSteps;
    }
}