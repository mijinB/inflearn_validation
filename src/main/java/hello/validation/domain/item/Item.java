package hello.validation.domain.item;

import jakarta.validation.constraints.Max;
import lombok.Data;

// jakarta.validation 으로 시작하는 것들은 Bean Validation 에서 제공하는 것이고 어떤 구현체에서도 다 동작한다.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// org.hibernate.validator 로 시작하는 것들은 hibernate validator 에서만 동작한다.
// 실무에서 대부분 하이버네이트 validator를 사용하므로 자유롭게 사용해도 된다. (+Spring Boot 도 기본적으로 Hibernate Validator 를 넣어준다. ⇒ build.gradle 에 spring-boot-starter 로 넣어야 사용가능)
import org.hibernate.validator.constraints.Range;

@Data
public class Item {

    private Long id;

    @NotBlank(message = "공백X")      // message 설정 가능
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
