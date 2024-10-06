package changer;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("/home/to/your/txt/catalogue");
        new TreeWalker(path).walk();
    }
}