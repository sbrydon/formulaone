package space.sausage.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * A factory for creating ClassPathResources
 */
@SuppressWarnings("unused")
@Component
class ResourceFactory {
    /**
     * @param path the absolute path within the class path
     * @return a ClassPathResource
     * @see ClassPathResource
     */
    ClassPathResource create(String path) {
        return new ClassPathResource(path);
    }
}
