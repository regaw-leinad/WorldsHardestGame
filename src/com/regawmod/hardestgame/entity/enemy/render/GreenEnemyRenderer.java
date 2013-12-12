package com.regawmod.hardestgame.entity.enemy.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.entity.EntityRenderer;

public class GreenEnemyRenderer implements EntityRenderer
{
    private Enemy enemy;

    public GreenEnemyRenderer(Enemy enemy)
    {
        this.enemy = enemy;
    }

    @Override
    public void renderEntity(Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(this.enemy.getX(), this.enemy.getY(), this.enemy.getWidth(), this.enemy.getHeight());
        g.setColor(Color.green);
        g.fillOval(this.enemy.getX() + 1, this.enemy.getY() + 1, this.enemy.getWidth() - 2, this.enemy.getHeight() - 2);

        g.setColor(Color.black);
        g.fillOval(this.enemy.getCenterX() - this.enemy.getWidth() / 4 - 1, this.enemy.getCenterY() - this.enemy.getWidth() / 4 + 1, 2, 2);
        g.fillOval(this.enemy.getCenterX() + this.enemy.getWidth() / 4 - 1, this.enemy.getCenterY() - this.enemy.getWidth() / 4 + 1, 2, 2);

        g.drawLine(this.enemy.getCenterX() - this.enemy.getWidth() / 4, this.enemy.getCenterY() + this.enemy.getWidth() / 4 - 1,
                this.enemy.getCenterX() + this.enemy.getWidth() / 4 - 1, this.enemy.getCenterY() + this.enemy.getWidth() / 4 - 1);
    }

}
