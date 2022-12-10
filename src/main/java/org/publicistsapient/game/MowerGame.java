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
    private List<Instruction> gameInstructions;
    private MowerPosition mowerPosition;

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
     */
    @Override
    public MowerGame applyInstructions() {
        int position = mowerPosition.getOrientation().getValue();
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
        mowerPosition.move(compassOrientation);
    }

    public MowerPosition getMowerPosition() {
        return mowerPosition;
    }
}
