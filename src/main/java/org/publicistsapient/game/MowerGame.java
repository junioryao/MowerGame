package org.publicistsapient.game;

import lombok.Builder;
import org.publicistsapient.constant.Compass;
import org.publicistsapient.constant.Instruction;

import java.util.List;
import java.util.logging.Logger;

import static org.publicistsapient.constant.Instruction.D;
import static org.publicistsapient.constant.Instruction.G;

@Builder
public class MowerGame implements Game<MowerGame> {
    private static final Logger LOGGER = Logger.getLogger(MowerGame.class.getName());
    private GameSurface gameSurface;
    private List<Instruction> gameInstructions;
    private MowerCoordinate mowerCoordinate;

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
     */
    @Override
    public MowerGame applyInstructions() {
        int position = mowerCoordinate.getOrientation().getValue();
        for (Instruction instruction : gameInstructions) {
            if (G.equals(instruction)) {
                position = moveCompassToTheLeft(position);
            } else
                if (D.equals(instruction)) {
                    position = moveCompassToTheRight(position);
                } else {
                    moveMower(position);
                }
        }
        LOGGER.info("Game instruction performed successfully");
        return this;
    }

    private void moveMower(int position) {
        Compass compassOrientation = Compass.getFromValue(position);
        mowerCoordinate.setOrientation(compassOrientation);
        updateMowerCoordinate(compassOrientation);
    }

    private void updateMowerCoordinate(Compass compassOrientation) {
        switch (compassOrientation) {
            case N -> mowerCoordinate.moveUp(this);
            case E -> mowerCoordinate.moveRight(this);
            case W -> mowerCoordinate.moveLeft(this);
            case S -> mowerCoordinate.moveDown(this);
        }
    }

    public MowerCoordinate getMowerCoordinate() {
        return mowerCoordinate;
    }

    public GameSurface getGameSurface() {
        return gameSurface;
    }

}
