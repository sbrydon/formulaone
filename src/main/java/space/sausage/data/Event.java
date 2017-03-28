package space.sausage.data;

import java.util.List;

/**
 * An event in the F1 calendar, e.g. 2017 Australian Grand Prix
 * @see Session
 */
@SuppressWarnings({"SameParameterValue", "unused", "WeakerAccess"})
public class Event {
    private int year;
    private int month;
    private int race;
    private String title;
    private List<Session> sessions;

    /**
     * @return the year of the event
     */
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the month of the event
     */
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the race number of the event, e.g. 1 (first) or 20 (last)
     */
    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    /**
     * @return the title of the event, e.g. 2017 Australian Grand Prix
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return a list of event's sessions
     * @see Session
     */
    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
