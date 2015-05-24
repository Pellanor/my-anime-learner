package com.myanimelearner.testdatagenerator.utils;

public class PseudoRandomGenerator {

    private PseudoRandomGenerator() {
        //Empty private constructor for utility class
    }

    /**
     * Generates a "preference" that is between -1.0 and 1.0 with an mean of avg.
     * @param avg the mean to use.
     * @return generated pseudorandom value
     */
    public static double generatePreference(double avg) {
        double result;
        do {
            result = generateGaussianNoise(0.25) + avg;
        } while ( !(-1.0 <= result && result <= 1.0));

        return result;
    }

    public static int generateNumberOfAnimeWatched(int animeCount) {
        int mean = 100;
        int stdDev = 25;
        int result;
        do {
            result = (int) (generateGaussianNoise(stdDev) + mean);
        } while ( !(0 < result && result < animeCount));

        return result;
    }

    private static double next;
    private static boolean generate;

    /**
     * Polar Box-Muller transform
     * See: http://en.wikipedia.org/wiki/Box%E2%80%93Muller_transform#Polar_form
     * @return a pseudo random number with a mean of 0 and stdDev of 1
     */
    static double generateGaussianNoise(double stdDev) {
        //Since pairs are generated we only have to recalculate every other run
        generate = ! generate;
        if(!generate) {
            return next;
        }

        double s, u, v;

        //Generate a point within the unit circle
        do {
            u = 2.0 * Math.random() - 1.0;
            v = 2.0 * Math.random() - 1.0;
            s = u*u + v*v;
        } while (s >= 1 || s == 0);

        double z = Math.sqrt(-2.0 * Math.log(s) / s);

        next = stdDev * u * z;
        return stdDev * v * z;
    }
}
