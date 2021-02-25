package maitredkata;

import java.util.Date;


public class MaitreD {
    private int _tableSize;

    public MaitreD(int tableSize) {
        _tableSize = tableSize;
    }
    public boolean reserve(Date date, int qty) {
        return qty <= _tableSize;
    }
}