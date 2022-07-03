package com.com;

public class A {

    //@Save
    private String s = "polka";
    @Save
    private String str = "lolka";
    @Save(searchInside = true)
    private D d = new D();
    @Save(searchInside = true)
    private B b = new B();
    @Save
    private float f = 15;
    @Save(searchInside = true)
    private C c = new C();



    boolean aBoolean = true;

    @Override
    public String toString() {
        return "A{" +
                "d=" + d +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
