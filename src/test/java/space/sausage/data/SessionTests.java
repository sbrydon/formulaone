package space.sausage.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings({"CanBeFinal", "unused"})
@RunWith(SpringRunner.class)
@JsonTest
public class SessionTests {
    @Autowired
    private JacksonTester<Session> json;

    @Test
    public void serializesToJson() throws Exception {
        Session session = new Session();
        session.setTitle("1st Practice");
        session.setEarliestStart(LocalDateTime.of(2017, 3, 24, 2, 0));
        session.setEarliestEnd(LocalDateTime.of(2017, 3, 24, 3, 30));

        assertThat(json.write(session)).isEqualToJson("session.json");
    }
}
