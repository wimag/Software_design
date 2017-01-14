package ru.spbau.mit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.spbau.mit.programs.*;

import java.io.*;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShellTest {
    final Shell shell = new ShellBuilder()
                        .add(new Cat())
                        .add(new Echo())
                        .add(new Pwd())
                        .add(new Wc())
                        .add(new Grep())
                        .add(new Ls())
                        .add(new Cd())
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

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

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

    @Test
    public void grepTest() throws IOException {
        final String text = "This is temp file\n" +
                "4 8 15 15 23 42\n" +
                "sdfdsf sdfsdf ssdfs\n" +
                "sdffsdewrwe rtyrt  rtyr rtywr w";

        File tempFile = File.createTempFile("testFile", ".txt");
        String filePath = tempFile.getPath();
        PrintStream fileStream = new PrintStream(tempFile);
        fileStream.println(text);
        fileStream.close();

        final String cmd1 = "grep \"temp\" " + filePath;
        assertEquals("This is temp file", runShell(cmd1));

        final String cmd2 = "grep \"temp$\" " + filePath;
        assertEquals("", runShell(cmd2));

        final String cmd3 = "grep -i \"^this\" " + filePath;
        assertEquals("This is temp file", runShell(cmd3));

        final String cmd4 = "grep -w \"Thi\" " + filePath;
        assertEquals("", runShell(cmd4));

        final String cmd5 = "grep -A 3 \"Thi\" " + filePath;
        assertEquals(text, runShell(cmd5));

        final String cmd6 = "cat pom.xml | grep build";
        final String resultCmd6 = "    <build>\n" +
                                  "    </build>";
        assertEquals(resultCmd6, runShell(cmd6));
    }

    @Test
    public void lsTest() throws IOException {
        String[] folders = {"folder1", "folder2", "folder3"};
        String[] files = {"file1", "file2", "file3"};
        for (String folder: folders){
            tmpFolder.newFolder(folder);
        }
        for (String file: files){
            tmpFolder.newFile(file);
        }

        String result = runShell("ls " + tmpFolder.getRoot());

        for (String folder: folders){
            assertTrue(result.contains(folder + "/"));
        }
        for (String file: files){
            assertTrue(result.contains(file));
        }
    }

    @Test
    public void cdTest() throws IOException {
        String[] folders = {"folder1", "folder2", "folder3"};
        for (String folder: folders){
            tmpFolder.newFolder(folder);
        }

        runShell("cd " + tmpFolder.getRoot().getAbsolutePath());
        assertEquals(tmpFolder.getRoot().getAbsolutePath(), runShell("pwd").trim());

        runShell("cd " + folders[0]);
        assertEquals(Paths.get(tmpFolder.getRoot().getAbsolutePath(), folders[0]).toString(), runShell("pwd").trim());
    }
}