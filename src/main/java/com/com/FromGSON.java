package com.com;
import com.google.gson.*;

import java.lang.reflect.Field;

public class FromGSON<T> {

    T t;

    // функція об'єкт зі зміненими полями з анотацією @Save.
    public T change(T t, Class<T> cls) throws  InstantiationException, IllegalAccessException{
        // Створення "стандартного" об'єкта
        T from = (T)cls.newInstance();

        // Зміна полів об'єкта
        setStandartValue(t, from);
        return t;
    }
    // Функція повертає об'єкт з GSON зі зміненими полями з анотацією @Save
    public T getObjectfromGson(String gsonString, Class<T> cls) {
        Gson gson = new GsonBuilder().create();
        t = (T)gson.fromJson(gsonString, cls);
        try {
            change(t, cls);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
    //Функцыя проходить по кожному полю об'єкта. Якщо поле з анотацією Save дані не змінює. Якщо анотації немає,
    //то значення полю присоюється зі стандартного.

    private void setStandartValue(Object to, Object from) throws IllegalAccessException {
        Field fieldsTo[] = to.getClass().getDeclaredFields();
        Field fieldsFrom [] = from.getClass().getDeclaredFields();
        for (int i = 0; i < fieldsTo.length; i++) {
            if (!fieldsTo[i].isAnnotationPresent(Save.class)) {
                fieldsTo[i].setAccessible(true);
                fieldsFrom[i].setAccessible(true);
                if (fieldsTo[i].getType().isPrimitive()) {
                    // Тут десеріалізація
                    if (fieldsTo[i].getType() == boolean.class) {
                        fieldsTo[i].set(to, fieldsFrom[i].getBoolean(from));
                    } else if (fieldsTo[i].getType() == int.class) {
                        fieldsTo[i].set(to, fieldsFrom[i].getInt(from));
                    } else if (fieldsTo[i].getType() == long.class) {
                        fieldsTo[i].set(to, fieldsFrom[i].getLong(from));
                    } else if (fieldsTo[i].getType() == byte.class) {
                        fieldsTo[i].set(to, fieldsFrom[i].getByte(from));
                    } else if (fieldsTo[i].getType() == double.class) {
                        fieldsTo[i].set(to, fieldsFrom[i].getDouble(from));
                    } else if (fieldsTo[i].getType() == short.class) {
                        fieldsTo[i].set(to, fieldsFrom[i].getShort(from));
                    } else if (fieldsTo[i].getType() == float.class) {
                        fieldsTo[i].set(to, fieldsFrom[i].getFloat(from));
                    }
                } else {
                    fieldsTo[i].set(to, fieldsFrom[i].get(from));
                }
                continue;
            }
            if (!fieldsTo[i].getType().isPrimitive()) {

                try {
                    fieldsTo[i].setAccessible(true);
                    fieldsFrom[i].setAccessible(true);
                    Save save = fieldsTo[i].getDeclaredAnnotation(Save.class);
                    if(!save.searchInside())
                        continue;
                    else
                        setStandartValue(fieldsTo[i].get(to), fieldsFrom[i].get(from));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
}
