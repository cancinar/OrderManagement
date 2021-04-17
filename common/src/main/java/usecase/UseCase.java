package usecase;

import java.util.function.Function;

public interface UseCase<I extends Input, O extends Output> extends Function<I,O> {
}
