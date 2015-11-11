package com.stma.yamllint.plugin;

import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.FileSystems;
import org.yaml.snakeyaml.Yaml;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.FileVisitResult;
import java.io.IOException;

public class YamlFilesChecker extends SimpleFileVisitor<Path> {

    private final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.yaml");

    void check(Path file) throws IOException {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            Yaml yaml = new Yaml();
            Object obj = yaml.load(Files.newInputStream(file));
            assert obj != null : String.format("Yaml parser failed during parsing %s.", file.toString());
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        try {
            check(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CONTINUE;
    }

}