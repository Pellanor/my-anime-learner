package com.myanimelearner.network;

public interface Neuron {

    double getOutput();

    void addInput(double val);

    void addForwardConnection(Connection f);
    void addBackwardConnection(Connection b);

    boolean canPropagate();
    boolean canBackPropagate();
    void propagate();
    void backPropagate();

}
