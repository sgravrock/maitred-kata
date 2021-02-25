package maitredkata;

import java.util.Date;


public class MaitreD {
    private int _tableSize;
    private int _numReserved;

    public MaitreD(int tableSize) {
        _tableSize = tableSize;
        _numReserved = 0;
    }
    public boolean reserve(Date date, int qty) {
        int newNumReserved = _numReserved + qty;

        if (newNumReserved <= _tableSize) {
            _numReserved = newNumReserved;
            return true;
        } else {
            return false;
        }
    }
}