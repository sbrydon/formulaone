package space.sausage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import space.sausage.data.Event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings({"CanBeFinal", "unused"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceTests {
    @MockBean
    private ResourceFactory factory;

    @MockBean
    private ObjectMapper mapper;

    @Autowired
    private ResourceService service;

    @SuppressWarnings("WeakerAccess")
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void loadsEvents() throws IOException {
        // Arrange
        ClassPathResource resource = mock(ClassPathResource.class);
        when(factory.create("events.json")).thenReturn(resource);

        InputStream inputStream = new ByteArrayInputStream("test data".getBytes());
        when(resource.getInputStream()).thenReturn(inputStream);

        when(mapper.readValue(eq(inputStream), any(CollectionType.class))).thenReturn(new ArrayList<>());

        // Act
        List<Event> events = service.loadEvents();

        // Assert
        assertNotNull(events);
    }

    @Test
    public void throwsIOException() throws IOException {
        exception.expect(IOException.class);

        ClassPathResource resource = mock(ClassPathResource.class);
        when(resource.getInputStream()).thenThrow(new IOException());
        when(factory.create(anyString())).thenReturn(resource);

        service.loadEvents();
    }
}
