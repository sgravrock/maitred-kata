package maitredkata;

import java.util.HashMap;
import java.util.Map;

public abstract class DefaultingMap<TKey, TValue> {
    private Map<TKey, TValue> _storage;

    public DefaultingMap() {
        _storage = new HashMap<>();
    }

    public TValue get(TKey key) {
        if (_storage.containsKey(key)) {
            return _storage.get(key);
        } else {
            TValue value = makeDefaultValue();
            _storage.put(key, value);
            return value;
        }
    }

    protected abstract TValue makeDefaultValue();
}
