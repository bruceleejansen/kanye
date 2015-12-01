public class paddle{
    int m;
    int n;
    int width;
    int length;
    paddle(int x, int y){
        m = x;
        n = y;
        width = 20;
        length = 20;
    }
    public void movePaddle(int xmove, int ymove, double theta) {
        m += xmove;
        n += ymove;
        
    }
   
}
