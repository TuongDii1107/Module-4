package com.sqc.academy;

public class Student {
    private int ID;
    private String name;
    private double score;

    public Student() {
    }

    public Student(int ID, String name, double score) {
        this.ID = ID;
        this.name = name;
        this.score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

