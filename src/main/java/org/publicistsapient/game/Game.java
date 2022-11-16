package org.publicistsapient.game;

public interface Game<T> {
    T applyInstruction();

    MowerBaseCoordinate getMowerBaseCoordinate();

}
