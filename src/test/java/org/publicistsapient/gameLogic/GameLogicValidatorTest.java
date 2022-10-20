package org.publicistsapient.gameLogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.publicistsapient.exception.FileProcessorException;
import org.publicistsapient.exception.GameValidatorException;
import org.publicistsapient.fileProcessor.FileProcessor;

import java.io.FileNotFoundException;
import java.util.List;

import static org.publicistsapient.constant.Constant.*;
import static org.publicistsapient.constant.Property.*;

class GameLogicValidatorTest {

    @Test
    @DisplayName("test correct game input")
    void validateAndBuildGame() throws FileProcessorException, GameValidatorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_PATTERN_TXT);
        GameLogicValidator gameLogicValidator = new GameLogicValidator(fileProcessor);
        gameLogicValidator.validateAndBuildGame();
        List<MowerGame> mowerGameList = gameLogicValidator.getMowerGameList();
        Assertions.assertTrue(mowerGameList.size() == 2);
    }

    @Test
    @DisplayName("test with wrong game instruction")
    void validateAndBuildWrongGameInstruction() throws FileProcessorException, GameValidatorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_WRONG_GAME_INSTRUCTION);
        GameLogicValidator gameLogicValidator = new GameLogicValidator(fileProcessor);
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class, () -> gameLogicValidator.validateAndBuildGame());
        Assertions.assertEquals(gameValidatorException.getMessage(), INVALID_GAME_INSTRUCTION);
    }

    @Test
    @DisplayName("test with wrong game surface")
    void validateAndBuildWrongGameSurface() throws FileProcessorException, GameValidatorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(WRONG_GAME_SURFACE);
        GameLogicValidator gameLogicValidator = new GameLogicValidator(fileProcessor);
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class, () -> gameLogicValidator.validateAndBuildGame());
        Assertions.assertEquals(gameValidatorException.getMessage(), WRONG_GAME_SURFACE_DEFINITION);
    }

    @Test
    @DisplayName("test with wrong game base coordinate")
    void validateAndBuildWrongGameCoordinate() throws FileProcessorException, GameValidatorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(WRONG_GAME_BASE_COORDINATE);
        GameLogicValidator gameLogicValidator = new GameLogicValidator(fileProcessor);
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class, () -> gameLogicValidator.validateAndBuildGame());
        Assertions.assertEquals(gameValidatorException.getMessage(), MOWER_COORDINATE_CAN_NOT_GET_VALIDATED);
    }


}