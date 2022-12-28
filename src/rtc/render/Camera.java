package rtc.render;

import rtc.utils.Ray;
import rtc.utils.Vec3;

public class Camera {
    private final Vec3 horizontal;
    private final Vec3 vertical;
    private final Vec3 lookFrom;
    private final Vec3 lowerLeftCorner;

    public Camera (
            Vec3 lookFrom, Vec3 lookAt, Vec3 vup, double aspectRatio,
            double viewportHeight, double vfov) {
        this.lookFrom = lookFrom;
        Vec3 w = lookFrom.subtract(lookAt).normalize();
        Vec3 u = vup.cross(w).normalize();
        Vec3 v = w.cross(u);
        double vfov1 = vfov / 360 * Math.PI;
        double height = viewportHeight * Math.tan(vfov1 / 2);
        double width = height * aspectRatio;
        this.horizontal = u.multiply(width);
        this.vertical = v.multiply(height);
        this.lowerLeftCorner = lookFrom.subtract(this.horizontal.divide(2)).subtract(this.vertical.divide(2)).subtract(w);
    }

    /**
     * get a ray according to ratio
     * @param u ratio of img width
     * @param v ratio of img height
     */
    public Ray getRay(double u, double v) {
        // ray: corner + u * x + v * y - origin
        return new Ray(this.lookFrom, this.lowerLeftCorner
                        .add(this.horizontal.multiply(u))
                        .add(this.vertical.multiply(v))
                        .subtract(this.lookFrom));
    }
}
