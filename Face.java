import java.util.*;
public class Face{
    double x, y, v, theta;
    int b;
    int bounces = 0;
    Face(int vplus, int a){
        bounces = 0;
        b=a;
        if (a == 0){
            x=(Math.random())*50;
            y=(Math.random())*500;
            v=3+(0.3 * vplus) + (0.1 * bounces);
            theta=(Math.random()) * (500 - y) + (50 - x);
        }
        else{
            x=500-((Math.random())*50);
            y=(Math.random())*500;
            v=3+(0.3 * vplus) + (0.1 * bounces);
            theta=(Math.random()) * (500 - y) + (50 - x);
        }
    }

    public void move(int height, int width){
        x = x + v * Math.cos(theta);
        y = y + v * Math.sin(theta);
        if (x <= 1 || x >= width){
            bounces++;
            theta = Math.PI - theta;
            if (x <= 1) x = 2;
            if (x >= width) x = width-1;
        }
        if (y <= 1 || y >= height){
            bounces++;
            theta = theta *-1;
            if (y <=1) y = 2;
            else if (y >= height) y = height-1;
        }
        if (bounces > 6) {
            theta += 1;
            bounces = 0;
        }
    }
}
