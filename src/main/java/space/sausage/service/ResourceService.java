package space.sausage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import space.sausage.data.Event;

import java.io.IOException;
import java.util.List;

/**
 * A service for loading class path resources
 */
@SuppressWarnings("unused")
@Service
public class ResourceService {
    private final ResourceFactory factory;
    private final ObjectMapper mapper;

    @Autowired
    public ResourceService(ResourceFactory factory, ObjectMapper mapper) {
        this.factory = factory;
        this.mapper = mapper;
    }

    /**
     * @return a deserialised list of Events
     * @throws IOException if the file could not be opened
     * @see Event
     */
    public List<Event> loadEvents() throws IOException {
        ClassPathResource resource = factory.create("events.json");
        CollectionType type = TypeFactory.defaultInstance().constructCollectionType(List.class, Event.class);

        return mapper.readValue(resource.getInputStream(), type);
    }
}
