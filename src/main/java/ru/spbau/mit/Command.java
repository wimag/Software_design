package ru.spbau.mit;

import java.util.List;

/**
 * The class describes a command that need to execute in the shell
*/
public class Command {
    public enum Type {
        VAR, // set variable
        PROG // execute program
    }

    private final Type type;
    private final String name;
    private final List<String> arguments;

    /**
     * Constructor of Command
     * @param type - type of command
     * @param name - name of command
     * @param arguments - arguments of command
     */
    public Command(Type type, String name, List<String> arguments) {
        this.type = type;
        this.name = name;
        this.arguments = arguments;
    }

    /**
     * Get type of command
     * @return type of command
     */
    public Type getType() {
        return type;
    }

    /**
     * Get name of command
     * @return name of command
     */
    public String getName() {
        return name;
    }

    /**
     * Get arguments of command
     * @return arguments of command
     */
    public List<String> getArguments() {
        return arguments;
    }
}
