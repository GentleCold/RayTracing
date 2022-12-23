package rtc.render;

import rtc.utils.Vec3;

public record Camera(double viewportWidth, double viewportHeight, double focalLength) {
    // default position is (0, 0, 0)
    public static final Vec3 origin = new Vec3();

    public Vec3 horizontal() { return new Vec3(viewportWidth, 0, 0); }

    public Vec3 vertical() { return new Vec3(0, viewportHeight, 0); }

    public Vec3 lowerLeftCorner() {
        return new Vec3(origin.x() - viewportWidth / 2, origin.y() - viewportHeight / 2, origin.z() - focalLength);
    }
}
