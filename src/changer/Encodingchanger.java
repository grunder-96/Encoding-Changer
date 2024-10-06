package changer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Encodingchanger {

    private static final String BASE_ENCODING = "windows-1251";
    private static final String TARGET_ENCODING = StandardCharsets.UTF_8.name();

    private static final String TEMP_FILE_NAME_PREFIX = "temp_";

    private static final int BUFFER_SIZE = 256;

    public static void changeEncoding(Path path) {
        Path tempPath = createTempPath(path);
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(path.toFile()), BASE_ENCODING);
             OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempPath.toFile()), TARGET_ENCODING)) {
            char[] buffer = new char[BUFFER_SIZE];
            while (reader.ready()) {
                int count = reader.read(buffer);
                writer.write(buffer, 0, count);
            }
            Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createTempPath(Path path) {
        String fileName = path.getFileName().toString();
        String tempFileName = TEMP_FILE_NAME_PREFIX + fileName;
        String tempFilePathString = path.toString().replace(fileName, tempFileName);
        Path tempPath = Path.of(tempFilePathString);
        try {
            Files.createFile(tempPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tempPath;
    }
}