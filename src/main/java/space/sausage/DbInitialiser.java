package space.sausage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import space.sausage.data.Event;
import space.sausage.service.ResourceService;

import java.io.IOException;
import java.util.List;

/**
 * An initialiser for seeding the database
 */
@Component
class DbInitialiser {
    private static final Logger logger = LoggerFactory.getLogger(DbInitialiser.class);

    private final ResourceService resources;
    private final MongoTemplate mongo;

    @Autowired
    DbInitialiser(ResourceService resources, MongoTemplate mongo) {
        this.resources = resources;
        this.mongo = mongo;
    }

    @EventListener(ContextRefreshedEvent.class)
    void onContextRefreshed() {
        if (mongo.collectionExists(Event.class)) {
            return;
        }

        List<Event> events;

        try {
            events = resources.loadEvents();
        } catch (IOException e) {
            logger.error("Error loading events to seed the database", e);
            return;
        }

        mongo.createCollection(Event.class);
        mongo.insert(events, Event.class);
    }
}
