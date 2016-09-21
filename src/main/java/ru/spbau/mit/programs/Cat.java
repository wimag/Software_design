package ru.spbau.mit.programs;


import ru.spbau.mit.Program;
import java.io.*;
import java.util.List;
import java.util.Scanner;


/**
 * Program cat
 */
public class Cat implements Program {
    @Override
    public String getName() {
        return "cat";
    }

    @Override
    public void execute(List<String> arguments, InputStream input, OutputStream output) {
        PrintStream currentOutput = new PrintStream(output);

        if (arguments.size() != 0) {

            for (String arg : arguments) {

                try (Scanner scanner = new Scanner(new File(arg), "UTF-8")) {
                    printFromScanner(scanner, currentOutput);
                } catch (FileNotFoundException e) {
                    currentOutput.format("cat: File %s is not found.\n", arg);
                }
            }

        } else if (input != null) {
            printFromScanner(new Scanner(input), currentOutput);
        }
    }

    private void printFromScanner(Scanner scanner, PrintStream output) {
        while (scanner.hasNextLine()) {
            output.println(scanner.nextLine());
        }
    }
}
