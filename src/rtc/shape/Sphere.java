package rtc.shape;

import rtc.material.Material;
import rtc.utils.Ray;
import rtc.utils.Vec3;

public class Sphere extends Shape {
    private final Vec3 center;
    private final double radius;
    public Sphere(Vec3 center, double radius, Material m) {
        this.center = center;
        this.radius = radius;
        this.m = m;
    }

    @Override
    public boolean ifHit(Ray r, double minScale, double maxScale) {
        Vec3 oc = r.origin().subtract(center);
        // b^2 - 4ac
        var a = r.direction().lengthSquared();
        var halfB = oc.dot(r.direction()); // little optimize
        var c = oc.lengthSquared() - radius * radius;
        var delta = halfB * halfB - a * c;
        if (delta < 0) return false;
        // answer of equation
        delta = Math.sqrt(delta);
        var answer = (-halfB - delta) / a; // silly2: must be the minimum answer at first
        // in the correct limitation
        if (answer < minScale || answer > maxScale) {
            answer = (-halfB + delta) / a;
            if (answer < minScale || answer > maxScale) return false;
        }
        // set hit info
        Vec3 point = r.at(answer);
        h.setHitInfo(point, answer, r, point.subtract(center).normalize());
        return true;
    }
}
