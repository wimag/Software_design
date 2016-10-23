package ru.spbau.mit.programs;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import ru.spbau.mit.Program;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Program grep
 */
public class Grep implements Program {
    private OptionParser optionParser = new OptionParser();

    public Grep() {
        optionParser.accepts("i");
        optionParser.accepts("w");
        optionParser.accepts("A").withRequiredArg().ofType(Integer.class);
    }

    @Override
    public String getName() {
        return "grep";
    }

    private static class Options {
        private int nOptions = 0;
        private int nPrintLines = 0;
        private boolean ignoreCase = false;
        private boolean onlyWords = false;
    }

    private Options getOptions(List<String> arguments) {
        Options opt = new Options();
        String[] argsArray = new String[arguments.size()];
        OptionSet options = optionParser.parse(arguments.toArray(argsArray));

        if (options.has("i")) {
            opt.ignoreCase = true;
            opt.nOptions++;
        }
        if (options.has("w")) {
            opt.onlyWords = true;
            opt.nOptions++;
        }
        if (options.has("A")) {
            opt.nPrintLines = (Integer) options.valueOf("A");
            opt.nOptions += 2;
        }

        return opt;
    }

    private Pattern getPattern(String regexpr, Options opt) {
        String curRegexpr = regexpr;

        if (opt.onlyWords) {
            curRegexpr = "\\b" + curRegexpr + "\\b";
        }

        if (opt.ignoreCase) {
            return Pattern.compile(curRegexpr, Pattern.CASE_INSENSITIVE);
        }

        return Pattern.compile(curRegexpr);
    }

    private void process(Pattern pattern, int nPrintLines, Scanner input, PrintStream output) {
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        for (int i = 0; i < lines.size(); i++) {
            Matcher matcher = pattern.matcher(lines.get(i));

            if (matcher.find()) {
                for (int j = i; j <= i + nPrintLines && j < lines.size(); j++) {
                    output.println(lines.get(j));
                }
            }
        }
    }

    @Override
    public void execute(List<String> arguments, InputStream input, OutputStream output) {
        PrintStream curOutput = new PrintStream(output);
        Options opt = getOptions(arguments);

        if (arguments.size() <= opt.nOptions) {
            System.out.println("Can not find pattern");
            return;
        }

        String regexpr = arguments.get(opt.nOptions);
        Pattern pattern = getPattern(regexpr, opt);

        if (arguments.size() > opt.nOptions + 1) {
            for (int i = opt.nOptions + 1; i < arguments.size(); i++) {
                try (Scanner scanner = new Scanner(new File(arguments.get(i)))) {
                    if (arguments.size() - opt.nOptions - 1 > 1) {
                        curOutput.println(arguments.get(i));
                    }
                    process(pattern, opt.nPrintLines, scanner, curOutput);

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    curOutput.format("grep: File %s is not found.\n", arguments.get(i));
                }
            }

        } else if (input != null) {
            process(pattern, opt.nPrintLines, new Scanner(input), curOutput);

        } else {
            curOutput.println("");
        }
    }

}
