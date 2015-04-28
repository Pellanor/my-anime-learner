package com.myanimelearner.testdatagenerator;

import org.junit.Test;

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
}
