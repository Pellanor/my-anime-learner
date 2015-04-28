package com.myanimelearner.data.implementation;

import com.myanimelearner.data.Anime;

public class DefaultAnime implements Anime {
    private int id;
    private double rating = 0.0;
    private int ratingCount = 1;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void addRating(double rating) {
        this.rating += (rating - this.rating) / ratingCount;
        ratingCount++;
    }
}
