package rtc.utils;

/**
 * Vector util in three dimension.
 * @author GentleCold
 */
public class Vec3 {
    private final double[] v;

    public Vec3() { v = new double[3]; }
    public Vec3(double v1, double v2, double v3) { v = new double[]{v1, v2, v3}; }

    public double x() { return v[0]; }
    public double y() { return v[1]; }
    public double z() { return v[2]; }

    public Vec3 add(Vec3 rv) { return new Vec3(v[0] + rv.x(), v[1] + rv.y(), v[2] + rv.z()); }
    public Vec3 subtract(Vec3 rv) { return new Vec3(v[0] - rv.x(), v[1] - rv.y(), v[2] - rv.z()); }
    public Vec3 multiply(Vec3 rv) { return new Vec3(v[0] * rv.x(), v[1] * rv.y(), v[2] * rv.z()); }
    public Vec3 divide(Vec3 rv) { return new Vec3(v[0] / rv.x(), v[1] / rv.y(), v[2] / rv.z()); }

    public Vec3 add(double rv) { return new Vec3(v[0] + rv, v[1] + rv, v[2] + rv); }
    public Vec3 subtract(double rv) { return new Vec3(v[0] - rv, v[1] - rv, v[2] - rv); }
    public Vec3 multiply(double rv) { return new Vec3(v[0] * rv, v[1] * rv, v[2] * rv); }
    public Vec3 divide(double rv) { return new Vec3(v[0] / rv, v[1] / rv, v[2] / rv); }

    public double lengthSquared() {
        return v[0] * v[0] + v[1] * v[1] + v[2] * v[2];
    }

    /**
     * Calculate the length, return a number.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Dot products, return a number.
     */
    public double dot(Vec3 rv) {
        return v[0] * rv.x() + v[1] * rv.y() + v[2] * rv.z();
    }

    /**
     * Cross products, return a vector.
     */
    public Vec3 cross(Vec3 rv) {
        return new Vec3(v[1] * rv.z() - v[2] * rv.y(),
                        v[2] * rv.x() - v[0] * rv.z(),
                        v[0] * rv.y() - v[1] * rv.x());
    }

    /**
     * Normalize, return a vector.
     */
    public Vec3 normalize() {
        var length = this.length();
        return new Vec3(v[0] / length, v[1] / length, v[2] / length);
    }
}
