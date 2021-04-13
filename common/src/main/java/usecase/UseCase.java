package usecase;

public interface UseCase<I extends Input, O extends Output> {

  O run(I input);
}
