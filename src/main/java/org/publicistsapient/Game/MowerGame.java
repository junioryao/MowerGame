package org.publicistsapient.Game;

import lombok.Builder;

import java.util.logging.Logger;

import static org.publicistsapient.constant.Property.compassIndex;
import static org.publicistsapient.constant.Property.compassMovingCoordinate;

@Builder
public class MowerGame implements Game<MowerGame> {
    private MowerBaseCoordinate mowerBaseCoordinate;
    private String[] mowerBaseInstruction;
    public GameSurface gameSurface;
    private static final String[] compass = new String[]{"N", "E", "S", "W"};
    private final static Logger LOGGER = Logger.getLogger(MowerGame.class.getName());

    /**
     * move the compass clockwise or anti-clockwise based on (G,D) instruction (left <-> right)
     *
     * @return
     */
    @Override
    public MowerGame applyInstruction() {
        int position = compassIndex.get(mowerBaseCoordinate.getOrientation());
        for (int i = 0; i < mowerBaseInstruction.length; i++) {
            if (mowerBaseInstruction[i].equals("G")) {
                position = moveCompassToTheLeft(position);
            } else
                if (mowerBaseInstruction[i].equals("D")) {
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

}
