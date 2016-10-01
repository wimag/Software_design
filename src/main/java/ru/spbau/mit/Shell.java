package ru.spbau.mit;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import ru.spbau.mit.parser.ShellLexer;
import ru.spbau.mit.parser.ShellParser;

import java.io.*;
import java.util.*;

/**
 * The Shell
 */
public class Shell {
    private HashMap<String, Program> commands = new HashMap<>();
    private HashMap<String, String> environment = new HashMap<>();

    public Shell() {
        Program exit = new Program() {
            @Override
            public String getName() {
                return "exit";
            }

            @Override
            public void execute(List<String> arguments, InputStream input, OutputStream output) {
                System.exit(0);
            }
        };

        addCommand(exit);
    }

    /**
     * Run the shell
     * @param input - input of shell
     * @param output - output of shell
     */
    public void run(InputStream input, OutputStream output) {
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            try {
                processLine(scanner.nextLine(), output);
            } catch (Exception e) {
                new PrintStream(output).println("Error - " + e.getMessage());
            }
        }
    }

    /**
     * Add program to shell
     * @param program - object of Program
     */
    public void addCommand(Program program) {
        commands.put(program.getName(), program);
    }

    private void addVariable(String name, String value) {
        environment.put(name, value);
    }

    private void execExternProc(Command cmd, InputStream input, OutputStream output) throws IOException {
        List<String> args = new LinkedList<>(cmd.getArguments());
        args.add(0, cmd.getName());

        Process proc = new ProcessBuilder(args).start();

        Scanner scanner;
        if (input != null) {
            PrintStream procInput = new PrintStream(proc.getOutputStream());
            scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                procInput.println(scanner.nextLine());
            }
        }

        PrintStream currentOutput = new PrintStream(output);
        scanner = new Scanner(proc.getInputStream());
        while (scanner.hasNextLine()) {
            currentOutput.println(scanner.nextLine());
        }
    }

    private void execCommand(Command cmd, InputStream input, OutputStream output) throws IOException {
        Command.Type typeCmd = cmd.getType();

        if (typeCmd == Command.Type.PROG) {
            Program prog = commands.get(cmd.getName());

            if (prog != null) {
                prog.execute(cmd.getArguments(), input, output);
            } else {
                execExternProc(cmd, input, output);
            }

        } else if (typeCmd == Command.Type.VAR) {
            addVariable(cmd.getName(), cmd.getArguments().get(0));
        }
    }

    private List<Command> getCommands(String line) {
        CharStream stream = new ANTLRInputStream(line);
        ShellLexer lexer = new ShellLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShellParser parser = new ShellParser(tokens);

        parser.setEnv(environment);

        return parser.start().list;
    }

    private void processLine(String line, OutputStream resultOutput) throws IOException {
        List<Command> cmdList = getCommands(line);
        if (cmdList == null) {
            return;
        }

        InputStream input = null;
        OutputStream output = null;
        PipedInputStream nextInput = null;

        for (int i = 0; i < cmdList.size(); i++) {
            if (output != null) {
                output.close();
            }

            if (i == cmdList.size() - 1) {
                output = resultOutput;
            } else  {
                nextInput = new PipedInputStream();
                output = new PipedOutputStream(nextInput);
            }

            Command curCmd = cmdList.get(i);
            execCommand(curCmd, input, output);

            input = nextInput;
        }
    }
}
