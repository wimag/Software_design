package ru.spbau.mit.programs;

import ru.spbau.mit.Program;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Program pwd
 */
public class Pwd implements Program {
    @Override
    public String getName() {
        return "pwd";
    }

    @Override
    public void execute(List<String> arguments, InputStream input, OutputStream output) {
        PrintStream currentOutput = new PrintStream(output);
        String currentDir = System.getProperty("user.dir");
        currentOutput.println(currentDir);
    }
}
