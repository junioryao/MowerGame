package org.publicistsapient.fileprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.publicistsapient.exception.FileProcessorException;

import java.io.FileNotFoundException;

import static org.publicistsapient.constant.Constant.*;
import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_2_TXT;
import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_TXT;

class FileProcessingTest {

    @Test
    @DisplayName("get File with empty path")
    void getFileWithEmptyPath() {
        FileProcessor fileProcessor = new FileProcessor("");
        FileProcessorException fileProcessorException = Assertions.assertThrows(FileProcessorException.class, () -> fileProcessor.buildGameProcess());
        Assertions.assertEquals(fileProcessorException.getMessage(), FILE_PATH_SHOULD_NOT_BE_NULL_OR_EMPTY);
    }

    @Test
    @DisplayName("get file with wrong input path")
    void getFileWithWrongPath() {
        FileProcessor fileProcessor = new FileProcessor("./test.txt");
        FileProcessorException NotFoundException = Assertions.assertThrows(FileProcessorException.class, () -> fileProcessor.buildGameProcess());
        Assertions.assertEquals(NotFoundException.getMessage(), CAN_NOT_FIND_THE_FILE_FROM_THE_GIVEN_PATH);
    }

    @Test
    @DisplayName("get file with the right input path")
    void getFileWithRightPath() throws FileProcessorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_PATTERN_TXT);
        Assertions.assertTrue(fileProcessor.buildGameProcess().size() % 2 != 0);
    }

    @Test
    @DisplayName("get file with the right input path and wrong game configuration")
    void getFileWithRightPathAndWrongGameConfiguration() throws FileProcessorException, FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor(GAME_INPUT_PATTERN_2_TXT);
        FileProcessorException NotFoundException = Assertions.assertThrows(FileProcessorException.class, () -> fileProcessor.buildGameProcess());
        Assertions.assertEquals(NotFoundException.getMessage(), WRONG_INPUT_GAME_FILE_CONFIGURATION);
    }


}