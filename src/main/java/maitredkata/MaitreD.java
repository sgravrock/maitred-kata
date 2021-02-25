package maitredkata;

import java.util.Date;


public class MaitreD {
    public boolean reserve(Date date, int qty) {
        return qty <= 12;
    }
}