package com.myanimelearner.data;

import java.io.Serializable;
import java.util.List;

/**
 * All of the Data!
 */
public class AnimeStore implements Serializable {

    List<Anime> allOfTheAnime;
    List<AnimeFan> allOfTheFans;

    public List<Anime> getAllOfTheAnime() {
        return allOfTheAnime;
    }

    public void setAllOfTheAnime(List<Anime> allOfTheAnime) {
        this.allOfTheAnime = allOfTheAnime;
    }

    public List<AnimeFan> getAllOfTheFans() {
        return allOfTheFans;
    }

    public void setAllOfTheFans(List<AnimeFan> allOfTheFans) {
        this.allOfTheFans = allOfTheFans;
    }
}
