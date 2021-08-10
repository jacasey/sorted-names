package nz.co.jcasey.sortednames.mapper;

import java.util.function.Function;
import nz.co.jcasey.sortednames.model.Person;

@FunctionalInterface
public interface Mapper {
  Function<String, Person> mapper();
}
