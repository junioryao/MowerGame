package org.publicistsapient.fileprocessor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.publicistsapient.exception.FileProcessorException;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.publicistsapient.constant.Constant.CAN_NOT_FIND_THE_FILE_FROM_THE_GIVEN_PATH;
import static org.publicistsapient.constant.Constant.FILE_PATH_SHOULD_NOT_BE_NULL_OR_EMPTY;
import static org.publicistsapient.constant.Constant.WRONG_INPUT_GAME_FILE_CONFIGURATION;
import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_2_TXT;
import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_TXT;

class FileProcessingTest {

    @Test
    @DisplayName("get File with empty path")
    void getFileWithEmptyPath() {
        FileProcessor fileProcessor = new FileProcessor("");
        FileProcessorException fileProcessorException = assertThrows(FileProcessorException.class, fileProcessor::buildGameProcess);
        assertEquals(FILE_PATH_SHOULD_NOT_BE_NULL_OR_EMPTY, fileProcessorException.getMessage());
    }

    @Test
    @DisplayName("get file with wrong input path")
    void getFileWithWrongPath() {
        FileProcessor fileProcessor = new FileProcessor("./test.txt");
        FileProcessorException NotFoundException = assertThrows(FileProcessorException.class, fileProcessor::buildGameProcess);
        assertEquals(CAN_NOT_FIND_THE_FILE_FROM_THE_GIVEN_PATH, NotFoundException.getMessage());
    }

    @Test
    @DisplayName("get file with the right input path")
    void getFileWithRightPath() throws FileProcessorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_PATTERN_TXT);
        assertNotEquals(0, fileProcessor.buildGameProcess().size() % 2);
    }

    @Test
    @DisplayName("get file with the right input path and wrong game configuration")
    void getFileWithRightPathAndWrongGameConfiguration() throws FileProcessorException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_PATTERN_2_TXT);
        FileProcessorException NotFoundException = assertThrows(FileProcessorException.class, fileProcessor::buildGameProcess);
        assertEquals(WRONG_INPUT_GAME_FILE_CONFIGURATION, NotFoundException.getMessage());
    }


}