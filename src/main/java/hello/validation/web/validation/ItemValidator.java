package hello.validation.web.validation;

import hello.validation.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        /**
         * item == clazz
         * item == subItem(자식클래스)
         */
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        /* 오류 메시지가 2개 보이니까 앞에 입력해서 1개만 보이도록 할 수 있다. 선택하면 되는 거지만 일반적으로 앞에 많이 쓴다.
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }*/

        // 검증 로직
        // ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");        아래 if (!StringUtils.hasText(item.getItemName())) 를 ValidationUtils 사용해서 한 줄로 가능 (Empty 랑 공백만 제공)

        if (!StringUtils.hasText(item.getItemName())) {                                                                                  // 첫번째꺼 못찾으면 두번째꺼 찾음 / 두번째꺼도 못찾으면 defaultMessage 찾음 / defaultMessage 도 없으면 500 error
            errors.rejectValue("itemName", "required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        // 특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
