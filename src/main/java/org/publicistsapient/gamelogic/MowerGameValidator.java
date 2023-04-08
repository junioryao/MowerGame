package org.publicistsapient.gamelogic;

import org.publicistsapient.fileprocessor.FileProcessor;
import org.publicistsapient.game.Builder;
import org.publicistsapient.game.MowerGame;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @implSpec validate game input data and build object
 */
public class MowerGameValidator implements Validator<MowerGame> {
    private static final Logger LOGGER = Logger.getLogger(MowerGameValidator.class.getName());
    private final FileProcessor fileProcessor;
    private final MowerValidator validator = MowerValidator.Instance();
    private final Builder builder;

    public MowerGameValidator(FileProcessor fileProcessor, Builder builder) {
        this.fileProcessor = fileProcessor;
        this.builder = builder;
    }

    @Override
    public List<MowerGame> execute() throws FileNotFoundException {
        int surfaceCoordinate = 0;
        List<String> gameLogic = fileProcessor.buildGameProcess();
        Integer[] surface = validator.getSurface(gameLogic);
        gameLogic.remove(surfaceCoordinate);
        return builder.buildGames(gameLogic, surface);
    }
}
