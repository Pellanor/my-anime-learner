package com.myanimelearner;

import com.myanimelearner.data.Anime;
import com.myanimelearner.data.AnimeStore;
import com.myanimelearner.network.Connection;
import com.myanimelearner.network.Neuron;
import com.myanimelearner.network.implementation.DefaultNeuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Network {

    //TODO: Load data
    //TODO: Train network
    //TODO: Save network
    private static Random rand = new Random();

    void setupNetwork() {
        AnimeStore as = new AnimeStore();

    }

    private static List<Neuron> createInputList(AnimeStore as) {
        List<Neuron> inputList = new ArrayList<>(as.getAllOfTheAnime().size());
        for (Anime a : as.getAllOfTheAnime()) {
            inputList.add(new InputNeuron(a));
        }
        return inputList;
    }

    private static List<Neuron> createOutputList(AnimeStore as) {
        List<Neuron> inputList = new ArrayList<>(as.getAllOfTheAnime().size());
        for (Anime ignored : as.getAllOfTheAnime()) {
            inputList.add(new OutputNeuron());
        }
        return inputList;
    }

    private static void createLayers(List<Neuron> inputs, List<Neuron> outputs, Stack<Integer> layers) {
        if (layers == null || layers.size() == 0) {
            for (Neuron first : inputs) {
                for (Neuron second : outputs) {
                    Connection c = new Connection(first, second, rand.nextDouble() * 2.0 - 1.0);
                    first.addForwardConnection(c);
                    second.addBackwardConnection(c);
                }
            }
        } else {
            int layerSize = layers.pop();
            List<Neuron> newLayer = new ArrayList<>(layerSize);
            for (int i = 0; i < layerSize; i++) {
                newLayer.add(new DefaultNeuron());
            }
            createLayers(newLayer, outputs, null);
            createLayers(inputs, newLayer, layers);
        }

    }

}
