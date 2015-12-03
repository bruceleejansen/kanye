public class Paddle{
    double x;
    double y;
    double m;//width
    double n;//height
    double theta = 1;
    double r = 100;
    Paddle(double x1, double y1){
        x = x1;
        y = y1;
        m = 250;
        n = 250;
    }
    public void movePaddle() {
//         theta = theta - 1;
        x = m + r * Math.cos(theta);
        y = n + r * Math.sin(theta);
    }
   
}
