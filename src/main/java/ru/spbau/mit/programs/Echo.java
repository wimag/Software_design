package ru.spbau.mit.programs;


import ru.spbau.mit.Program;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;


/**
 * Program echo
 */
public class Echo implements Program {
    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public void execute(List<String> arguments, InputStream input, OutputStream output) {
        PrintStream currentOutput = new PrintStream(output);

        for (int i = 0; i < arguments.size(); i++) {
            currentOutput.print(arguments.get(i));
            if (i != arguments.size() - 1)
                currentOutput.print(' ');
        }
        currentOutput.print('\n');
    }
}
