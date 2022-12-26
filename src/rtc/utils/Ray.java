package rtc.utils;

public record Ray(Vec3 origin, Vec3 direction) {
    /**
     * calculate origin + t * direction
     * @param t scale
     * @return point position
     */
    public Vec3 at(double t) {
        return origin.add(direction.multiply(t));
    }
}
