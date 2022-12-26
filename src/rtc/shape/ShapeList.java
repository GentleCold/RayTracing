package rtc.shape;

import rtc.utils.HitInfo;
import rtc.utils.Ray;

import java.util.ArrayList;

public class ShapeList extends Shape {
    private final ArrayList<Shape> shapeList = new ArrayList<>();
    public ShapeList() {}
    public void add(Shape s) {
        shapeList.add(s);
    }

    @Override
    public boolean ifHit(Ray r, double minScale, double maxScale, HitInfo h) {
        boolean ifHit = false;
        double firstHitScale = maxScale;
        for (Shape s : shapeList) {
            if (s.ifHit(r, minScale, firstHitScale, h)) {
                firstHitScale = h.t;
                ifHit = true;
            }
        }
        return ifHit;
    }
}
