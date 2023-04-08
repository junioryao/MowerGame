package org.publicistsapient;

import org.publicistsapient.fileprocessor.FileProcessor;
import org.publicistsapient.game.Game;
import org.publicistsapient.game.MowerGame;
import org.publicistsapient.game.MowerGameBuilder;
import org.publicistsapient.game.MowerPosition;
import org.publicistsapient.gamelogic.MowerGameValidator;
import org.publicistsapient.gamelogic.Validator;

import java.io.FileNotFoundException;
import java.util.List;

import static org.publicistsapient.constant.Property.GAME_INPUT_PATTERN_TXT;

/**
 * @author Junior Yao -> junioryao.jy@gmail.com
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = overrideDefaultGamePattern(args);
        Validator<MowerGame> mowerGameValidator = new MowerGameValidator(new FileProcessor(path), new MowerGameBuilder());
        List<MowerGame> mowerGames = mowerGameValidator.execute();
        mowerGames.stream()
                .map(Game::getMowerPosition)
                .map(MowerPosition::toString)
                .forEach(System.out::println);
    }

    private static String overrideDefaultGamePattern(String[] args) {
        String path = GAME_INPUT_PATTERN_TXT;
        if (args.length > 0) {
            path = args[0];
        }
        return path;
    }
}
