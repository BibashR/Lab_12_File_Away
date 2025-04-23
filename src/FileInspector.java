import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("src"));
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            System.out.println("Reading file: " + selectedFile.getName());

            int lineCount = 0, wordCount = 0, charCount = 0;

            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath());
                for (String line : lines) {
                    System.out.println(line);
                    lineCount++;
                    wordCount += line.trim().isEmpty() ? 0 : line.trim().split("\\s+").length;
                    charCount += line.length();
                }

                System.out.println("\n--- File Summary ---");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Lines: " + lineCount);
                System.out.println("Words: " + wordCount);
                System.out.println("Characters: " + charCount);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}
