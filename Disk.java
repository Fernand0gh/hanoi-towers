import java.awt.*;

public class Disk extends Rectangle{
    private int n;
    private Color color;
    public Disk(int n ,Color color){
        super(n * 35, 30);
        this.color = color;
        this.n = n;
    }

    public int getN(){
        return n - 1;
    }
    public Color getColor(){
        return color;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    //Estos metodos son una alternativa a los que ya vienen en la clase rectangle, porque esos son de tipo double.
    //These methods are an alternative to the ones that come in the rectangle class, cause those are of type double.
    public int getXInt(){
        return x;
    }
    public int getYInt(){
        return y;
    }
    public int getWidthInt(){
        return width;
    }
    public int getHeightInt(){
        return height;
    }
}
