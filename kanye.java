import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class kanye extends JApplet implements Runnable, KeyListener
{
    Thread t;
    paddle []paddles = new paddle[1];
    double v, theta;
    ArrayList<Ball>balls = new ArrayList();
    public void init(){
        setFocusable(true);
        addKeyListener(this);
        paddles[0] = new paddle (250,250);
        for(int i = 0; i < i; i++) {
            balls.add(new Ball());
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
                for(int i = 0; i < balls.size(); i++){
                    balls.get(i).move();
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
        for(int i = 0; i < balls.size(); i++){
            g.fillOval((int)balls.get(i).x,(int)balls.get(i).y,10,10);
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
