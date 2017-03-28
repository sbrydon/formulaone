package space.sausage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.sausage.data.Event;
import space.sausage.data.EventRepository;

import java.util.List;

/**
 * A service for querying Events
 * @see Event
 */
@SuppressWarnings("unused")
@Service
public class EventService {
    private final EventRepository repository;

    @Autowired
    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    /**
     * @param year the year of the Events
     * @param month the month of the Events
     * @return a list of Events in the given year and month
     * @see Event
     */
    public List<Event> findAllByYearAndMonth(int year, int month) {
        return repository.findAllByYearAndMonth(year, month);
    }

    /**
     * @param year the year of the Event
     * @param race the race number of the Event
     * @return an Event for the given year and race
     * @throws EventNotFoundException if an event can't be found
     * @see Event
     */
    public Event findByYearAndRace(int year, int race) {
        return repository.findByYearAndRace(year, race).orElseThrow(EventNotFoundException::new);
    }
}
