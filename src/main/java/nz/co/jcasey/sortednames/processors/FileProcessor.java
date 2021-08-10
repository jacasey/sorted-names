package nz.co.jcasey.sortednames.processors;

import java.util.Comparator;
import java.util.stream.Stream;
import nz.co.jcasey.sortednames.mapper.Mapper;
import nz.co.jcasey.sortednames.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor implements Processor {

  @Autowired
  Mapper mapper;

  @Override
  public Stream<Person> process(Stream<String> lines) {
    return lines
        .map(mapper.mapper())
        .sorted(Comparator.comparing(Person::getLastname)
            .thenComparing(Person::getFirstname));
  }
}
