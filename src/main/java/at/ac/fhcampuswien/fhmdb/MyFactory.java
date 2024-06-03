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
    // In FhmdbApplication, calls the call method here
    //returns singleton of controller, as no singleton in JavaFX
    public Object call(Class<?> aClass) {
        try{
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}