package nz.co.jcasey.sortednames.mapper;

import java.util.function.Function;
import java.util.logging.Logger;
import nz.co.jcasey.sortednames.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements Mapper {

  @Override
  public Function<String, Person> mapper() {
    return line -> {


      String[] name = line.split(",");

      if(name.length == 2) {
        String lastname = name[0];
        String firstname = name[1];
        return new Person(firstname.trim(), lastname.trim());
      }
      else if(name.length == 1) {
        // some customers only have one name
        return new Person("", name[0].trim());
      }

      // return null if we have some bad data
      return null;
    };
  }
}
