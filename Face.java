import java.util.*;
public class Face{
    double x, y, v, theta;
    int b;
    Face(int vplus, int a){
        b=a;
        if (a == 0){
            x=(Math.random())*50;
            y=(Math.random())*500;
            v=(Math.random())+3+vplus;
            theta=(Math.random()) * (500 - y) + (50 - x);
        }
        else{
            x=500-((Math.random())*50);
            y=(Math.random())*500;
            v=(Math.random())+3+vplus;
            theta=(Math.random()) * (500 - y) + (50 - x);
        }
    }

    public void move(int height, int width){
        x = x + v * Math.cos(theta);
        y = y + v * Math.sin(theta);
        if (x <= 1 || x >= width){
            theta = Math.PI - theta;
            if (x <= 1) x = 2;
            if (x >= width) x = width-1;
        }
        if (y <= 1 || y >= height){
            theta = theta *-1;
            if (y <=1) y = 2;
            else if (y >= height) y = height-1;
        }

    }
}
