package com.myanimelearner.network.implementation;

import com.myanimelearner.network.Connection;
import com.myanimelearner.network.Neuron;

import java.util.ArrayList;
import java.util.List;

public class DefaultNeuron implements Neuron {

    private List<Connection> forwardConnections = new ArrayList<>();
    private List<Connection> backwardConnections = new ArrayList<>();
    private double val = 0.0;

    @Override
    public double getOutput() {
        return val;
    }

    @Override
    public void addInput(double val) {
        this.val += val;
    }

    @Override
    public void addForwardConnection(Connection f) {
        forwardConnections.add(f);
    }

    @Override
    public void addBackwardConnection(Connection b) {
        backwardConnections.add(b);
    }

    @Override
    public boolean canPropagate() {
        return true;
    }

    @Override
    public boolean canBackPropagate() {
        return true;
    }

    @Override
    public void propagate() {
        forwardConnections.forEach(Connection::doConnection);
    }

    @Override
    public void backPropagate() {
        backwardConnections.forEach(Connection::doConnection);
    }
}
