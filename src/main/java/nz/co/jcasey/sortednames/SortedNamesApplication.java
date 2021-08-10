package nz.co.jcasey.sortednames;

import nz.co.jcasey.sortednames.controller.SortedNamesFileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootApplication
public class SortedNamesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SortedNamesApplication.class, args);
	}
}
