package nz.co.jcasey.sortednames;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import nz.co.jcasey.sortednames.controller.SortedNamesFileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SortedNamesApplicationTests {
	@Autowired
	private SortedNamesFileController sortedNamesFileController;

	@Test
	public void testFileSortHappyPath() throws IOException {
		final List<String> testData = List.of("BAKER, THEODORE","SMITH, ANDREW","KENT, MADISON","SMITH, FREDRICK");
		Files.write(Paths.get("test.txt"), testData);

		sortedNamesFileController.run("test.txt");

		List<String> output = Files.readAllLines(Paths.get("test-sorted.txt"));

		assertEquals("Number of lines does not match",4,output.size());
	}

	@Test
	public void testFileSortHappyPathLargeFile() throws IOException {
		sortedNamesFileController.run("large.txt");

		List<String> sample = Files.readAllLines(Paths.get("large-sorted-sample-output.txt"));

		List<String> output = Files.readAllLines(Paths.get("large-sorted.txt"));

		assertEquals("Number of lines does not match",sample.size(),output.size());

		for(int i = 0; i<sample.size();i++) {
			assertEquals("Output does not match sample file",sample.get(i), output.get(i));
		}
	}

	@Test
	public void testFileSortNoParams() throws IOException {
		Exception exception = assertThrows(Exception.class, () -> sortedNamesFileController.run());
		assertEquals("Error message does not match","File not specified.",exception.getMessage());
	}

	@Test
	public void testFileDoesNotExist() throws IOException {
		Exception exception = assertThrows(Exception.class, () -> sortedNamesFileController.run("random.txt"));
		assertEquals("Error message does not match","java.nio.file.NoSuchFileException: random.txt",exception.getMessage());
	}

}
