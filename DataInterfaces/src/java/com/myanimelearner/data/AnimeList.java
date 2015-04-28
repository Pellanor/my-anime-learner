package com.myanimelearner.data;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnimeList implements Map<Anime, Double> {
    private Map<Anime, Double> map = new HashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Double get(Object key) {
        return map.get(key);
    }

    @Override
    public Double put(Anime key, Double value) {
        if(value < -1.0 || 1.0 < value) {
            throw new IllegalArgumentException("Cannot add rating " + value + " for Anime #" + key.getID() + ". Ratings must be between -1.0 and 1.0");
        }
        key.addRating(value);
        return map.put(key, value);
    }

    @Override
    public Double remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends Anime, ? extends Double> m) {
        for(Entry<? extends Anime, ? extends Double> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<Anime> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Double> values() {
        return map.values();
    }

    @Override
    public Set<Entry<Anime, Double>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getPercentInstance();
        formatter.setMinimumFractionDigits(1);
        StringBuilder sb = new StringBuilder();
        for(Entry<? extends Anime, ? extends Double> entry : map.entrySet()) {
            sb.append("Anime #");
            sb.append(entry.getKey().getID());
            sb.append(": ");
            sb.append(formatter.format(entry.getValue()));
            sb.append(" (");
            sb.append(formatter.format(entry.getKey().getRating()));
            sb.append(" avg)");
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
