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

    private Type type;
    private String name;
    private List<String> arguments;

    public Command(Type type, String name, List<String> arguments) {
        this.type = type;
        this.name = name;
        this.arguments = arguments;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
