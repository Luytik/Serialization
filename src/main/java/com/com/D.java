package com.com;

public class D {
    @Save
    private int a = 15;
    @Save(searchInside = true)
    private F f = new F();
    @Save
    private int b = 13;

    @Override
    public String toString() {
        return "D{" +
                "f=" + f +
                '}';
    }
}
