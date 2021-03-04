package ru.lighthouse.mobile.main.unittest;

import org.junit.jupiter.api.Test;
import ru.lighthouse.mobile.main.core.FileUtils;
import ru.lighthouse.mobile.main.utils.TestResourcesUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileUtilsTest extends AbstractUnitTest {

    @Test
    public void test_splitFileByLineNumbers() throws IOException, URISyntaxException {
        Path path = TestResourcesUtils.convertResourcePath("file/test-split-file.txt");
        FileUtils.splitFileByLineNumbers(path, 5);

        path = TestResourcesUtils.convertResourcePath("file/test-split-file1.txt");
        assertThat(Files.lines(path).count(), is(equalTo(5L)));
        Files.delete(path);

        path = TestResourcesUtils.convertResourcePath("file/test-split-file2.txt");
        assertThat(Files.lines(path).count(), is(equalTo(5L)));
        Files.delete(path);

        path = TestResourcesUtils.convertResourcePath("file/test-split-file3.txt");
        assertThat(Files.lines(path).count(), is(equalTo(5L)));
        Files.delete(path);

        path = TestResourcesUtils.convertResourcePath("file/test-split-file4.txt");
        assertThat(Files.lines(path).count(), is(equalTo(2L)));
        Files.delete(path);
    }
}
