package com.regawmod.slick.interfaces;

public interface Updatable
{
    /**
     * Update logic based on the amount of time that has passed.
     * 
     * @param gc The game's container
     * @param dt The amount of time that has passed in seconds since the last update
     */
    void update(org.newdawn.slick.GameContainer gc, float dt);
}
