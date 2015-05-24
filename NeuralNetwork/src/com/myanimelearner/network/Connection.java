package com.myanimelearner.network;

public class Connection {

    private final Neuron first;
    private final Neuron second;
    private double weight;

    public Connection (Neuron first, Neuron second, double weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    public void doConnection() {
        second.addInput(first.getOutput() * weight);
    }

    public void adjustWeight(double newWeight) {
        this.weight = newWeight;
    }
}
