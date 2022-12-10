package org.publicistsapient.constant;

import java.util.List;

public enum Instruction {
    G, D, A;

    public static List<Instruction> getInstructions(List<String> directions) {
        return directions.stream().map(Instruction::valueOf).toList();
    }
}
