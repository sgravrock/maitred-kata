package maitredkata;

import java.util.Date;
import java.util.List;

public class HauteCuisineMaitreD {
    private final List<Integer> _tableSizes;

    public HauteCuisineMaitreD(List<Integer> tableSizes) {
        _tableSizes = tableSizes;
    }

    public boolean reserve(Date date, int qty) {
        return _tableSizes.stream().anyMatch((size) -> size >= qty);
    }
}
