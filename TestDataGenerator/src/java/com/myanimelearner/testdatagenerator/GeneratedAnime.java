package com.myanimelearner.testdatagenerator;

import com.myanimelearner.data.implementation.DefaultAnime;
import com.myanimelearner.testdatagenerator.utils.Consts;
import com.myanimelearner.testdatagenerator.utils.PseudoRandomGenerator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GeneratedAnime extends DefaultAnime {

    static int nextId = 1;

    public List<Double> absoluteQualities = new ArrayList<>(Consts.ABS_SIZE);
    public List<Double> subjectiveQualities = new ArrayList<>(Consts.REL_SIZE);

    public GeneratedAnime() {
        setID(nextId);
        nextId++;
        for (int i = 0; i < Consts.ABS_SIZE; i++) {
            absoluteQualities.add(PseudoRandomGenerator.generatePreference(0.0));
        }
        for (int i = 0; i < Consts.REL_SIZE ; i++) {
            subjectiveQualities.add(PseudoRandomGenerator.generatePreference(0.0));
        }
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getPercentInstance();
        formatter.setMinimumFractionDigits(1);
        StringBuilder sb = new StringBuilder();
        sb.append("Anime #");
        sb.append(getID());
        sb.append(System.getProperty("line.separator"));
        for(Double d : absoluteQualities) {
            sb.append(formatter.format(d));
            sb.append(", ");
        }
        sb.append(System.getProperty("line.separator"));
        for(Double d : subjectiveQualities) {
            sb.append(formatter.format(d));
            sb.append(", ");
        }
        sb.append(System.getProperty("line.separator"));
        return sb.toString();    }
}
