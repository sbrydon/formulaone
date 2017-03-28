package space.sausage.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import space.sausage.data.Event;
import space.sausage.data.EventRepository;

import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings({"CanBeFinal", "unused"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTests {
    @MockBean
    private EventRepository repository;

    @Autowired
    private EventService service;

    @SuppressWarnings("WeakerAccess")
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void findsAllByYearAndMonth() {
        service.findAllByYearAndMonth(2017, 3);
        verify(repository).findAllByYearAndMonth(2017, 3);
    }

    @Test
    public void findsByYearAndRace() {
        when(repository.findByYearAndRace(2017, 1)).thenReturn(Optional.of(new Event()));
        service.findByYearAndRace(2017, 1);
    }

    @Test
    public void throwsEventNotFoundException() {
        exception.expect(EventNotFoundException.class);
        when(repository.findByYearAndRace(anyInt(), anyInt())).thenReturn(Optional.empty());

        service.findByYearAndRace(2017, 1);
    }
}
