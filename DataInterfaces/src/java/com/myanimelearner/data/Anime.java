package com.myanimelearner.data;

import java.io.Serializable;

public interface Anime extends Serializable {

    int getID();
    void setID(int id);

    double getRating();
    void addRating(double rating);

    double getVariance();
}
