import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class kanye extends JApplet implements Runnable, KeyListener
{
    Thread t;
    Paddle []paddles = new Paddle[1];
    Goal []goals = new Goal[1];
    double v, theta;
    ArrayList<Face>faces = new ArrayList();
    int manyfaces = 1;
    int vplus = 0;
    int gradius = 40;
    boolean leftmove = false;
    boolean rightmove = false;
    public void init(){
        setFocusable(true);
        addKeyListener(this);
        paddles[0] = new Paddle(225, 250, (double)(gradius + 20));
        goals[0] = new Goal(getWidth()/2, getHeight()/2,gradius);
        for(int i = 0; i < manyfaces; i++) {
            faces.add(new Face(vplus));
        }
        t = new Thread(this);
        resize(500,500);
        t.start();
    }

    public void newFace() {
        for (int i = 0; i < manyfaces; i++) {
            faces.add(new Face(vplus));
        }
    }

    public void run(){
        try{
            while (true){
                repaint();
                t.sleep(12);
                for(int i = 0; i < faces.size(); i++){
                    for(int j = 0; j < paddles.length; j++){
                        faces.get(i).move(getHeight(), getWidth());
                        if(leftmove == true){
                            paddles[j].theta -= .05;
                            paddles[j].movePaddle();
                        }
                        if(rightmove == true){
                            paddles[j].theta += .05;
                            paddles[j].movePaddle();
                        }
                        if(faces.get(i).x+10 >= paddles[j].x 
                        && faces.get(i).x <= paddles[j].x+30
                        && faces.get(i).y+10 >= paddles[j].y
                        && faces.get(i).y <= paddles[j].y+30){
                            faces.remove(i);
                            manyfaces--;
                            if (manyfaces == 0){
                                faces.add(new Face(vplus));
                            }
                            manyfaces++;
                            gradius += 5;
                        }
                    }

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
            g.setColor(Color.cyan);
            g.fillOval( (int)(paddles[i].x) - 15
            , (int)(paddles[i].y) - 15
            , 30
            , 30);
            //g.setColor(Color.cyan);
            //g.fillOval( (int)(paddles[i].x)
            //    , (int)(paddles[i].y)
            //    , 22
            //    , 22);
        }
        for (int i = 0; i < goals.length; i++){
            g.setColor(Color.red);
            g.fillOval( (int)(goals[i].x) - goals[i].radius
            , (int)(goals[i].y) - goals[i].radius
            , (int)(goals[i].radius) * 2
            , (int)(goals[i].radius) * 2);
        }
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyChar() == 'a'){
            leftmove = true;
        }
        if (e.getKeyChar() == 'd') {
            rightmove = true;
        }
        if (e.getKeyChar() == 'j') {
            for (int i = 0; i < paddles.length; i++) {
                paddles[i].theta += Math.PI;
                paddles[i].movePaddle();
            }
        }
    }

    public void keyReleased(KeyEvent e){
        if (e.getKeyChar() == 'a'){
            leftmove = false;
        }
        if (e.getKeyChar() == 'd') {
            rightmove = false;
        }

    }
}
