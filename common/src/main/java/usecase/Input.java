package usecase;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.join;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;

public interface Input {

  default void validate() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    final List<String> errorMessages = validatorFactory
        .getValidator()
        .validate(this)
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(toList());

    if (!isEmpty(errorMessages)) {
      throw new ValidationException(join(errorMessages, EMPTY));
    }
  }
}
