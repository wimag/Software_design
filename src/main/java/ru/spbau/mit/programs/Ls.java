package ru.spbau.mit.programs;

import ru.spbau.mit.Program;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Mark on 14.01.2017.
 */
public class Ls implements Program {
    @Override
    public String getName() {
        return "ls";
    }

    @Override
    public void execute(List<String> arguments, InputStream input, OutputStream output) {
        PrintStream currentOutput = new PrintStream(output);

        String currentFolder = System.getProperty("user.dir");
        File directory = new File(currentFolder);

        if (!arguments.isEmpty()) {
            String extraPath = arguments.get(0);
            if (isValidDirectory(currentFolder, extraPath)) {
                directory = Paths.get(currentFolder, extraPath).toFile();
            } else if (isValidDirectory(extraPath)) {
                directory = Paths.get(extraPath).toFile();
            } else {
                currentOutput.println("No such directory: " + extraPath);
                return;
            }
        }

        File files[] = directory.listFiles();
        if (files != null){
            for (File file: files){
                currentOutput.print(file.getName());
                if (file.isDirectory()){
                    currentOutput.print("/");
                }
                currentOutput.println();
            }
        } else {
            currentOutput.println("No such directory: " + directory.getAbsolutePath());
        }
    }

    private boolean isValidDirectory(String path, String... args){
        try {
            File file = Paths.get(path, args).toFile();
            return file.exists() && file.isDirectory();
        }catch (InvalidPathException |  NullPointerException ex) {
            return false;
        }
    }
}
