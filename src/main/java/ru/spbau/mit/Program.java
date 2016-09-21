package ru.spbau.mit;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


/**
 * The interface describes the methods that need to implement
 * all the program-classes that are executed in the shell
 */
public interface Program {
    String getName();
    void execute(List<String> arguments, InputStream input, OutputStream output);
}
