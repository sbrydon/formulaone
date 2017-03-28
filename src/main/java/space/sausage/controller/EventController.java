package space.sausage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import space.sausage.data.Event;
import space.sausage.service.EventNotFoundException;
import space.sausage.service.EventService;

import java.util.List;

/**
 * A {@link RestController} for retrieving Events
 * @see Event
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/events/{year}")
class EventController {
    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    /**
     * @param year the year of the Events
     * @param month the month of the Events
     * @return a list of Events in the given year and month
     * @see Event
     */
    @RequestMapping(method = RequestMethod.GET, value = "/months/{month}")
    public List<Event> getByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        return service.findAllByYearAndMonth(year, month);
    }

    /**
     * @param year the year of the Event
     * @param race the race number of the Event
     * @return an Event for the given year and race
     * @throws EventNotFoundException if an event can't be found
     * @see Event
     */
    @RequestMapping(method = RequestMethod.GET, value = "/races/{race}")
    public Event getByYearAndRace(@PathVariable int year, @PathVariable int race) {
        return service.findByYearAndRace(year, race);
    }
}
