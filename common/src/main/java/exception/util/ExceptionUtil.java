package exception.util;

import exception.ValidationException;
import java.util.function.Supplier;

public class ExceptionUtil {

  private ExceptionUtil() {

  }

  public static Supplier<ValidationException> validationException(String msg) {
    return () -> new ValidationException(msg);
  }
}
