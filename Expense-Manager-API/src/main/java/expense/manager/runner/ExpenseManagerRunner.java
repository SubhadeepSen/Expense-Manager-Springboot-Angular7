package expense.manager.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "expense.manager.*")
public class ExpenseManagerRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerRunner.class, args);
	}

}
