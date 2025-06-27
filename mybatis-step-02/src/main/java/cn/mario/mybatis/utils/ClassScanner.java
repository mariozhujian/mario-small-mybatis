package cn.mario.mybatis.utils;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 扫描指定包下的所有类
 * @author: mario
 * @date: 2025/6/26
 */
public class ClassScanner {

    public static Set<Class<?>> scanPackage(String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            Enumeration<URL> resources = classLoader.getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File file = new File(resource.getFile());
                if (file.exists() && file.isDirectory()) {
                    findClasses(file, packageName, classes);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to scan package: " + packageName, e);
        }
        return classes;
    }


    private static void findClasses(File directory, String packageName, Set<Class<?>> classes) throws ClassNotFoundException {
        if (!directory.exists()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                findClasses(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
    }

}
