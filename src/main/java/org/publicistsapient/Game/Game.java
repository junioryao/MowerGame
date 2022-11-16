package org.publicistsapient.Game;

public interface Game<T> {
    T applyInstruction();

    MowerBaseCoordinate getMowerBaseCoordinate();

}
