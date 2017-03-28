package space.sausage.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A repository for querying Events
 * @see Event
 */
@SuppressWarnings("unused")
@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    /**
     * @param year the year of the Events
     * @param month the month of the Events
     * @return a list of Events in the given year and month
     * @see Event
     */
    List<Event> findAllByYearAndMonth(int year, int month);

    /**
     * @param year the year of the Event
     * @param race the race number of the Event
     * @return an Event for the given year and race
     * @see Event
     */
    Optional<Event> findByYearAndRace(int year, int race);
}
