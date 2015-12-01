import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class kanye extends JApplet implements Runnable, KeyListener
{
    Thread t;
    paddle []paddles = new paddle[1];
    double v, theta;
    ArrayList<Face>faces = new ArrayList();
    int manyfaces = 1;
    public void init(){
        setFocusable(true);
        addKeyListener(this);
        paddles[0] = new paddle (250,250);
        for(int i = 0; i < manyfaces; i++) {
            faces.add(new Face());
        }
        t = new Thread(this);
        resize(500,500);
        t.start();
    }

    public void run(){
        try{
            while (true){
                repaint();
                t.sleep(50);
                for(int i = 0; i < faces.size(); i++){
                    faces.get(i).move(getHeight(), getWidth());
                }
            }
        }catch (InterruptedException e) {}
        repaint();
    }

    public void paint(Graphics g) {
        Graphics offScreenG;
        Image offScreenI = null;

        offScreenI = createImage(getWidth(), getHeight());
        offScreenG = offScreenI.getGraphics();

        subpaint(offScreenG);

        g.drawImage(offScreenI, 0, 0, this);
    }

    public void subpaint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,500,500);
        g.setColor(Color.black);
        for(int i = 0; i < faces.size(); i++){
            g.fillOval((int)faces.get(i).x,(int)faces.get(i).y,10,10);
        }
        for(int i = 0; i < paddles.length; i++){
            g.fillOval(paddles[i].m, paddles[i].n, paddles[i].width, paddles[i].length);
        }
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
    }

    public void keyReleased(KeyEvent e){

    }
}
