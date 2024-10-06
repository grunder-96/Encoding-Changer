package changer;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class CustomFileVisitor extends SimpleFileVisitor<Path> {

    private static final String NECESSARY_EXTENSION = ".txt";

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        if (isNecessaryExtension(path.getFileName().toString())) {
            Encodingchanger.changeEncoding(path);
        }
        return FileVisitResult.CONTINUE;
    }

    private boolean isNecessaryExtension(String fileName) {
        if (Objects.isNull(fileName)) {
            return false;
        }
        return fileName.endsWith(NECESSARY_EXTENSION);
    }
}