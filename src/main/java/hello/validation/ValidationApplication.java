package hello.validation;

import hello.validation.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ValidationApplication implements WebMvcConfigurer {
// public class ValidationApplication implements WebMvcConfigurer {		⇒ validator 글로벌 설정 (사용할 일이 굉장히 드물다.)

	public static void main(String[] args) {
		SpringApplication.run(ValidationApplication.class, args);
	}

	/* validator 글로벌 설정
	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}*/

}
