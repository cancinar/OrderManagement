package event;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Event {
  private final UUID eventId = UUID.randomUUID();
}
