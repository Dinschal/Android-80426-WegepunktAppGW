package com.example.wagenhuberg.android_180426_wegepunktapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagenhuberg on 26.04.2018.
 */

public class Repo {

    private List<WegePunkt> wegePunktList;

    public Repo() {
        wegePunktList = new ArrayList<>();
    }

    public void add(WegePunkt wegePunkt) {
        if (wegePunkt != null) {
            wegePunktList.add(wegePunkt);
        }
    }


    public WegePunkt get(int index) {
        if (index < 0 || index >= wegePunktList.size()) {
            return null;
        }
        return wegePunktList.get(index);
    }


    public int size() {
        return wegePunktList.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (WegePunkt wegePunkt : wegePunktList) {
            stringBuilder.append(wegePunkt.toString());
            stringBuilder.append(String.format("%n"));
        }
        return stringBuilder.toString();
    }
}
