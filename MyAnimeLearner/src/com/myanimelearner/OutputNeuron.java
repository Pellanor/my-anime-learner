package com.myanimelearner;

import com.myanimelearner.network.Connection;
import com.myanimelearner.network.Neuron;

public class OutputNeuron implements Neuron {
    @Override
    public double getOutput() {
        return 0;
    }

    @Override
    public void addInput(double val) {

    }

    @Override
    public void addForwardConnection(Connection f) {
        throw new UnsupportedOperationException("Output Neurons are the last layer in the network");
    }

    @Override
    public void addBackwardConnection(Connection b) {

    }

    @Override
    public boolean canPropagate() {
        return false;
    }

    @Override
    public boolean canBackPropagate() {
        return true;
    }

    @Override
    public void propagate() {
        throw new UnsupportedOperationException("Output Neurons are the last layer in the network");
    }

    @Override
    public void backPropagate() {

    }
}
