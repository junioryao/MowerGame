package org.publicistsapient.fileprocessor;

import org.publicistsapient.exception.FileProcessorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.publicistsapient.constant.Constant.*;

/**
 * #FileProcessor handle the .txt data extraction
 */
public class FileProcessor {

    private final static Logger LOGGER = Logger.getLogger(FileProcessor.class.getName());
    List<String> fileCollector;
    private String filePath;

    public FileProcessor(String path) {
        this.filePath = path;
        this.fileCollector = new ArrayList<>();
    }

    /**
     * get the game schema from the input file
     *
     * @return
     * @throws FileProcessorException
     * @throws FileNotFoundException
     */
    public List<String> buildGameProcess() throws FileProcessorException, FileNotFoundException {
        validateFilePath(filePath);
        File file = new File((filePath));
        validateFile(file);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine().strip();
            fileCollector.add(data);
        }
        fileReader.close();
        validateInputFileConfiguration();
        LOGGER.info("Game file has been loaded successfully");
        return fileCollector;
    }

    private void validateInputFileConfiguration() throws FileProcessorException {
        if (fileCollector.size() % 2 == 0) throw new FileProcessorException(WRONG_INPUT_GAME_FILE_CONFIGURATION);
    }

    private void validateFilePath(String filePath) throws FileProcessorException {
        if (filePath.isBlank()) {
            throw new FileProcessorException(FILE_PATH_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
    }

    private void validateFile(File file) throws FileProcessorException {
        if (!file.exists()) {
            throw new FileProcessorException(CAN_NOT_FIND_THE_FILE_FROM_THE_GIVEN_PATH);
        }
    }
}