import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

public class kanye extends JApplet implements Runnable, KeyListener
{
    Thread t;
    Paddle []paddles = new Paddle[1];
    Goal []goals = new Goal[1];
    double v, theta;
    ArrayList<Face>faces = new ArrayList();
    Image sammy;
    int manyfaces = 1;
    int vplus = 0;
    int gradius = 40;
    boolean leftmove = false;
    boolean rightmove = false;
    boolean loseScreen = false;
    int samboy = 1;
    String debug = "";
    Random r = new Random();
    Random g = new Random();
    int a = 0;
    public void init(){
        setFocusable(true);
        addKeyListener(this);
        paddles[0] = new Paddle(225, 250, (double)(gradius + 20));
        goals[0] = new Goal(getWidth()/2, getHeight()/2,gradius);
        for(int i = 0; i < manyfaces; i++) {
            faces.add(new Face(vplus,a));
        }
        t = new Thread(this);
        resize(500,500);
        t.start();
    }

    //     public void newFace() {
    //         for (int i = 0; i < manyfaces; i++) {
    //             faces.add(new Face(vplus));
    //         }
    //     }

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
                        if(faces.get(i).x+10 >= paddles[j].x-15 
                        && faces.get(i).x <= paddles[j].x+15
                        && faces.get(i).y+10 >= paddles[j].y-15
                        && faces.get(i).y <= paddles[j].y+15){
                            faces.remove(i);
                            manyfaces--;
                            if (manyfaces == 0){
                                samboy = r.nextInt() * 8;
                                faces.add(new Face(vplus,a));
                                
                            }
                            a = g.nextInt();
                            manyfaces++;
                            goals[0].radius += 5;
                            paddles[j].r += 5;
                            vplus += 1;
                        }
                        if(faces.get(i).x+10 >= goals[0].x-38
                        && faces.get(i).x <= goals[0].x+38
                        && faces.get(i).y+10 >= goals[0].y-38
                        && faces.get(i).y <= goals[0].y+38){
                            faces.remove(i);
                            loseScreen = true;
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
        if (samboy == 1) {try
            {
                sammy = ImageIO.read(getClass().getClassLoader().getResource("sampics/hat.png"));
            } catch (Exception e)
            {
                e.printStackTrace(); debug += "Fix ";
            }
        }
        if (samboy == 2) {try
            {
                sammy = ImageIO.read(getClass().getClassLoader().getResource("sampics/charlie.png"));
            } catch (Exception e)
            {
                e.printStackTrace(); debug += "Fix ";
            }
        }
        if (samboy == 3) {try
            {
                sammy = ImageIO.read(getClass().getClassLoader().getResource("sampics/dennis.png"));
            } catch (Exception e)
            {
                e.printStackTrace(); debug += "Fix ";
            }
        }
        if (samboy == 4) {try
            {
                sammy = ImageIO.read(getClass().getClassLoader().getResource("sampics/nolan.png"));
            } catch (Exception e)
            {
                e.printStackTrace(); debug += "Fix ";
            }
        }
        if (samboy == 5) {try
            {
                sammy = ImageIO.read(getClass().getClassLoader().getResource("sampics/poor.png"));
            } catch (Exception e)
            {
                e.printStackTrace(); debug += "Fix ";
            }
        }
        if (samboy == 6) {try
            {
                sammy = ImageIO.read(getClass().getClassLoader().getResource("sampics/fog.png"));
            } catch (Exception e)
            {
                e.printStackTrace(); debug += "Fix ";
            }
        }
        if (samboy == 7) {try
            {
                sammy = ImageIO.read(getClass().getClassLoader().getResource("sampics/happy.png"));
            } catch (Exception e)
            {
                e.printStackTrace(); debug += "Fix ";
            }
        }
        for (int i = 0; i < faces.size(); i++) {
            g.drawImage(sammy, (int)(faces.get(i).x), (int)(faces.get(i).y), this);
        }
        //         g.setColor(Color.black); //old faces
        //         for(int i = 0; i < faces.size(); i++){
        //             g.fillOval((int)faces.get(i).x,(int)faces.get(i).y,10,10);
        //         }
        g.setColor(Color.black);
        g.drawString("Don't let Sam into his zone!",165,50);
        g.drawString("'a' and 'd' to move", 192,70);
        g.drawString("'j' to jump across the zone",167,90);
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
        g.setColor(Color.black);
        g.drawString("Zone",235,253);
        if (loseScreen == true){
            g.drawString("You Lose!", 220,130);
        }
        showStatus("Debug state: " + debug);
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
