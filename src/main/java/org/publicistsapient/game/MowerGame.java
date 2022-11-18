package org.publicistsapient.game;

import lombok.Builder;

import java.util.List;
import java.util.logging.Logger;

import static org.publicistsapient.constant.Property.*;

@Builder
public class MowerGame implements Game<MowerGame> {
    private final static Logger LOGGER = Logger.getLogger(MowerGame.class.getName());
    private GameSurface gameSurface;
    private MowerCoordinate mowerCoordinate;
    private List<String> gameInstructions;

    private static int moveCompassToTheRight(int position) {
        position += 1;
        if (position > 3) position = 0;
        return position;
    }

    private static int moveCompassToTheLeft(int position) {
        position -= 1;
        if (position < 0) position = 3;
        return position;
    }

    /**
     * move the compass clockwise or anti-clockwise based on (G,D) instruction (left <-> right)
     *
     * @return
     */
    @Override
    public MowerGame applyInstructions() {
        int position = compassIndex.get(mowerCoordinate.getOrientation());
        for (String instruction : gameInstructions) {
            if (instruction.equals("G")) {
                position = moveCompassToTheLeft(position);
            } else
                if (instruction.equals("D")) {
                    position = moveCompassToTheRight(position);
                } else {
                    moveMower(position);
                }
        }
        LOGGER.info("Game instruction performed successfully");
        return this;
    }

    private void moveMower(int position) {
        String orientationMoved = compass[position];
        mowerCoordinate.setOrientation(orientationMoved);
        updateMowerCoordinate(compassMovingCoordinate.get(orientationMoved));
    }


    /**
     * @param direction
     * @implNote perform mower movement based on compass orientation
     */
    private void updateMowerCoordinate(Direction direction) {
        switch (direction) {
            case UP -> mowerCoordinate.moveUp(this);
            case RIGHT -> mowerCoordinate.moveRight(this);
            case LEFT -> mowerCoordinate.moveLeft(this);
            case DOWN -> mowerCoordinate.moveDown(this);
        }
    }

    public MowerCoordinate getMowerCoordinate() {
        return mowerCoordinate;
    }

    public GameSurface getGameSurface() {
        return gameSurface;
    }

}
