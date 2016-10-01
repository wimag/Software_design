package ru.spbau.mit;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

import ru.spbau.mit.programs.Cat;
import ru.spbau.mit.programs.Echo;
import ru.spbau.mit.programs.Pwd;
import ru.spbau.mit.programs.Wc;


public class ShellTest {
    final Shell shell = new ShellBuilder()
                        .add(new Cat())
                        .add(new Echo())
                        .add(new Pwd())
                        .add(new Wc())
                        .toShell();

    private String runShell(String command) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(command.getBytes());
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();

        shell.run(inputStream, resultStream);
        String result = resultStream.toString();

        if (result.length() > 1) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    @Test
    public void echoTest() {
        final String cmd = "echo \"hello   world   !\" 481516      2342";
        final String result = "hello   world   ! 481516 2342";

        assertEquals(result, runShell(cmd));
    }

    @Test
    public void pwdTest() {
        final String cmd = "pwd";
        final String result = System.getProperty("user.dir");

        assertEquals(result, runShell(cmd));
    }

    @Test
    public void varTest() {
        final String cmd1 = "var1 = 1488";
        runShell(cmd1);
        final String cmd2 = "echo $var1";
        assertEquals("1488", runShell(cmd2));

        final String cmd3 = "var2 = \"yo $var1 knigga $var1\"";
        runShell(cmd3);
        final String cmd4 = "echo $var2";
        assertEquals("yo 1488 knigga 1488", runShell(cmd4));

        final String cmd5 = "var2 = \'yo $var1 knigga $var1\'";
        runShell(cmd5);
        final String cmd6 = "echo $var2";
        assertEquals("yo $var1 knigga $var1", runShell(cmd6));
    }

    @Test
    public void catTest() throws IOException {
        final String text1 = "This is temp file\n42";

        File tempFile = File.createTempFile("testFile", ".txt");
        String filePath = tempFile.getPath();
        PrintStream fileStream = new PrintStream(tempFile);
        fileStream.println(text1);
        fileStream.close();

        final String cmd1 = "cat " + filePath + "   " + filePath;
        assertEquals(text1 + "\n" + text1, runShell(cmd1));

        final String text2 = "hello";
        final String cmd2 = "echo " + text2 +" |   cat";
        assertEquals(text2, runShell(cmd2));
    }

    @Test
    public void wcTest() throws IOException {
        final String text1 = "This is temp file\n42";

        File tempFile = File.createTempFile("testFile", ".txt");
        String filePath = tempFile.getPath();
        PrintStream fileStream = new PrintStream(tempFile);
        fileStream.println(text1);
        fileStream.close();

        final String cmd1 = "wc " + filePath + " " + filePath;
        assertEquals("2 5 19 " + filePath +
                     "\n2 5 19 " + filePath +
                     "\n4 10 38 all", runShell(cmd1));

        final String text2 = "hello";
        final String cmd2 = "echo " + text2 +" | wc";
        assertEquals("1 1 5", runShell(cmd2));
    }
}