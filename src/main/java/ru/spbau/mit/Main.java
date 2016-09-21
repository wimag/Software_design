package ru.spbau.mit;


import ru.spbau.mit.programs.Cat;
import ru.spbau.mit.programs.Echo;
import ru.spbau.mit.programs.Pwd;
import ru.spbau.mit.programs.Wc;


public class Main {
    public static void main(String[] args) {
        Shell shell = new Shell.ShellBuilder()
                .add(new Cat())
                .add(new Echo())
                .add(new Pwd())
                .add(new Wc())
                .toShell();

        shell.run(System.in, System.out);
    }
}
