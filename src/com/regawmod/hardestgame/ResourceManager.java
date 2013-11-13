package com.regawmod.hardestgame;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Handles loading/getting resources.
 * 
 * @author Dan Wager
 */
public class ResourceManager
{
    /** The collection of images */
    private static Map<String, Image> images;

    static
    {
        images = new HashMap<String, Image>();
    }

    /**
     * Gets the image for the specified level.
     * 
     * @param name The level name
     * @return The level image
     */
    public static Image getLevelImage(String name)
    {
        return getImage(name);
    }

    /**
     * Gets an image with the specified name.
     * 
     * @param name The name of the image
     * @return The image with the specified name
     */
    public static Image getImage(String name)
    {
        Image result = images.get(name);

        if (result == null)
            return loadImage(name);

        return result;
    }

    /**
     * Loads an image from the specified file.
     * 
     * @param name The name of the resource
     * @return The loaded image
     */
    private static Image loadImage(String name)
    {
        return loadImage("res/" + name + ".png", name);
    }

    /**
     * Loads an image from the specified path, and saves it with the specified name.
     * 
     * @param path The path to the image
     * @param name The image name to save as
     * @return The loaded image
     */
    public static Image loadImage(String path, String name)
    {
        Image load = null;

        try
        {
            load = new Image(path);
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        images.put(name, load);

        return load;
    }
}
