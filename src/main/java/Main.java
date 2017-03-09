import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by ali on 3/9/17.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        MyClass myClass = new MyClass();      // You Can Use "ANY" Class that you want!
        myClass.setAge(21);
        myClass.setId(1);
        myClass.setName("Ali Boom");

        String jsonClass = toJson(myClass);
        System.out.println(jsonClass);
    }

    // This functions gets a ClassObject (Java Bean), then it creates a JSON representing its Fields and Values.
    private static String toJson(Object o) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class co = o.getClass();
        Method[] mtds = co.getDeclaredMethods();
        String jsonClass = "{\n";
        for (Method m: mtds) {
            String mtdName = m.getName();
            if (mtdName.startsWith("get")) {
                String fieldName = mtdName.substring(3);
                char s = Character.toLowerCase(fieldName.charAt(0));
                fieldName = s + fieldName.substring(1);
                jsonClass += fieldName + ": ";
                Object value = m.invoke(o);
                jsonClass += "\"" + value.toString() + "\",\n";
            }
        }
        jsonClass = jsonClass.substring(0, jsonClass.length() - 2);
        jsonClass += "\n}";

        return jsonClass;
    }
}
