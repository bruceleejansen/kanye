public class Ball{
    double x, y, v, theta;
    Ball(){
        x=(Math.random())*50;
        y=(Math.random())*500;
        v=(Math.random())*5+7;
        theta=(Math.random())*.7+.2;
    }

    public void move(){
        x = x + v * Math.cos(theta);
        y = y + v * Math.sin(theta);
        if (x >= 500){
            theta = Math.PI - theta;
        }
        if (y <= 1 || y >= 500){
            theta = theta *-1;
        }
        
    }
}
