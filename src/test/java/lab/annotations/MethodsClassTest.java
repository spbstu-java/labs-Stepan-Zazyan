package lab.annotations;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MethodsClassTest {
    @Test
    void test() throws InvocationTargetException, IllegalAccessException {
        MethodsClass obj = new MethodsClass();
        Class<MethodsClass> classMethodsClass = MethodsClass.class;
        Method[] methods = classMethodsClass.getDeclaredMethods();
        String result = "";
        for (Method m : methods) {
            if (m.isAnnotationPresent(MyAnnotation.class)) {
                m.setAccessible(true);
                for (int i = 0; i < m.getAnnotation(MyAnnotation.class).value(); i++) {
                    result += " " + m.invoke(obj, 2, 4);
                }
            }
        }
        assertEquals(result.trim(), ("8 8 6 6 6 6 8 8 8 6").trim());
    }
}