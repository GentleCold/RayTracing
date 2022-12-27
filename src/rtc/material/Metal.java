package rtc.material;

import rtc.utils.HitInfo;
import rtc.utils.Ray;
import rtc.utils.Vec3;

public class Metal extends Material {
    private final double fuzz;
    /**
     * @param albedo rgb, [0, 255]
     * @param fuzz blur, [0, 1]
     */
    public Metal(Vec3 albedo, double fuzz) {
        // map to [0, 1]
        this.albedo = albedo.divide(255);
        this.fuzz = fuzz;
    }

    @Override
    public Ray scatter(Ray r, HitInfo h) {
        Vec3 unitDirection = r.direction().normalize();
        // little disturbance
        Vec3 fuzzed = _randomUnitVec().multiply(fuzz);
        Vec3 doubleNormal = h.normal.multiply(h.normal.dot(unitDirection) * 2).add(fuzzed);
        return new Ray(h.point, unitDirection.subtract(doubleNormal));
    }
}
