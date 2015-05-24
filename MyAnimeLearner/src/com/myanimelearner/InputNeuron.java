package com.myanimelearner;

import com.myanimelearner.data.Anime;
import com.myanimelearner.data.AnimeList;
import com.myanimelearner.network.Connection;
import com.myanimelearner.network.Neuron;

public class InputNeuron implements Neuron {

    private Anime anime;
    private AnimeList list;

    public InputNeuron(Anime a) {
        this.anime = a;
    }

    @Override
    public double getOutput() {
        if (list == null) {
            throw new IllegalStateException("Cannot generate output if an AnimeList hasn't been set");
        }
        if (list.containsKey(anime)) {
            return list.get(anime);
        }
        return 0.0;
    }

    @Override
    public void addInput(double val) {
        throw new UnsupportedOperationException("Input Neurons are set by updating their associated list.");
    }

    @Override
    public void addForwardConnection(Connection f) {

    }

    @Override
    public void addBackwardConnection(Connection b) {
        throw new UnsupportedOperationException("Input Neurons don't connect back to anything");
    }

    @Override
    public boolean canPropagate() {
        return true;
    }

    @Override
    public boolean canBackPropagate() {
        return false;
    }

    @Override
    public void propagate() {

    }

    @Override
    public void backPropagate() {
        throw new UnsupportedOperationException("Input Neurons don't connect back to anything");

    }

    public void setList(AnimeList list) {
        this.list = list;
    }
}
