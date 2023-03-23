package be.kdg.tic_tac_toe.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SaveFiles {
     static void checkFile(Path file) throws SaveFileException {
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
                System.out.println("File created: " + file.getFileName());
            } catch (IOException e) {
                throw new SaveFileException("File could not be created: " + file.getFileName());
            }
        } else {
            System.out.println("File already exists: " + file.getFileName());
        }
    }

    public static String getSubString(String line, int index) {
         return line.split(";")[index].split(":")[1];
    }
}
