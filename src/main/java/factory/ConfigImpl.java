package factory;

import org.reflections.Reflections;

import java.util.Set;

public class ConfigImpl implements Config {
    private final Reflections reflections;

    public ConfigImpl(String packageToScan) {
        this.reflections = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> type) {
        Set<Class<? extends T>> classes = reflections.getSubTypesOf(type);
        return classes.iterator().next();
    }
}
