package org.publicistsapient.gameLogic;

import org.publicistsapient.exception.FileProcessorException;
import org.publicistsapient.exception.GameValidatorException;
import org.publicistsapient.fileProcessor.FileProcessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.publicistsapient.constant.Constant.*;

/**
 * @implSpec validate game input data and build object
 */
public class GameLogicValidator {
    FileProcessor fileProcessor;
    List<MowerGame> mowerGameList;

    private final static Logger LOGGER = Logger.getLogger(GameLogicValidator.class.getName());

    public GameLogicValidator(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
        this.mowerGameList = new ArrayList<>();
    }

    /**
     * @throws FileProcessorException
     * @throws FileNotFoundException
     * @throws GameValidatorException
     * @implNote extract the data from file and build game object
     */
    public void validateAndBuildGame() throws FileProcessorException, FileNotFoundException, GameValidatorException {
        int[] surface;
        fileProcessor.buildGameProcess();
        List<String> gameLogic = fileProcessor.getGameLogic();
        surface = getSurface(gameLogic);
        gameLogic.remove(0);
        this.mowerGameList = buildGame(gameLogic, surface);
        LOGGER.info("Mower game build successfully");
    }

    /**
     * @param gameLogic
     * @param surface
     * @return
     * @throws GameValidatorException
     * @implNote build game object
     */
    private List<MowerGame> buildGame(List<String> gameLogic, int[] surface) throws GameValidatorException {
        List<MowerGame> mowerGameList = new ArrayList<>();
        String[] mowerCoordinate;
        String[] mowerInstructions;
        for (int i = 0; i < gameLogic.size() - 1; i = i + 2) {
            mowerCoordinate = validateMowerCoordinate(gameLogic.get(i));
            mowerInstructions = validateMowerInstruction(gameLogic.get(i + 1));
            mowerGameList.add(new MowerGame(mowerCoordinate[2], new int[]{Integer.parseInt(mowerCoordinate[0]),
                Integer.parseInt(mowerCoordinate[1])}, mowerInstructions, surface));
        }
        return mowerGameList;
    }

    /**
     * @param s
     * @return
     * @throws GameValidatorException
     * @implNote validate correct game instruction sequence
     */
    private String[] validateMowerInstruction(String s) throws GameValidatorException {
        String[] instruction = s.toUpperCase().split("");
        for (String s1 : instruction) {
            if (!Pattern.matches("[^BCEFHIJKLMNOPQRSTUVWXYZ]", s1)) {
                throw new GameValidatorException(INVALID_GAME_INSTRUCTION);
            }
        }
        return s.toUpperCase().split("");
    }

    /**
     * @param s
     * @return
     * @throws GameValidatorException
     * @implNote validate correct initial mower position
     */
    private String[] validateMowerCoordinate(String s) throws GameValidatorException {
        String[] coordinate = s.toUpperCase().split(" ");
        if (coordinate.length == 3) return coordinate;
        if (coordinate.length == 2) {
            int x = Integer.parseInt(coordinate[0]);
            int y = Integer.parseInt(coordinate[1]);
            if (y == x + 1) return new String[]{coordinate[0], coordinate[1], "N"};
        }
        throw new GameValidatorException(MOWER_COORDINATE_CAN_NOT_GET_VALIDATED);
    }

    /**
     * @param gameLogic
     * @return
     * @throws GameValidatorException
     * @implNote validate correct surface definition
     */
    private int[] getSurface(List<String> gameLogic) throws GameValidatorException {
        String[] surface = gameLogic.get(0).split(" ");
        if (surface.length != 2) throw new GameValidatorException(WRONG_GAME_SURFACE_DEFINITION);
        int x = Integer.parseInt(surface[0]);
        int y = Integer.parseInt(surface[1]);
        return new int[]{x, y};
    }

    public List<MowerGame> getMowerGameList() {
        return mowerGameList;
    }
}
