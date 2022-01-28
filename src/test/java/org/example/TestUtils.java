package org.example;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class TestUtils {
    private TestUtils(){}

    public static InputStream fromResources(String name) throws IOException {
        return Files.newInputStream(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + name).toPath());
    }
}
