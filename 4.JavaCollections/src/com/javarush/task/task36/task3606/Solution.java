package com.javarush.task.task36.task3606;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection

Аргументом для класса Solution является абсолютный путь к пакету.
Имя пакета может содержать File.separator.
В этом пакете кроме скомпилированных классов (.class) могут находиться и другие файлы (например: .java).
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считай все классы с файловой системы, создай фабрику — реализуй метод getHiddenClassObjectByKey.
Примечание: в пакете может быть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File dir = new File(packageName);
        String[] classFiles = dir.list();
        for (String s : classFiles) {
            final String finalPath = packageName + File.separator;
            ClassLoader classLoader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    byte[] temp = getBytesFromFile(finalPath + name + ".class");
                    return defineClass(null, temp, 0, temp.length);
                }

                private byte[] getBytesFromFile(String s) {
                    File file = new File(s);
                    byte[] data = new byte[(int) file.length()];
                    try {
                        data = Files.readAllBytes(file.toPath());
                    } catch (IOException e) {
                    }
                    return data;
                }
            };
            try {
                Class clazz = classLoader.loadClass(s.substring(0, s.length() - 6));
                hiddenClasses.add(clazz);
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == 0) {
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance();
                        }
                    }
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                }
            }
        }
        return null;
    }
}

