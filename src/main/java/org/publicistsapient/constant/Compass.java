package org.publicistsapient.constant;

public enum Compass {
    N(0), E(1), S(2), W(3);

    private final Integer value;

    Compass(int inputValue) {
        value = inputValue;
    }

    public Integer getValue() {
        return value;
    }

    public static Compass getFromValue(int inputValue) {
        for (Compass compass : Compass.values()) {
            if (compass.getValue() == inputValue) return compass;
        }
        throw new IllegalArgumentException();
    }
}
