package com.myanimelearner.data;

import java.io.Serializable;
import java.util.Map;

public interface AnimeFan extends Serializable {

    int getID();
    void setID(int id);

    Map<Anime, Double> getMyList();
    Double getRating(Anime a);
    void addRating(Anime a, double rating);

}
