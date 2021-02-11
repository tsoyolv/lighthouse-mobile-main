package ru.lighthouse.mobile.main.unittest;

import org.junit.jupiter.api.Test;
import ru.lighthouse.mobile.main.core.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileUtilsTest {

    @Test
    public void test_splitFileByLineNumbers() throws IOException {
        Path path = Paths.get("src/test/resources/file/test-split-file.txt");
        FileUtils.splitFileByLineNumbers(path, 5);

        path = Paths.get("src/test/resources/file/test-split-file1.txt");
        assertThat(Files.lines(path).count(), is(equalTo(5L)));
        Files.delete(path);

        path = Paths.get("src/test/resources/file/test-split-file2.txt");
        assertThat(Files.lines(path).count(), is(equalTo(5L)));
        Files.delete(path);

        path = Paths.get("src/test/resources/file/test-split-file3.txt");
        assertThat(Files.lines(path).count(), is(equalTo(5L)));
        Files.delete(path);

        path = Paths.get("src/test/resources/file/test-split-file4.txt");
        assertThat(Files.lines(path).count(), is(equalTo(2L)));
        Files.delete(path);
    }
}
