package org.publicistsapient.game;

public enum Compass {
    N(0), E(1), S(2), W(3);

    private final Integer value;

    Compass(int inputValue) {
        value = inputValue;
    }

    public static Compass getFromValue(int inputValue) {
        for (Compass compass : Compass.values()) {
            if (compass.getValue() == inputValue) return compass;
        }
        throw new IllegalArgumentException();
    }

    public Integer getValue() {
        return value;
    }
}
