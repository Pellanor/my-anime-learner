package com.myanimelearner.testdatagenerator;

import com.myanimelearner.data.AnimeStore;
import org.junit.Test;

import java.io.IOException;

public class MakeAllOfTheThingsTest {

    @Test
    public void testRating() {
        for(int i = 0; i < 1; i++) {
            GeneratedAnime anime = new GeneratedAnime();
            GeneratedFan fan = new GeneratedFan();
            System.out.println(anime);
            System.out.println(fan);
            System.out.println("Rating: " + MakeAllOfTheThings.rateAnime(anime, fan));
        }
    }

    @Test
    public void testAllOfTheThings() {
        MakeAllOfTheThings.makeAndPrintThings(20, 40);
    }

    @Test
    public void testVariance() {
        AnimeStore store = MakeAllOfTheThings.makeAnimeStore(100,10000);
        MakeAllOfTheThings.getVariance(store);
    }

    @Test
    public void MakeSomeThings() throws IOException {
        MakeAllOfTheThings.makeAndSaveStore("Data/testData-1", 10000, 100000);
    }
}
