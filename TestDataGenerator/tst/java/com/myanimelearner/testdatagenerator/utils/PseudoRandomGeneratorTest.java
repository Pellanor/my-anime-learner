package com.myanimelearner.testdatagenerator.utils;

import org.junit.Test;

public class PseudoRandomGeneratorTest {

    @Test
    public void printABunchOfStuff() {
        for (int i = 0; i < 100; i++) {
            System.out.println(PseudoRandomGenerator.generateGaussianNoise(0));
        }
    }
}
