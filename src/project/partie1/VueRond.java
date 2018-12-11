package project.partie1;

import pobj.ihm.Drawable;

import java.awt.*;

public class VueRond implements Drawable {
    private double x,y,rayon;

    public VueRond(double x, double y, double rayon) {
        this.x = x;
        this.y = y;
        this.rayon = rayon;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval((int) x, (int) y, (int) (2*rayon), (int) (2*rayon));
    }

    @Override
    public int getPriority() {
        return 0;
    }

    public void move(){
        x++;
        y++;
    }
}
