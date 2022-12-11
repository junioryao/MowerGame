package org.publicistsapient.game;

import java.util.List;

public enum Instruction {
    G {
        @Override
        public Integer moveCompassOrientation(Integer position) {
            position -= 1;
            if (position < 0) position = 3;
            return position;
        }
    }, D {
        @Override
        public Integer moveCompassOrientation(Integer position) {
            position += 1;
            if (position > 3) position = 0;
            return position;
        }
    }, A {
        @Override
        public Integer moveCompassOrientation(Integer position) {
            return null;
        }
    };

    public static List<Instruction> getInstructions(List<String> directions) {
        return directions.stream().map(Instruction::valueOf).toList();
    }

    public abstract Integer moveCompassOrientation(Integer position);
}
