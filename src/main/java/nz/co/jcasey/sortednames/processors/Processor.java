package nz.co.jcasey.sortednames.processors;

import java.util.stream.Stream;
import nz.co.jcasey.sortednames.model.Person;

@FunctionalInterface
public interface Processor {
  Stream<Person> process(Stream<String> lines);
}
