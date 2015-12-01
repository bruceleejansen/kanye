public class Face{
    double x, y, v, theta;
    Face(int vplus){
        x=(Math.random())*50;
        y=(Math.random())*500;
        v=(Math.random())*5+vplus;
        v=(Math.random())*5 + vplus;

        theta=(Math.random())*.7+.2;
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
