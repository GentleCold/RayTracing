package rtc.render;

import rtc.utils.Ray;
import rtc.utils.Vec3;

public record Camera(double viewportWidth, double viewportHeight, double focalLength) {
    // default position is (0, 0, 0)
    public static final Vec3 origin = new Vec3();

    public Vec3 horizontal() { return new Vec3(viewportWidth, 0, 0); }

    public Vec3 vertical() { return new Vec3(0, viewportHeight, 0); }

    public Vec3 lowerLeftCorner() {
        return new Vec3(origin.x() - viewportWidth / 2, origin.y() - viewportHeight / 2, origin.z() - focalLength);
    }

    /**
     * get a ray according to ratio
     * @param u ratio of img width
     * @param v ratio of img height
     */
    public Ray getRay(double u, double v) {
        // ray: corner + u * x + v * y - origin
        return new Ray(Camera.origin, this.lowerLeftCorner()
                        .add(this.horizontal().multiply(u))
                        .add(this.vertical().multiply(v))
                        .subtract(Camera.origin));
    }
}
