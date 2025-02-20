package minicraft.graphic;

public class Point {

    public int x;
    public int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point model) {
        x = model.x;
        y = model.y;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point)) {
            return false;
        }
        
        Point o = (Point) other;
        return x == o.x && y == o.y;
    }

    public void translate(int xoff, int yoff) {
        x += xoff;
        y += yoff;
    }
    
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    @Override
    public int hashCode() {
        return x * 71 + y;
    }
}
