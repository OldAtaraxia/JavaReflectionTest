package github.oldataraxia;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter class name: ");
        name = in.next();
        try {
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers()); // 描述修饰符使用情况
            if(modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if(supercl != null && supercl != Object.class) {
                System.out.print(" extends " + supercl.getName());
            }
            System.out.print(" {\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getConstructors();
        for(Constructor c : constructors) {
            String name = c.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");
            // 参数列表
            Class[] params = c.getParameterTypes();
            for(Class p : params) {
                System.out.print(", " + p.getName());
            }
            System.out.println(");");
        }
    }

    public static void printMethods(Class cl) {
        Method[] methods = cl.getMethods();
        for (Method m : methods) {
            Class returnType = m.getReturnType();
            String name = m.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(returnType.getName() + " " + name + "(");
            // 参数
            Class[] paramTypes = m.getParameterTypes();
            for (Class p : paramTypes) {
                System.out.print(", " + p.getName());
            }
            System.out.println(");");
        }
    }

    public static void printFields(Class cl) {
        Field[] fields = cl.getFields();
        for (Field f : fields) {
            Class Type = f.getType();
            String name = f.getName();
            String modifiers = Modifier.toString(f.getModifiers());
            System.out.print("    ");
            if(modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(Type.getName() + " " + name + ";\n");
        }
    }

}
