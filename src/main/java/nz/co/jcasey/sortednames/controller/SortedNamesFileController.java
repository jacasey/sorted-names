package nz.co.jcasey.sortednames.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nz.co.jcasey.sortednames.model.Person;
import nz.co.jcasey.sortednames.processors.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SortedNamesFileController {
  public final static Logger logger = Logger.getLogger(SortedNamesFileController.class.getName());

  @Autowired
  Processor processor;

  public void run(String... args) {
    if(args.length != 1) {
      throw new RuntimeException("File not specified.");
    }
    else {
      String filename = args[0];

      int extIndex = filename.indexOf(".txt");

      if(extIndex == -1) {
        throw new RuntimeException("File error. Not a text file.");
      }

      String prefix = filename.substring(0,extIndex);
      String outputFilename = prefix + "-sorted.txt";

      try (Stream<String> lines = Files.lines(Paths.get(filename))) {
        Stream<Person> output = processor.process(lines);

        Files.write(Paths.get("./"+outputFilename),output.map(
            person -> {
              logger.log(Level.INFO,person.toString());
              return person.toString();
            }
        ).collect(Collectors.toList()));

        logger.log(Level.INFO, "Finished: created " + outputFilename);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
