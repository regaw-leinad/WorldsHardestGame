package com.regawmod.hardestgame;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resources
{
    private static Map<String, Image> images;

    static
    {
        images = new HashMap<String, Image>();
    }

    public static Image getLevelImage(String name)
    {
        Image result = images.get(name);

        if (result == null)
            return loadImage(name, LevelLoader.LEVEL_RES_DIRECTORY);

        return result;
    }

    private static Image loadImage(String name, String path)
    {
        Image load = null;

        try
        {
            load = new Image(path + File.separator + name + ".png");
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
