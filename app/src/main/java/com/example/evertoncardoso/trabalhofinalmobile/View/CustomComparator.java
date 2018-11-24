package com.example.evertoncardoso.trabalhofinalmobile.View;

import com.example.evertoncardoso.trabalhofinalmobile.Model.Item;

import java.util.Comparator;

public class CustomComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        if(o1.getDia() > o2.getDia())
            return 1;
        else if(o1.getDia() < o2.getDia())
            return -1;
        else
            return 0;
    }
}