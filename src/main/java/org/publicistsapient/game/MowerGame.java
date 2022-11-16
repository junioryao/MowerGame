package org.publicistsapient.game;

import lombok.Builder;

import java.util.List;
import java.util.logging.Logger;

import static org.publicistsapient.constant.Property.*;

@Builder
public class MowerGame implements Game<MowerGame> {
    private GameSurface gameSurface;
    private MowerBaseCoordinate mowerBaseCoordinate;
    private List<String> mowerGameInstruction;
    private final static Logger LOGGER = Logger.getLogger(MowerGame.class.getName());

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
    public MowerGame applyInstruction() {
        int position = compassIndex.get(mowerBaseCoordinate.getOrientation());
        for (String instruction : mowerGameInstruction) {
            if (instruction.equals("G")) {
                position = moveCompassToTheLeft(position);
            } else
                if (instruction.equals("D")) {
                    position = moveCompassToTheRight(position);
                } else {moveMower(position);}
        }
        LOGGER.info("Game instruction performed successfully");
        return this;
    }

    private void moveMower(int position) {
        String orientationMoved = compass[position];
        mowerBaseCoordinate.setOrientation(orientationMoved);
        updateMowerCoordinate(compassMovingCoordinate.get(orientationMoved));
    }

    @Override
    public MowerBaseCoordinate getMowerBaseCoordinate() {
        return mowerBaseCoordinate;
    }

    /**
     * @param direction
     * @implNote perform mower movement based on compass orientation
     */
    private void updateMowerCoordinate(Direction direction) {
        switch (direction) {
            case UP -> mowerBaseCoordinate.moveUp(this);
            case RIGHT -> mowerBaseCoordinate.moveRight(this);
            case LEFT -> mowerBaseCoordinate.moveLeft(this);
            case DOWN -> mowerBaseCoordinate.moveDown(this);
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    public GameSurface getGameSurface() {
        return gameSurface;
    }

}
