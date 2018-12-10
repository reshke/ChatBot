package main.java.finders;

import java.io.Serializable;

public interface FinderLinesDifference extends Serializable {
    public int getDifference(String firstString, String secondString);
}
