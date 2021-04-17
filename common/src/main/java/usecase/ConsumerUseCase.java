package usecase;

import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerUseCase<I extends Input> extends Consumer<I> {

}
