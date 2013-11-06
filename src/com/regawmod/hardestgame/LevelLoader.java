package com.regawmod.hardestgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import com.regawmod.hardestgame.level.Level;

class ClassFilter implements FilenameFilter
{
    @Override
    public boolean accept(File dir, String name)
    {
        return (name.endsWith(".jar"));
    }
}

public class LevelLoader
{
    private static final Class[] parameters = new Class[] { URL.class };
    private static List<Class<? extends Level>> pluginCollection;

    static
    {
        pluginCollection = new ArrayList<Class<? extends Level>>();
        loadLevels(System.getProperty("user.dir") + File.separator + "levels");
    }

    public static void loadLevels(String directory)
    {
        pluginCollection.clear();

        File dir = new File(directory);
        if (dir.isFile())
            return;

        File[] files = dir.listFiles(new ClassFilter());
        for (File f : files)
        {
            try
            {
                List<String> classNames = getClassNames(f.getAbsolutePath());
                for (String className : classNames)
                {
                    // Remove the ".class" at the back
                    String name = className.substring(0, className.length() - 6);
                    Class clazz = getClass(f, name);
                    Class superClass = clazz.getSuperclass();

                    if (superClass.getName().equals("com.regawmod.hardestgame.level.Level"))
                        pluginCollection.add(clazz);
                }
            }
            catch (Exception e)
            {
                System.err.println("Error loading from: " + f.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }

    private static List<String> getClassNames(String jarName) throws IOException
    {
        ArrayList<String> classes = new ArrayList<String>(10);
        JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
        JarEntry jarEntry;
        while (true)
        {
            jarEntry = jarFile.getNextJarEntry();
            if (jarEntry == null)
            {
                break;
            }
            if (jarEntry.getName().endsWith(".class"))
            {
                classes.add(jarEntry.getName().replaceAll("/", "\\."));
            }
        }

        jarFile.close();

        return classes;
    }

    private static Class<Level> getClass(File file, String name) throws Exception
    {
        addURL(file.toURI().toURL());

        URLClassLoader clazzLoader;
        Class clazz;
        String filePath = file.getAbsolutePath();
        filePath = "jar:file://" + filePath + "!/";
        URL url = new File(filePath).toURL();
        clazzLoader = new URLClassLoader(new URL[] { url });
        clazz = clazzLoader.loadClass(name);

        return clazz;

    }

    private static void addURL(URL u) throws IOException
    {
        URLClassLoader sysLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        URL urls[] = sysLoader.getURLs();
        for (int i = 0; i < urls.length; i++)
        {
            if (urls[i].toString().equalsIgnoreCase(u.toString()))
            {
                return;
            }
        }

        Class sysclass = URLClassLoader.class;
        try
        {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysLoader, new Object[] { u });
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }

    public static List<Class<? extends Level>> getLevelCollection()
    {
        return new ArrayList<Class<? extends Level>>(pluginCollection);
    }

    public static Level getLevel(int i) throws InstantiationException, IllegalAccessException
    {
        return pluginCollection.get(i).newInstance();
    }

}