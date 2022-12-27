package rtc.shape;

import rtc.utils.Ray;

import java.util.ArrayList;

public class ShapeList extends Shape {
    private final ArrayList<Shape> shapeList = new ArrayList<>();
    public ShapeList() {}
    public void add(Shape s) {
        shapeList.add(s);
    }

    @Override
    public boolean ifHit(Ray r, double minScale, double maxScale) {
        boolean ifHit = false;
        double firstHitScale = maxScale;
        for (Shape s : shapeList) {
            if (s.ifHit(r, minScale, firstHitScale)) {
                this.m = s.m;
                this.h = s.h;
                firstHitScale = h.t;
                ifHit = true;
            }
        }
        return ifHit;
    }
}
