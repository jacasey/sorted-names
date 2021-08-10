package nz.co.jcasey.sortednames;

import nz.co.jcasey.sortednames.controller.SortedNamesFileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class SortedNamesCommandLineRunner implements CommandLineRunner {
  @Autowired
  private SortedNamesFileController sortedNames;

  @Override
  public void run(String... args) throws Exception {
    sortedNames.run(args);
  }
}
