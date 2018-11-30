package com.company;

import javax.swing.*;
import java.awt.*;

public class MyTable extends JPanel {

    String[] columnNames;
    Object[][] data;

    public MyTable (int coreCount, int startCount, double[] startTimes, Node[] nodeArray) {
        super(new GridLayout(1,0));
        columnNames = new String[coreCount + 1];
        data = new Object[startCount][columnNames.length];
        String label = "Start Times";
        columnNames[0] = label;
        //System.out.println(startCount);
        for (int i = 1; i < columnNames.length; i++) {
            columnNames[i] = "Core " + (i - 1);
        }
        for (int j = 0; j < startCount; j++) {
            //System.out.println(j);
            //System.out.println(startTimes[j]);
            data[j][0] = startTimes[j];
        }
        for (int m = 0; m < data.length; m++) {
            for (int n = 1; n < data[m].length; n++) {
                data[m][n] = "X";
            }
        }

        double tWeight;
        for (int y = 0; y < nodeArray.length; y++) {
            for (int m = 0; m < data.length; m++) {
                if (nodeArray[y].getDistance().equals(0.0)) {
                    tWeight = nodeArray[y].getDistance();
                } else {
                    tWeight = -nodeArray[y].getDistance();
                }
                if (data[m][0].equals(tWeight)) {
                    int core = nodeArray[y].getCore() + 1;
                    String[] split = nodeArray[y].getName().split(" ");
                    if (core != -1) {
                        data[m][core] = "Job " + split[0] + " Starts";
                    }
                }
            }
        }

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }


}
