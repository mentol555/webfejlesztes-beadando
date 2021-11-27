package webfejlesztes.beadando;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import model.Car;
import repo.CarRepository;

@SpringBootApplication
@ComponentScan({"controller"})
@EntityScan("model")
@EnableJpaRepositories("repo")
public class ProjektApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektApplication.class, args);
	}

	@Autowired
	private CarRepository carRepository;

}
