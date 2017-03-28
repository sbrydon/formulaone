package space.sausage.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import space.sausage.data.Event;

/**
 * An exception thrown when an Event can't be found
 * @see Event
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Event not found")
public class EventNotFoundException extends RuntimeException {
}
