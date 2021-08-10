package nz.co.jcasey.sortednames;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nz.co.jcasey.sortednames.mapper.Mapper;
import nz.co.jcasey.sortednames.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonMapperTest {
  @Autowired
  Mapper mapper;

  @Test
  public void testHappyPath() {
    Stream<String> input = Stream.of("BAKER, THEODORE");

    List<Person> person = input
        .map(mapper.mapper())
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    assertEquals(person.size(),1);
    assertEquals("THEODORE", person.get(0).getFirstname());
    assertEquals("BAKER", person.get(0).getLastname());
  }

  @Test
  public void testNullFirstname() {
    Stream<String> input = Stream.of("BAKER,");

    List<Person> person = input
        .map(mapper.mapper())
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    assertEquals(person.size(),1);
    assertEquals("BAKER", person.get(0).getLastname());
    assertEquals("",person.get(0).getFirstname());
  }

  @Test
  public void testMissingLastname() {
    Stream<String> input = Stream.of(", THEODORE");

    List<Person> person = input
        .map(mapper.mapper())
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    assertEquals(person.size(),1);
    assertEquals("", person.get(0).getLastname());
    assertEquals("THEODORE",person.get(0).getFirstname());
  }

  @Test
  public void testBadSpacing() {
    Stream<String> input = Stream.of("BAKER  ,    THEODORE  ");

    List<Person> person = input
        .map(mapper.mapper())
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    assertEquals(person.size(),1);
    assertEquals("BAKER", person.get(0).getLastname());
    assertEquals("THEODORE",person.get(0).getFirstname());
  }

  @Test
  public void testBadData() {
    Stream<String> input = Stream.of("BAKER  ,    THEODORE, SMITH, ADAM  ");

    List<Person> person = input
        .map(mapper.mapper())
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    assertEquals(0,person.size());
  }
}
