package factory;

import lombok.SneakyThrows;

import java.lang.reflect.Field;


public class ObjectFactory {
    private static final ObjectFactory ourInstance = new ObjectFactory();
    private final Config config = new ConfigImpl("factory");

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();

        injectByType(t);

        return t;
    }

    private static <T> void injectByType(T t) throws IllegalAccessException {
        for (Field declaredFields : t.getClass().getDeclaredFields()) {
            //Если в классе, есть аннотация InjectByType над интерфейсом, мы создаем инстанс имплементации этого интерфейса
            if (declaredFields.isAnnotationPresent(InjectByType.class)) {
                declaredFields.setAccessible(true);
                Object object = ObjectFactory.getInstance().createObject(declaredFields.getType());
                declaredFields.set(t, object);
            }
        }
    }
}
