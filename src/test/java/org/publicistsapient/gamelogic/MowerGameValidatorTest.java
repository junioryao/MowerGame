package org.publicistsapient.gamelogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.publicistsapient.exception.FileProcessorException;
import org.publicistsapient.exception.GameValidatorException;
import org.publicistsapient.fileprocessor.FileProcessor;
import org.publicistsapient.game.MowerGame;
import org.publicistsapient.game.MowerGameBuilder;

import java.io.FileNotFoundException;
import java.util.List;

import static org.publicistsapient.constant.Constant.*;
import static org.publicistsapient.constant.Property.*;

class MowerGameValidatorTest {

    @Test
    @DisplayName("test correct game input")
    void validateAndBuildGame() throws FileProcessorException, GameValidatorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_PATTERN_TXT);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor, new MowerGameBuilder());
        List<MowerGame> mowerGameList = mowerGameValidator.execute();
        Assertions.assertEquals(2, mowerGameList.size());
    }

    @Test
    @DisplayName("test with wrong game instruction")
    void validateAndBuildWrongGameInstruction() throws FileProcessorException, GameValidatorException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_WRONG_GAME_INSTRUCTION);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor, new MowerGameBuilder());
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class,
                mowerGameValidator::execute);
        Assertions.assertEquals(INVALID_GAME_INSTRUCTION, gameValidatorException.getMessage());
    }

    @Test
    @DisplayName("test with wrong game surface")
    void validateAndBuildWrongGameSurface() throws FileProcessorException, GameValidatorException {
        FileProcessor fileProcessor = new FileProcessor(WRONG_GAME_SURFACE);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor, new MowerGameBuilder());
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class,
                mowerGameValidator::execute);
        Assertions.assertEquals(WRONG_GAME_SURFACE_DEFINITION, gameValidatorException.getMessage());
    }

    @Test
    @DisplayName("test with wrong game base coordinate")
    void validateAndBuildWrongGameCoordinate() throws FileProcessorException, GameValidatorException {
        FileProcessor fileProcessor = new FileProcessor(WRONG_GAME_BASE_COORDINATE);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor, new MowerGameBuilder());
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class,
                mowerGameValidator::execute);
        Assertions.assertEquals(MOWER_COORDINATE_CAN_NOT_GET_VALIDATED, gameValidatorException.getMessage());
    }


}