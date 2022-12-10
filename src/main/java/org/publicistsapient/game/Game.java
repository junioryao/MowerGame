package org.publicistsapient.game;

public interface Game<T> {
    T applyInstructions();

    MowerPosition getMowerPosition();

}
