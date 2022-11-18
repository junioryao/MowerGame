package org.publicistsapient;

import org.publicistsapient.fileprocessor.FileProcessor;
import org.publicistsapient.game.Game;
import org.publicistsapient.gamelogic.LogicValidator;
import org.publicistsapient.gamelogic.MowerGameValidator;

import java.io.FileNotFoundException;
import java.util.List;

import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_TXT;

/**
 * @author Junior Yao -> junioryao.jy@gmail.com
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = overrideDefaultGamePattern(args);
        LogicValidator mowerGameValidator = new MowerGameValidator(new FileProcessor(path));
        List<? extends Game> mowerGames = mowerGameValidator.execute();
        mowerGames.forEach(mowerGame -> System.out.println(mowerGame.getMowerCoordinate().toString()));
    }

    private static String overrideDefaultGamePattern(String[] args) {
        String path = GAME_INPUT_PATTERN_TXT;
        if (args.length > 0) {
            path = args[0];
        }
        return path;
    }

}
