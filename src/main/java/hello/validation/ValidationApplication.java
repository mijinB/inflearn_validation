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

	/* validator 글로벌 설정 ⇒ 이렇게 직접 Global Validator 를 직접 등록하면 Spring boot 는 Bean Validator 를 Global Validator 로 등록하지 않는다. 따라서 어노테이션 기반의 Bean Validator 가 동작하지 않게 된다.
	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}*/

}
