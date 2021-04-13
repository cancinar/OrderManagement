package usecase;

@FunctionalInterface
public interface SupplierUseCase<O extends Output> {

  O run();
}
