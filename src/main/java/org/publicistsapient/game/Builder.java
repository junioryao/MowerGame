package org.publicistsapient.game;

import java.util.List;

public interface Builder<T> {
    List<T> buildGames(List<String> gameLogic, int[] surface);
}
