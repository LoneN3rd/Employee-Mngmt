package co.mercy.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class HrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
		System.out.println("Spring Version: " + SpringVersion.getVersion());
	}

}
