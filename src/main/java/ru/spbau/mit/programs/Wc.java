package ru.spbau.mit.programs;

import ru.spbau.mit.Program;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Program wc
 */
public class Wc implements Program {
    @Override
    public String getName() {
        return "wc";
    }

    @Override
    public void execute(List<String> arguments, InputStream input, OutputStream output) {
        PrintStream currentOutput = new PrintStream(output);
        int nLinesAll = 0, nWordsAll = 0, nBytesAll = 0;

        if (arguments.size() != 0) {

            for (String arg : arguments) {
                try (Scanner scanner = new Scanner(new File(arg), "UTF-8")) {
                    int[] array = calcFromScanner(scanner);

                    nLinesAll += array[0];
                    nWordsAll += array[1];
                    nBytesAll += array[2];

                    currentOutput.format("%d %d %d %s\n", array[0], array[1], array[2], arg);

                } catch (FileNotFoundException e) {
                    currentOutput.format("wc: File %s is not found.\n", arg);
                }
            }

            if (arguments.size() > 1) {
                currentOutput.format("%d %d %d all\n", nLinesAll, nWordsAll, nBytesAll);
            }

        } else if (input != null) {
            int[] array = calcFromScanner(new Scanner(input));
            currentOutput.format("%d %d %d\n", array[0], array[1], array[2]);
        }
    }

    private int[] calcFromScanner(Scanner scanner) {
        int[] array = new int[3];

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            array[0] += 1; //lines
            array[1] += line.split("\\s+").length; //words
            array[2] += line.getBytes().length; //bytes
        }

        return array;
    }
}
