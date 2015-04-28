package com.myanimelearner.testdatagenerator;

import com.myanimelearner.data.AnimeList;
import com.myanimelearner.data.implementation.DefaultFan;
import com.myanimelearner.testdatagenerator.utils.Consts;
import com.myanimelearner.testdatagenerator.utils.PseudoRandomGenerator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GeneratedFan extends DefaultFan{

    static int nextId = 1;

    double avgRating;
    public List<Double> absolutePrefs = new ArrayList<>(Consts.ABS_SIZE);
    public List<Double> relativePrefs = new ArrayList<>(Consts.REL_SIZE);

    public GeneratedFan() {
        setID(nextId);
        nextId++;
        avgRating = PseudoRandomGenerator.generatePreference(0);
        for (int i = 0; i < Consts.ABS_SIZE ; i++) {
            absolutePrefs.add(PseudoRandomGenerator.generatePreference(avgRating));
        }
        for (int i = 0; i < Consts.REL_SIZE ; i++) {
            relativePrefs.add(PseudoRandomGenerator.generatePreference(avgRating));
        }
    }

    public void setMyList(AnimeList newList) {
        this.myList = newList;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getPercentInstance();
        formatter.setMinimumFractionDigits(1);
        StringBuilder sb = new StringBuilder();
        sb.append("Fan #");
        sb.append(getID());
        sb.append(System.getProperty("line.separator"));
        for(Double d : absolutePrefs) {
            sb.append(formatter.format(d));
            sb.append(", ");
        }
        sb.append(System.getProperty("line.separator"));
        for(Double d : relativePrefs) {
            sb.append(formatter.format(d));
            sb.append(", ");
        }
        sb.append(System.getProperty("line.separator"));
        sb.append(getMyList());
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
