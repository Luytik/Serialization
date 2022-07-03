package com.com;

public class F {
    @Save
    private int a = 7;
    private int b = 8;
    @Save
    private G g = new G();

    @Override
    public String toString() {
        return "F{" +
                "a=" + a +
                ", b=" + b +
                ", g=" + g +
                '}';
    }
}
