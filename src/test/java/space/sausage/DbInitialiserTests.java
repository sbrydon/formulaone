package space.sausage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import space.sausage.data.Event;
import space.sausage.service.ResourceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings({"CanBeFinal", "unused"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbInitialiserTests {
    @MockBean
    private ResourceService resources;

    @Mock
    private MongoTemplate mongo;

    private DbInitialiser initialiser;

    @Before
    public void setup() {
        initialiser = new DbInitialiser(resources, mongo);
    }

    @Test
    public void doesNothingIfCollectionExists() {
        when(mongo.collectionExists(Event.class)).thenReturn(true);

        initialiser.onContextRefreshed();

        verify(mongo, never()).createCollection(Event.class);
    }

    @Test
    public void doesNothingIfUnableToLoadEvents() throws IOException {
        when(mongo.collectionExists(Event.class)).thenReturn(false);
        when(resources.loadEvents()).thenThrow(new IOException());

        initialiser.onContextRefreshed();

        verify(mongo, never()).createCollection(Event.class);
    }

    @Test
    public void createsEventCollection() throws IOException {
        when(mongo.collectionExists(Event.class)).thenReturn(false);
        when(resources.loadEvents()).thenReturn(new ArrayList<>());

        initialiser.onContextRefreshed();

        verify(mongo).createCollection(Event.class);
    }

    @Test
    public void insertsEvents() throws IOException {
        when(mongo.collectionExists(Event.class)).thenReturn(false);
        List<Event> events = new ArrayList<>();
        when(resources.loadEvents()).thenReturn(events);

        initialiser.onContextRefreshed();

        verify(mongo).insert(events, Event.class);
    }
}
