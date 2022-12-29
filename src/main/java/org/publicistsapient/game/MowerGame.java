package org.publicistsapient.game;

import lombok.Builder;

import java.util.List;
import java.util.logging.Logger;

import static org.publicistsapient.game.Instruction.A;

@Builder
public class MowerGame implements Game<MowerGame> {
    private static final Logger LOGGER = Logger.getLogger(MowerGame.class.getName());
    private List<Instruction> gameInstructions;
    private MowerPosition mowerPosition;

    /**
     * move the compass clockwise or anti-clockwise based on (G,D) instruction (left <-> right)
     */
    @Override
    public MowerGame applyInstructions() {
        Integer position = mowerPosition.getOrientation().getValue();
        for (Instruction instruction : gameInstructions) {
            if (!A.equals(instruction)) {
                position = instruction.moveCompassOrientation(position);
            } else {
                moveMower(position);
            }
        }
        LOGGER.info("Game instruction performed successfully");
        return this;
    }

    private void moveMower(int position) {
        Compass compassOrientation = Compass.getFromValue(position);
        mowerPosition.setOrientation(compassOrientation);
        mowerPosition.execute();
    }

    public MowerPosition getMowerPosition() {
        return mowerPosition;
    }
}
