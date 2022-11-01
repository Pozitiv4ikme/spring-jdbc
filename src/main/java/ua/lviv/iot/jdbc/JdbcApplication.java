package ua.lviv.iot.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.lviv.iot.jdbc.view.MyView;

@SpringBootApplication
@RequiredArgsConstructor
public class JdbcApplication implements CommandLineRunner {

	private final MyView view;

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		view.show();
	}
}
