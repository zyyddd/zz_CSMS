//@Time:2021/11/2 21:30
//@Author:aFun

import org.junit.jupiter.api.Test;

import java.util.List;

public class TestSomething {
    @Test
    public <E> E get(List<E> list, Class<E> clazz) {
        getA(clazz);
        return null;
    }

    public <E> void get1(List<E> list, Class<E> clazz) {
        getA(clazz);
    }

    public void getA(Class<?> clazz) {
        System.out.println(clazz.getClass());
    }

//
//    @Test
//    public void testClass(){
//        TransFormT<User> tft=new TransFormT<User>();
//
//        System.out.println(tft.newInstance());
//    }
}
