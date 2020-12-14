package ex;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {
    public <T> void inject(T inj) {
        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            prop.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Field[] fields = inj.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                if (f.isAnnotationPresent(AutoInjectable.class)) {
                    f.setAccessible(true);
                    String typeOfField = prop.getProperty(f.getType().getName());
                    Class<?> class1 = Class.forName(typeOfField);
                    Constructor<?> constructor = class1.getDeclaredConstructor();
                    Object obj = constructor.newInstance();
                    f.set(inj, obj);
                    System.out.println(f);
                }
            } catch (NullPointerException | ClassNotFoundException | NoSuchMethodException |
            IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
