package org.publicistsapient.gamelogic;

import org.publicistsapient.game.Game;

import java.io.FileNotFoundException;
import java.util.List;

public interface LogicValidator {
    List<? extends Game> execute() throws FileNotFoundException;
}
