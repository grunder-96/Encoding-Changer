package changer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class TreeWalker {

    private final Path sourcePath;

    public TreeWalker(Path sourcePath) {
        this.sourcePath = Objects.requireNonNull(sourcePath);
    }

    public void walk() {
        try {
            Files.walkFileTree(sourcePath, new CustomFileVisitor());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}