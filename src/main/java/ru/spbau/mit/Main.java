package ru.spbau.mit;


import ru.spbau.mit.programs.*;


public class Main {
    public static void main(String[] args) {
        Shell shell = new Shell.ShellBuilder()
                .add(new Cat())
                .add(new Echo())
                .add(new Pwd())
                .add(new Wc())
                .add(new Grep())
                .toShell();

        shell.run(System.in, System.out);
    }
}
