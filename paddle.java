public class paddle{
    double x;
    double y;
    double m;//width
    double n;//height
    double theta = 1;
    double r = 1;
    paddle(double x1, double y1){
        x = x1;
        y = y1;
        m = 20;
        n = 20;
    }
    public void move() {
        theta = theta + .1;
        x = m + r * Math.cos(theta);
        y = n + r * Math.cos(theta);
    }
   
}
