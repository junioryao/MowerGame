package org.publicistsapient.gamelogic;

import org.publicistsapient.exception.FileProcessorException;
import org.publicistsapient.exception.GameValidatorException;
import org.publicistsapient.fileprocessor.FileProcessor;
import org.publicistsapient.game.Game;
import org.publicistsapient.game.GameSurface;
import org.publicistsapient.game.MowerCoordinate;
import org.publicistsapient.game.MowerGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.publicistsapient.constant.Constant.*;

/**
 * @implSpec validate game input data and build object
 */
public class MowerGameValidator implements LogicValidator {
    private final static Logger LOGGER = Logger.getLogger(MowerGameValidator.class.getName());
    FileProcessor fileProcessor;

    public MowerGameValidator(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    /**
     * @throws FileProcessorException
     * @throws FileNotFoundException
     * @throws GameValidatorException
     * @implNote extract the data from file and build game object
     */
    @Override
    public List<? extends Game> execute() throws FileProcessorException, FileNotFoundException, GameValidatorException {
        int[] surface;
        int surfaceCoordinate = 0;
        List<String> gameLogic = fileProcessor.buildGameProcess();
        surface = getSurface(gameLogic);
        gameLogic.remove(surfaceCoordinate);
        return buildGames(gameLogic, surface);
    }

    /**
     * @param gameLogic
     * @param surface
     * @return
     * @throws GameValidatorException
     * @implNote build game object
     */
    private List<? extends Game> buildGames(List<String> gameLogic, int[] surface) throws GameValidatorException {
        List<MowerGame> mowerGameList = new ArrayList<>();
        GameSurface gameSurface = buildGameSurface(surface);
        for (int i = 0; i < gameLogic.size() - 1; i = i + 2) {
            String[] mowerCoordinate = validateMowerCoordinate(gameLogic.get(i));
            String[] mowerInstructions = validateMowerInstruction(gameLogic.get(i + 1));
            MowerCoordinate coordinate = buildMowerCoordinate(mowerCoordinate);
            MowerGame mowerGame = buildGame(gameSurface, mowerInstructions, coordinate);
            mowerGameList.add(mowerGame);
        }
        LOGGER.info("Mower game build successfully");
        return mowerGameList;
    }

    private static GameSurface buildGameSurface(int[] surface) {
        return new GameSurface(surface[0], surface[1]);
    }

    private static MowerCoordinate buildMowerCoordinate(String[] mowerCoordinate) {
        return MowerCoordinate.builder()
                              .x(Integer.parseInt(mowerCoordinate[0]))
                              .y(Integer.parseInt(mowerCoordinate[1]))
                              .orientation(mowerCoordinate[2])
                              .build();
    }

    private static MowerGame buildGame(GameSurface gameSurface, String[] mowerInstructions, MowerCoordinate mowerCoordinate) {
        return MowerGame.builder()
                        .gameSurface(gameSurface)
                        .mowerCoordinate(mowerCoordinate)
                        .gameInstructions(List.of(mowerInstructions))
                        .build()
                        .applyInstructions();
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
        return instruction;
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
        int surfaceRow = 0;
        String[] surface = gameLogic.get(surfaceRow).split(" ");
        if (surface.length != 2) throw new GameValidatorException(WRONG_GAME_SURFACE_DEFINITION);
        int x = Integer.parseInt(surface[0]);
        int y = Integer.parseInt(surface[1]);
        return new int[]{x, y};
    }

}
