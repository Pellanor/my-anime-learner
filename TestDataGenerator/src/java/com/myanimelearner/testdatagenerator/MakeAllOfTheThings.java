package com.myanimelearner.testdatagenerator;

import com.myanimelearner.data.AnimeList;
import com.myanimelearner.testdatagenerator.utils.Consts;
import com.myanimelearner.testdatagenerator.utils.PseudoRandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MakeAllOfTheThings {

    private static Random rnd = new Random();

    private MakeAllOfTheThings() {

    }

    public static List<GeneratedAnime> makeAnime(int count) {
        List<GeneratedAnime> results = new ArrayList<>(count);
        for(int i = 0; i < count; i++) {
            results.add(new GeneratedAnime());
        }
        return results;
    }

    public static List<GeneratedFan> makeFan(int count) {
        List<GeneratedFan> results = new ArrayList<>(count);
        for(int i = 0; i < count; i++) {
            results.add(new GeneratedFan());
        }
        return results;
    }

    public static AnimeList makeList(GeneratedFan fan, List<GeneratedAnime> allAnime) {
        int numberToWatch = PseudoRandomGenerator.generateNumberOfAnimeWatched(allAnime.size());

        AnimeList watchList = new AnimeList();
        for(int i = 0; i < numberToWatch; i++) {
            int pos = i + rnd.nextInt(allAnime.size() - i);
            GeneratedAnime temp = allAnime.get(pos);
            allAnime.set(pos, allAnime.get(i));
            allAnime.set(i, temp);
            watchList.put(temp, rateAnime(temp, fan));
        }

        return watchList;
    }

    public static void makeAndPrintThings(int animeCount, int fanCount) {
        List<GeneratedAnime> anime = makeAnime(animeCount);
        List<GeneratedFan> fans = makeFan(fanCount);

        for (GeneratedFan f : fans) {
            f.setMyList(makeList(f, anime));
        }

        System.out.println(Arrays.deepToString(anime.toArray()));
        System.out.println(Arrays.deepToString(fans.toArray()));
    }

    public static double rateAnime(GeneratedAnime anime, GeneratedFan fan) {
        // -1 to 1 for quality
        // -1 to 1 for value. Should work as a force multiplier. Add 1 and multiply? sounds good.
        // If audio quality is very important to me then particularly good or bad shows get more extreme ratings.
        double quality = 0.0;
        for(int i = 0; i < Consts.ABS_SIZE; i++) {
            quality += (anime.absoluteQualities.get(i) * (1 + fan.absolutePrefs.get(i))) / Consts.ABS_SIZE;
        }
        quality = trim(quality);

        // Now time for the subjective
        // If I'm a huge romance fan, romance should be rated higher.
        // Fan's -1.0 to 1.0 range means how much they like romance.
        // Show's -1.0 to 1.0 range means how much romance there is.
        // Should just multiply the two together, as a negative to both is a higher rating.

        double pref = 0.0;
        for(int i = 0; i < Consts.REL_SIZE; i++) {
            pref += (anime.subjectiveQualities.get(i) * fan.relativePrefs.get(i)) / Consts.REL_SIZE;
        }
        pref = trim(pref);

        //Now we hve the quality, plus the preference for style. Force multiplier again?
        double rating = quality * (1 + pref);

        //Add this to the average rating.
        rating += fan.avgRating;

        return trim(rating);
    }

    private static double trim(double d) {
        if(d < -1.0) {
            return -1.0;
        }
        if(d > 1.0) {
            return  1.0;
        }
        return d;
    }

}
