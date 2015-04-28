package com.myanimelearner.data.implementation;

import com.myanimelearner.data.Anime;
import com.myanimelearner.data.AnimeFan;
import com.myanimelearner.data.AnimeList;

import java.util.Map;

public class DefaultFan implements AnimeFan {

    private int id;
    protected AnimeList myList = new AnimeList();

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public Map<Anime, Double> getMyList() {
        return myList;
    }

    @Override
    public Double getRating(Anime a) {
        return myList.get(a);
    }

    @Override
    public void addRating(Anime a, double rating) {
        myList.put(a, rating);
    }
}
