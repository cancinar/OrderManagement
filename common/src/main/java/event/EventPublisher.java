package event;

public interface EventPublisher<T extends Event> {

  void publish(String topic, T t);
}
