import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.applet.*;

public class kanye extends JApplet implements Runnable, KeyListener
{
    Thread t;
    Paddle []paddles = new Paddle[1];
    Goal []goals = new Goal[1];
    double v, theta;
    ArrayList<Face>faces = new ArrayList();
    Image sammy;
    Image papa;
    int manyfaces = 1;
    int vplus;
    boolean leftmove = false;
    boolean rightmove = false;
    boolean loseScreen = false;
    String debug = "";
    Random r = new Random();
    int samboy = r.nextInt(7)+1;
    int a = 0;//sides
    int sound = 0;
    boolean hardmode = false;
    int score = 0;
    AudioClip song;
    AudioClip whistle;
    AudioClip foghorn;
    public void init(){
        setFocusable(true);
        addKeyListener(this);
        vplus = 0;
        paddles[0] = new Paddle(225, 250, (double)(60));
        goals[0] = new Goal(getWidth()/2, getHeight()/2,40);
        song = getAudioClip(getDocumentBase(), "imagod.wav");
        whistle = getAudioClip(getDocumentBase(), "ooo.wav");
        foghorn = getAudioClip(getDocumentBase(), "foghorn.wav");
        try
        {
            papa = ImageIO.read(getClass().getClassLoader().getResource("john.png"));
        } catch (Exception e)
        {
            e.printStackTrace(); debug += "Fix ";
        }
        song.play();
        newface();
        t = new Thread(this);
        resize(500,500);
        t.start();
    }

    public void newface(){
        if (score >= 30) hardmode = true;
        if (hardmode == true) manyfaces = 300;
        for(int i = 0; i < manyfaces; i++) {
            faces.add(new Face(vplus,a));
        }
    }

    public void run(){
        try{
            while (true){
                repaint();
                t.sleep(10);
                for(int i = 0; i < faces.size(); i++){
                    for(int j = 0; j < paddles.length; j++){
                        faces.get(i).move(getHeight(), getWidth());
                        if (faces.get(i).bounces >= 4) foghorn.play();
                        if(leftmove == true){
                            paddles[j].theta -= .07;
                            paddles[j].movePaddle();
                        }
                        if(rightmove == true){
                            paddles[j].theta += .07;
                            paddles[j].movePaddle();
                        }
                        if(faces.get(i).x+60 >= paddles[j].x-15 
                        && faces.get(i).x <= paddles[j].x+15
                        && faces.get(i).y+60 >= paddles[j].y-15
                        && faces.get(i).y <= paddles[j].y+15){
                            faces.remove(i);
                            whistle.play();
                            manyfaces--;
                            if (manyfaces == 0){
                                samboy = r.nextInt(7) + 1;
                                manyfaces++;
                                newface();
                                score++;
                            }
                            a = r.nextInt();
                            //goals[0].radius += 5;
                            //paddles[j].r += 5;
                            //vplus++;
                        }
                        if(faces.get(i).x+60 >= goals[0].x-goals[0].radius
                        && faces.get(i).x <= goals[0].x+goals[0].radius
                        && faces.get(i).y+60 >= goals[0].y-goals[0].radius
                        && faces.get(i).y <= goals[0].y+goals[0].radius){
                            faces.remove(i);
                            loseScreen = true;
                        }
                    }

                }

            }
        }catch (InterruptedException e) {}
        try{
            while (true){
                t.sleep(31000);
                song.play();
            }
        }catch (InterruptedException e) {}
        repaint();
    }

    public void clearScreen() {
        loseScreen = false;
        if (faces.size() > 0) faces.remove(0);
        vplus = 0;
        samboy=r.nextInt(7)+1;
        a=r.nextInt();
        newface();
        score = 0;
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
        if (hardmode == false)
            g.setColor(Color.white);
        else if (hardmode == true)
            g.setColor(Color.black);
        g.fillRect(0,0,500,500);
        for (int i = 0; i < faces.size(); i++){
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
        }
        for (int i = 0; i < faces.size(); i++) {
            g.drawImage(sammy, (int)(faces.get(i).x), (int)(faces.get(i).y), this);
        }
        //         g.setColor(Color.black); //old faces
        //         for(int i = 0; i < faces.size(); i++){
        //             g.fillOval((int)faces.get(i).x,(int)faces.get(i).y,10,10);
        //         }
        g.setColor(Color.black);
        g.drawString("Don't let Sam eat the pizza!",165,50);
        g.drawString("'a' and 'd' to move", 192,70);
        g.drawString("'j' to jump across the zone",167,90);
        g.drawString("Calories saved: " + score*2900 , 195, 110);
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
            g.setColor(Color.black);//outline
            g.fillOval( ((int)(goals[i].x) - goals[i].radius) - 3
            , ((int)(goals[i].y) - goals[i].radius) - 3
            , ((int)(goals[i].radius) * 2) + 6
            , ((int)(goals[i].radius) * 2) + 6);

            g.setColor(Color.red);//goal
            g.fillOval( (int)(goals[i].x) - goals[i].radius
            , (int)(goals[i].y) - goals[i].radius
            , (int)(goals[i].radius) * 2
            , (int)(goals[i].radius) * 2);
        }
        g.drawImage(papa, getWidth()/2 - 28, getHeight()/2 - 28, this);
        g.setColor(Color.black);
        //         g.drawString("Zone",235,253);
        if (loseScreen == true){
            g.drawString("You Lose! Press 'r' to restart", 166,130);
        }
        showStatus("Samboy: " + samboy);
    }

    public void keyTyped(KeyEvent e){
        if (e.getKeyChar() == 'r') {
            clearScreen();
        }
        if (e.getKeyChar() =='h') {
            hardmode = !hardmode;
        }
    }

    public void keyPressed(KeyEvent e){
        if (loseScreen != true) {
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
