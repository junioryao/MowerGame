package org.publicistsapient.gamelogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.publicistsapient.exception.FileProcessorException;
import org.publicistsapient.exception.GameValidatorException;
import org.publicistsapient.fileprocessor.FileProcessor;
import org.publicistsapient.game.MowerGame;

import java.io.FileNotFoundException;
import java.util.List;

import static org.publicistsapient.constant.Constant.INVALID_GAME_INSTRUCTION;
import static org.publicistsapient.constant.Constant.MOWER_COORDINATE_CAN_NOT_GET_VALIDATED;
import static org.publicistsapient.constant.Constant.WRONG_GAME_SURFACE_DEFINITION;
import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_TXT;
import static org.publicistsapient.constant.Property.GAME_INPUT_WRONG_GAME_INSTRUCTION;
import static org.publicistsapient.constant.Property.WRONG_GAME_BASE_COORDINATE;
import static org.publicistsapient.constant.Property.WRONG_GAME_SURFACE;

class MowerGameValidatorTest {

    @Test
    @DisplayName("test correct game input")
    void validateAndBuildGame() throws FileProcessorException, GameValidatorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_PATTERN_TXT);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor);
        List<MowerGame> mowerGameList = mowerGameValidator.execute();
        Assertions.assertEquals(2, mowerGameList.size());
    }

    @Test
    @DisplayName("test with wrong game instruction")
    void validateAndBuildWrongGameInstruction() throws FileProcessorException, GameValidatorException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_WRONG_GAME_INSTRUCTION);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor);
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class,
            mowerGameValidator::execute);
        Assertions.assertEquals(INVALID_GAME_INSTRUCTION, gameValidatorException.getMessage());
    }

    @Test
    @DisplayName("test with wrong game surface")
    void validateAndBuildWrongGameSurface() throws FileProcessorException, GameValidatorException {
        FileProcessor fileProcessor = new FileProcessor(WRONG_GAME_SURFACE);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor);
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class,
            mowerGameValidator::execute);
        Assertions.assertEquals(WRONG_GAME_SURFACE_DEFINITION, gameValidatorException.getMessage());
    }

    @Test
    @DisplayName("test with wrong game base coordinate")
    void validateAndBuildWrongGameCoordinate() throws FileProcessorException, GameValidatorException {
        FileProcessor fileProcessor = new FileProcessor(WRONG_GAME_BASE_COORDINATE);
        MowerGameValidator mowerGameValidator = new MowerGameValidator(fileProcessor);
        GameValidatorException gameValidatorException = Assertions.assertThrows(GameValidatorException.class,
            mowerGameValidator::execute);
        Assertions.assertEquals(MOWER_COORDINATE_CAN_NOT_GET_VALIDATED, gameValidatorException.getMessage());
    }


}