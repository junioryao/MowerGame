package org.publicistsapient.gamelogic;


import java.io.FileNotFoundException;
import java.util.List;

public interface Validator<T> {
    List<T> execute() throws FileNotFoundException;
}
