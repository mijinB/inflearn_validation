package hello.validation.domain.item;

import jakarta.validation.constraints.Max;
import lombok.Data;

// jakarta.validation 으로 시작하는 것들은 Bean Validation 에서 제공하는 것이고 어떤 구현체에서도 다 동작한다.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// org.hibernate.validator 로 시작하는 것들은 hibernate validator 에서만 동작한다.
// 실무에서 대부분 하이버네이트 validator를 사용하므로 자유롭게 사용해도 된다. (+Spring Boot 도 기본적으로 Hibernate Validator 를 넣어준다. ⇒ build.gradle 에 spring-boot-starter 로 넣어야 사용가능)
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

/*
 * @ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")     ⇒ JDK 11 버전만 가능하다. Spring Boot 3이후 부터는 JDK 17 이상을 사용하는 것이 필수 조건으로 되어있기 때문에 @ScriptAssert 를 이용한 자바스크립트 표현식을 사용할 수 없다.
 *   @ScriptAssert 보다 직접 java 코드로 작성하는 게 더 좋다. ValidationItemControllerV3.java > addItem > "특정 필드가 아닌 복합 룰 검증"라고 주석되어 있는 조건문!
 */
@Data
public class Item {

    @NotNull(groups = UpdateCheck.class)
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
