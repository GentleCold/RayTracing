package rtc.material;

import rtc.utils.HitInfo;
import rtc.utils.Ray;
import rtc.utils.Vec3;

public class Dielectric extends Material {
    private final double eta;
    /**
     * @param eta index of refraction
     */
    public Dielectric(double eta) {
        // map to [0, 1]
        this.albedo = new Vec3(1, 1, 1);
        this.eta = eta;
    }

    @Override
    public Ray scatter(Ray r, HitInfo h) {
        double oneEta = h.ifFrontFace ? 1 / eta : eta;
        Vec3 unitDirection = r.direction().normalize();
        double cos = -unitDirection.dot(h.normal);
        // refraction
        if (Math.sqrt(1 - cos * cos) * oneEta < 1) {
            Vec3 rh = h.normal.multiply(cos).add(unitDirection).multiply(oneEta);
            Vec3 rv = h.normal.multiply(-Math.sqrt(1 - rh.lengthSquared()));
            return new Ray(h.point, rh.add(rv));
        } else { // reflection
            Vec3 doubleNormal = h.normal.multiply(h.normal.dot(unitDirection) * 2);
            return new Ray(h.point, unitDirection.subtract(doubleNormal));
        }
    }
}
