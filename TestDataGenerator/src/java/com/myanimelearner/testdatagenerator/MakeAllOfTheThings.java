package com.myanimelearner.testdatagenerator;

import com.myanimelearner.data.Anime;
import com.myanimelearner.data.AnimeFan;
import com.myanimelearner.data.AnimeList;
import com.myanimelearner.data.AnimeStore;
import com.myanimelearner.testdatagenerator.utils.Consts;
import com.myanimelearner.testdatagenerator.utils.PseudoRandomGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MakeAllOfTheThings {

    private static Random rnd = new Random();

    private MakeAllOfTheThings() {

    }

    public static List<Anime> makeAnime(int count) {
        List<Anime> results = new ArrayList<>(count);
        for(int i = 0; i < count; i++) {
            results.add(new GeneratedAnime());
        }
        return results;
    }

    public static List<AnimeFan> makeFan(int count) {
        List<AnimeFan> results = new ArrayList<>(count);
        for(int i = 0; i < count; i++) {
            results.add(new GeneratedFan());
        }
        return results;
    }

    public static AnimeList makeList(AnimeFan fan, List<Anime> allAnime) {
        int numberToWatch = PseudoRandomGenerator.generateNumberOfAnimeWatched(allAnime.size());

        AnimeList watchList = new AnimeList();
        for(int i = 0; i < numberToWatch; i++) {
            int pos = i + rnd.nextInt(allAnime.size() - i);
            GeneratedAnime temp = (GeneratedAnime) allAnime.get(pos);
            allAnime.set(pos, allAnime.get(i));
            allAnime.set(i, temp);
            watchList.put(temp, rateAnime(temp, (GeneratedFan) fan));
        }

        return watchList;
    }

    public static void makeAndPrintThings(int animeCount, int fanCount) {
        List<Anime> anime = makeAnime(animeCount);
        List<AnimeFan> fans = makeFan(fanCount);

        for (AnimeFan f : fans) {
            GeneratedFan gf = (GeneratedFan) f;
            gf.setMyList(makeList(f, anime));
        }

        System.out.println(Arrays.deepToString(anime.toArray()));
        System.out.println(Arrays.deepToString(fans.toArray()));
    }

    public static AnimeStore makeAnimeStore(int animeCount, int fanCount) {
        List<Anime> anime = makeAnime(animeCount);
        List<AnimeFan> fans = makeFan(fanCount);

        for (AnimeFan f : fans) {
            GeneratedFan gf = (GeneratedFan) f;
            gf.setMyList(makeList(f, anime));
        }

        AnimeStore store = new AnimeStore();
        store.setAllOfTheAnime(anime);
        store.setAllOfTheFans(fans);

        return store;
    }

    public static void makeAndSaveStore(String fileName, int animeCount, int fanCount) throws IOException {
        AnimeStore store = makeAnimeStore(animeCount, fanCount);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(store);
        oos.close();
    }

    public static void getVariance(AnimeStore store) {
        int resolution = 20;
        for (Anime a : store.getAllOfTheAnime()) {
            System.out.print(a.getID());
            for (double i = -resolution; i <= resolution; i++) {
                double x = i / resolution;
                double mean = a.getRating();
                double variance = a.getVariance();
                double stdDeviation = Math.sqrt(variance);
                double val = Math.pow(Math.exp(-(((x - mean) * (x - mean)) / ((2 * variance)))), 1 / (stdDeviation * Math.sqrt(2 * Math.PI)));
                System.out.print("," + val);
            }
            System.out.print(System.getProperty("line.separator"));
        }
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
