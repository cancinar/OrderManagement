package usecase;

@FunctionalInterface
public interface ConsumerUseCase<I extends Input> {

  void run(I input);
}
