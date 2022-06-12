package com.sree;

public class Leader implements Comparable<Leader> {
    private String name;
    private int score;

    public Leader(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Leader compareTo) {
        int compareScore = compareTo.getScore();

        return compareScore - this.score;
    }

    @Override
    public String toString() {
        return score + " - " + name;
    }
}
