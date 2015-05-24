package com.myanimelearner.data.implementation;

import com.myanimelearner.data.Anime;

public class DefaultAnime implements Anime {
    private int id;
    private double avgRating = 0.0;
    private double variance = 0.0;
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
        return avgRating;
    }

    @Override
    public void addRating(double rating) {
        //Update average on the fly
        double delta = (rating - this.avgRating);
        this.avgRating += delta / ratingCount;
        this.variance += delta * (rating - avgRating);
        ratingCount++;
    }

    @Override
    public double getVariance() {
        return variance / ratingCount;
    }
}
