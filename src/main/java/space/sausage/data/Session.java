package space.sausage.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * A session, e.g. First Practice, Qualifying, or Grand Prix
 * @see Event
 */
@SuppressWarnings({"SameParameterValue", "unused", "WeakerAccess"})
class Session {
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime earliestStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime earliestEnd;

    /**
     * @return the title of the session, e.g. 2017 Australian Grand Prix - First Practice
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the earliest start (UTC)
     */
    public LocalDateTime getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(LocalDateTime earliestStart) {
        this.earliestStart = earliestStart;
    }

    /**
     * @return the earliest end (UTC)
     */
    public LocalDateTime getEarliestEnd() {
        return earliestEnd;
    }

    public void setEarliestEnd(LocalDateTime earliestEnd) {
        this.earliestEnd = earliestEnd;
    }
}
