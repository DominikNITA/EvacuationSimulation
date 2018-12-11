package pobj.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{

    private ArrayList<Drawable> listToDraw;

    public Fenetre(){
        super("Simulation multi-agents");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listToDraw = new ArrayList<Drawable>();
        JPanel panel = new JPanel(){
            public void paint(Graphics g){
                super.paint(g);
                for(int i=0; i<listToDraw.size(); i++)
                    listToDraw.get(i).draw(g);
            }
        };
//        panel.setPreferredSize(new Dimension(1024, 768));
        panel.setPreferredSize(new Dimension(450, 550));
        panel.setBackground(Color.BLUE);
        this.add(panel);
        this.setVisible(true);
        this.pack();
    }

    public void add(Drawable d){ 
        listToDraw.add(d);
        // tri par ordre de priorité...
        // ce qui est affiché en dernier écrase ce qui est affiché avant
        Collections.sort(listToDraw, new Comparator<Drawable>() {
            @Override
            public int compare(Drawable o1, Drawable o2) {
                if(o1.getPriority()<o2.getPriority())
                    return -1;
                if(o1.getPriority()>o2.getPriority())
                    return 1;
                return 0;
            }

        });
    }
    // dessiner l'image courante
    public BufferedImage getImage() {
        BufferedImage im = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
//        this.getContentPane().paint(im.getGraphics());
        im.getGraphics().setColor(Color.BLUE);
        im.getGraphics().fillRect(0, 0, this.getWidth(), this.getHeight());
        for(int i=0; i<listToDraw.size(); i++)
            listToDraw.get(i).draw(im.getGraphics());
        return im;
    }


}
