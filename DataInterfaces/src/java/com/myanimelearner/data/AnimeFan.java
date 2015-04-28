package com.myanimelearner.data;

import java.util.Map;

public interface AnimeFan {

    int getID();
    void setID(int id);

    Map<Anime, Double> getMyList();
    Double getRating(Anime a);
    void addRating(Anime a, double rating);

}
