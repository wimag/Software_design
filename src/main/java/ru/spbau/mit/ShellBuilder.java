package ru.spbau.mit;

import java.util.Collection;

/**
 * The class designed for building objects of class Shell
 */
public final class ShellBuilder {
    private Shell shell = null;

    /**
     * Add a program
     * @param program - instance of Program interface
     * @return current object ShellBuilder
     */
    public ShellBuilder add(Program program) {
        if (shell == null) {
            shell = new Shell();
        }
        shell.addCommand(program);

        return this;
    }

    /**
     * Add programs from Collection
     * @param programs - Collection with programs
     * @return current object ShellBuilder
     */
    public ShellBuilder addAll(Collection<? extends Program> programs) {
        for (Program prog : programs) {
            add(prog);
        }

        return this;
    }

    /**
     * Convert ShellBuilder to Shell
     * @return Shell object
     */
    public Shell toShell() {
        return shell;
    }
}
