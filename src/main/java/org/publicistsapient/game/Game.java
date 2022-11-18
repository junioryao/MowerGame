package org.publicistsapient.game;

public interface Game<T> {
    T applyInstructions();

    MowerCoordinate getMowerCoordinate();

}
