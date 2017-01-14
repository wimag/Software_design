package ru.spbau.mit;

import ru.spbau.mit.programs.*;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        Shell shell = new ShellBuilder()
                .add(new Cat())
                .add(new Echo())
                .add(new Pwd())
                .add(new Wc())
                .add(new Grep())
                .add(new Ls())
                .add(new Cd())
                .toShell();

        shell.run(System.in, System.out);
    }
}
