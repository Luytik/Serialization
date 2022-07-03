package com.com;

public class C {
   // @Save
    private String s = "lala";
    @Save
    private String str = "lala";
    @Save(searchInside = true)
    private E e = new E();

    @Override
    public String toString() {
        return "C{}";
    }
}
