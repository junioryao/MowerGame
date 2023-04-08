package org.publicistsapient.game;

import java.util.List;

public interface Builder<T, I, S> {
    List<T> buildGames(List<I> gameLogic, S[] surface);
}
