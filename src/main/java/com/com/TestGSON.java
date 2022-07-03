package com.com;

public class TestGSON {
    public static void main(String[] args) {
        //Створюємо об'єкт класу
        A a = new A();
        ToGSON<A> toJSON= new ToGSON<A>();
        System.out.println("JSONs");
        System.out.println("simple");

        // отримуємо GSON формат для об'єкта класу
        System.out.println(toJSON.getSimpleGSON(a));
        System.out.println("formatted");

        //GSON для зміненого об'єкту
        String gsonString = toJSON.getFGsonObj(a);
        System.out.println(toJSON.getFGsonObj(a));
        FromGSON<A> fromGSON = new FromGSON<A>();

        //Хардкод зміни GSON
        String changedOriginalGSON = gsonString.replaceFirst("lolka", "kolobok");
        System.out.println("Changed Original GSON");
        System.out.println(changedOriginalGSON);

        //З GSON формату в об'єкт
        a = fromGSON.getObjectfromGson(changedOriginalGSON, A.class);
        System.out.println("Deserialized object");
        System.out.println(toJSON.getSimpleGSON(a));
    }
}
