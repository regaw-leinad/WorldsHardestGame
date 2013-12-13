package com.regawmod.hardestgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import org.apache.commons.io.IOUtils;
import com.regawmod.hardestgame.level.Level;

/**
 * Class to load levels dynamically from external jars.
 * 
 * @author Dan Wager
 */
public final class LevelLoader
{
    /** The directory that is searched for level jars */
    public static final File LEVEL_DIRECTORY = new File(System.getProperty("user.dir") + File.separator + "levels");

    /** The Level class name */
    public static final String LEVEL_SUPERCLASS = "com.regawmod.hardestgame.level.Level";
    /** The Enemy class name */
    public static final String ENEMY_SUPERCLASS = "com.regawmod.hardestgame.entity.Enemy";

    /** The collection of level subclasses */
    private List<Class<? extends Level>> pluginCollection;

    /**
     * Creates a new {@link LevelLoader}.
     */
    public LevelLoader()
    {
        pluginCollection = new ArrayList<Class<? extends Level>>();

        if (LEVEL_DIRECTORY.isDirectory())
            loadAllLevels();
        else
            LEVEL_DIRECTORY.mkdir();
    }

    /**
     * Loads all levels from the file system.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void loadAllLevels()
    {
        pluginCollection.clear();

        File[] levelJars = LEVEL_DIRECTORY.listFiles(new JarFilter());

        for (File f : levelJars)
        {
            try
            {
                List<String> classNames = getClassNames(f.getAbsolutePath());
                List<String> imageNames = getImageNames(f.getAbsolutePath());
                Class imageLoadRefClass = null;

                for (String className : classNames)
                {
                    // Remove the ".class" at the back
                    String name = className.substring(0, className.length() - 6);

                    // This will add enemies to class path as well
                    // We just don't need a reference to it in the level collection
                    addURLToClasspath(f.toURI().toURL());

                    Class currentClass = getClass(f, name);
                    imageLoadRefClass = currentClass;
                    Class superClass = currentClass.getSuperclass();

                    if (superClass.getName().equals(LEVEL_SUPERCLASS))
                        pluginCollection.add(currentClass);
                }

                // Brought outside to load only once per jar
                if (imageLoadRefClass != null)
                    loadLevelImages(imageNames, imageLoadRefClass);
            }
            catch (Exception e)
            {
                System.err.println("Error loading from: " + f.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads the level images from the external jar.
     * 
     * @param imageNames A list of image names
     * @param currentClass The class to get the class loader from
     * @throws IOException If there is an error reading from the file
     * @throws FileNotFoundException If the file does not exist
     */
    @SuppressWarnings("rawtypes")
    private void loadLevelImages(List<String> imageNames, Class currentClass) throws IOException, FileNotFoundException
    {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));

        for (int i = 0; i < imageNames.size(); i++)
        {
            File temporaryFile = new File(tempDir, "lvlTmp" + i);

            InputStream test = currentClass.getClassLoader().getResourceAsStream(imageNames.get(i));

            IOUtils.copy(test, new FileOutputStream(temporaryFile));

            String imageName = new File(imageNames.get(i)).getName().replace(".png", "");

            ResourceManager.loadImage(temporaryFile.getAbsolutePath(), imageName);
        }

        for (int i = 0; i < imageNames.size(); i++)
            new File(tempDir, "lvlTmp" + i).delete();
    }

    /**
     * Gets a list of class names from the external jar.
     * 
     * @param jarName The name of the jar file
     * @return A list of class names in the jar
     * @throws IOException If there was an error reading the jar
     */
    private List<String> getClassNames(String jarName) throws IOException
    {
        ArrayList<String> classes = new ArrayList<String>();

        JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
        JarEntry jarEntry;

        while ((jarEntry = jarFile.getNextJarEntry()) != null)
            if (jarEntry.getName().endsWith(".class"))
                classes.add(jarEntry.getName().replaceAll("/", "\\."));

        jarFile.close();

        return classes;
    }

    /**
     * Gets a list of image names from the external jar.
     * 
     * @param jarName The name of the jar file
     * @return A list of image names in the jar
     * @throws IOException If there was an error reading the jar
     */
    private List<String> getImageNames(String jarName) throws IOException
    {
        ArrayList<String> images = new ArrayList<String>();

        JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
        JarEntry jarEntry;

        while ((jarEntry = jarFile.getNextJarEntry()) != null)
            if (jarEntry.getName().endsWith(".png"))
                images.add(jarEntry.getName());

        jarFile.close();

        return images;
    }

    /**
     * Gets a class of the specified type.
     * 
     * @param file The jar containing the class
     * @param name The name of class
     * @return The class of the specified type
     * @throws MalformedURLException If the URL to the jar is bad
     * @throws ClassNotFoundException If the specified class doesn't exist
     */
    @SuppressWarnings({ "resource", "rawtypes" })
    private Class getClass(File file, String name) throws MalformedURLException, ClassNotFoundException
    {
        URLClassLoader classLoader;
        String filePath = file.getAbsolutePath();
        filePath = "jar:file://" + filePath + "!/";
        URL url = new File(filePath).toURI().toURL();
        classLoader = new URLClassLoader(new URL[] { url });

        return classLoader.loadClass(name);
    }

    /**
     * Adds a jar to the classpath.
     * 
     * @param u The URL to the jar file
     * @throws IOException If there is an error reading the jar
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void addURLToClasspath(URL u) throws IOException
    {
        URLClassLoader sysLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        URL urls[] = sysLoader.getURLs();

        for (int i = 0; i < urls.length; i++)
            if (urls[i].toString().equalsIgnoreCase(u.toString()))
                return;
        try
        {
            Class sysclass = URLClassLoader.class;
            Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
            method.setAccessible(true);
            method.invoke(sysLoader, new Object[] { u });
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }

    /**
     * Gets the collection of loaded levels.
     * 
     * @return A collection of loaded levels
     */
    public List<Class<? extends Level>> getLevelCollection()
    {
        return new ArrayList<Class<? extends Level>>(pluginCollection);
    }
}

/**
 * A filename filter for jar files.
 * 
 * @author Dan Wager
 */
class JarFilter implements FilenameFilter
{
    @Override
    public boolean accept(File dir, String name)
    {
        return (name.endsWith(".jar"));
    }
}