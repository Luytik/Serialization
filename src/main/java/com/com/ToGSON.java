package com.com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
// Клас для серіалізації об'єкта в Gson
public class ToGSON<T> {
    private T t;

    // Функція яка повертає об'єкт зі зміненими полями з анотацією Save
    public T formattedT(T t) {
        try {
            setNull(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    // Функція повертає GSON для об'єкта
    public String getSimpleGSON(T t) {
        Gson g = new GsonBuilder().create();
        String simpleGson = g.toJson(t);
        return simpleGson;
    }
    // Функція повертає GSON об'єкта зі зміненими полями з анотацією Save
    public String getFGsonObj(T t) {
        Gson g = new GsonBuilder().create();
        String fObjGson = g.toJson(formattedT(t));
        return fObjGson;
    }

    //Проходимо по кожному полю об'єкта. Якщо поле в об'єкті позначено анотацією @Save не чіпаємо його.
    //Якщо навпаки позначаємо Null для об'єктів. Для примітивних полів ставимо значення -1 або false в залежності від типу
    //примітиву.
    private void setNull(Object o) throws IllegalAccessException {
        Field fields[] = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].isAnnotationPresent(Save.class)) {
                fields[i].setAccessible(true);
                if (fields[i].getType().isPrimitive()) {
                    // Я розумію, що передавати так значення не можна. Поки, що тестую програму, можливо далі
                    // виправлю. При десеріалізації даного класу, дані будуть перероблені. Додано просто для перевірки моливості.
                    if (fields[i].getType() == boolean.class) {
                        fields[i].set(o, false);
                    } else if (fields[i].getType() == int.class) {
                        fields[i].set(o, -1);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(o, -1);
                    } else if (fields[i].getType() == byte.class) {
                        fields[i].set(o, -1);
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(o, -1.0);
                    } else if (fields[i].getType() == short.class) {
                        fields[i].set(o, -1.0);
                    } else if (fields[i].getType() == float.class) {
                        fields[i].set(o, -1);
                    }
                } else {
                    fields[i].set(o, null);
                }
                continue;
            }
            if (!fields[i].getType().isPrimitive()) {

                try {
                    fields[i].setAccessible(true);
                    Save save = fields[i].getDeclaredAnnotation(Save.class);
                    if(!save.searchInside())
                        continue;
                    else
                    setNull(fields[i].get(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
}
