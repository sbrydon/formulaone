package space.sausage.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings({"CanBeFinal", "unused"})
@RunWith(SpringRunner.class)
@JsonTest
public class EventTests {
    @Autowired
    private JacksonTester<Event> json;

    @Test
    public void serializesToJson() throws Exception {
        Event event = new Event();
        event.setYear(2017);
        event.setMonth(3);
        event.setRace(1);
        event.setTitle("2017 Australian Grand Prix");

        assertThat(json.write(event)).isEqualToJson("event.json");
    }
}