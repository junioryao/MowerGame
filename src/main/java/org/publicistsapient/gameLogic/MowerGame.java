package org.publicistsapient.gameLogic;

import java.util.Map;
import java.util.logging.Logger;

public class MowerGame implements Game {
    private String orientation;
    private int[] mowerBaseCoordinate;
    private String[] mowerBaseInstruction;
    private int[] gameSurface;
    private static final String[] compass = new String[]{"N", "E", "S", "W"};
    private Map<String, String> compassMovingCoordinate = Map.of("N", "up", "E", "right", "S", "down", "W", "left");
    private Map<String, Integer> compassIndex = Map.of("N", 0, "E", 1, "S", 2, "W", 3);

    private final static Logger LOGGER = Logger.getLogger(MowerGame.class.getName());


    public MowerGame(String orientation, int[] mowerBaseCoordinate, String[] mowerBaseInstruction, int[] gameSurface) {
        this.orientation = orientation;
        this.mowerBaseCoordinate = mowerBaseCoordinate;
        this.mowerBaseInstruction = mowerBaseInstruction;
        this.gameSurface = gameSurface;
    }

    /**
     * move the compass clockwise or anti-clockwise based on (G,D) instruction
     */
    @Override
    public void applyInstruction() {
        int position = compassIndex.get(orientation);
        for (int i = 0; i < mowerBaseInstruction.length; i++) {
            if (mowerBaseInstruction[i].equals("G") || mowerBaseInstruction[i].equals("D")) {
                if (mowerBaseInstruction[i].equals("G")) {
                    position -= 1;
                    if (position < 0) position = 3;

                } else {
                    position += 1;
                    if (position > 3) position = 0;
                }
            } else {
                String orientationMove = compass[position];
                orientation = orientationMove;
                updateMowerCoordinate(compassMovingCoordinate.get(orientationMove));
            }
        }
        LOGGER.info("Game instruction performed successfully");
    }

    /**
     * @param s
     * @implNote perform mower movement based on compass orientation
     */
    private void updateMowerCoordinate(String s) {
        switch (s) {
            case "up":
                moveUp();
                break;
            case "right":
                moveRight();
                break;
            case "left":
                moveLeft();
                break;
            case "down":
                moveDown();
                break;
        }
    }


    private void moveUp() {
        if (mowerBaseCoordinate[0] <= gameSurface[0] && mowerBaseCoordinate[1] + 1 <= gameSurface[1]) {
            mowerBaseCoordinate[1] = mowerBaseCoordinate[1] + 1;
        }
    }

    private void moveDown() {
        if (mowerBaseCoordinate[0] <= gameSurface[0] && mowerBaseCoordinate[1] - 1 <= gameSurface[1]) {
            mowerBaseCoordinate[1] = mowerBaseCoordinate[1] - 1;
        }
    }

    private void moveLeft() {
        if (mowerBaseCoordinate[0] - 1 <= gameSurface[0] && mowerBaseCoordinate[1] <= gameSurface[1]) {
            mowerBaseCoordinate[0] = mowerBaseCoordinate[0] - 1;
        }
    }

    private void moveRight() {
        if (mowerBaseCoordinate[0] + 1 <= gameSurface[0] && mowerBaseCoordinate[1] <= gameSurface[1]) {
            mowerBaseCoordinate[0] = mowerBaseCoordinate[0] + 1;
        }
    }

    public String[] getMowerBaseCoordinate() {
        return new String[]{String.valueOf(mowerBaseCoordinate[0]), String.valueOf(mowerBaseCoordinate[1]),
            orientation};
    }

}
