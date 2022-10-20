package org.publicistsapient;

import org.publicistsapient.exception.FileProcessorException;
import org.publicistsapient.exception.GameValidatorException;
import org.publicistsapient.fileProcessor.FileProcessor;
import org.publicistsapient.gameLogic.GameLogicValidator;
import org.publicistsapient.gameLogic.MowerGame;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_TXT;

/**
 * @author Junior Yao -> junioryao.jy@gmail.com
 */
public class Main {
    public static void main(String[] args) throws FileProcessorException, FileNotFoundException, GameValidatorException {
        String path = overrideDefaultGamePattern(args);
        FileProcessor fileProcessor = new FileProcessor(path);
        GameLogicValidator gameLogicValidator = new GameLogicValidator(fileProcessor);
        gameLogicValidator.validateAndBuildGame();
        List<MowerGame> mowerGameList = gameLogicValidator.getMowerGameList();
        mowerGameList.forEach(mowerGame -> {
            mowerGame.applyInstruction();
            System.out.println(Arrays.toString(mowerGame.getMowerBaseCoordinate()));
        });
    }

    private static String overrideDefaultGamePattern(String[] args) {
        String path = GAME_INPUT_PATTERN_TXT;
        if (args.length > 0 && (!args[0].isBlank() || args[0] != null)) {
            path = args[0];
        }
        return path;
    }
}
