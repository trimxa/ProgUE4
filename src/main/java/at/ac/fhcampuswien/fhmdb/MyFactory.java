package at.ac.fhcampuswien.fhmdb;

import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {
    private static MyFactory instance;
    private MyFactory() {
    }

    public static MyFactory getInstance() {
        if (instance == null) {
            instance = new MyFactory();
        }
        return instance;
    }

    @Override
    // In FhmdbApplication, greift auf Callmethode dieser Klasse
    //gibt singleton instance of controller zurück, da kein Singleton in javafx möglich ist
    public Object call(Class<?> aClass) {
        try{
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}