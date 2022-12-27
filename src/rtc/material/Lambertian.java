package rtc.material;

import rtc.utils.HitInfo;
import rtc.utils.Ray;
import rtc.utils.Vec3;

public class Lambertian extends Material{
    /**
     * @param albedo rgb, [0, 255]
     */
    public Lambertian(Vec3 albedo) {
        // map to [0, 1]
        this.albedo = albedo.divide(255);
    }

    @Override
    public Ray scatter(Ray r, HitInfo h) {
        Vec3 target = _randomUnitVec().add(h.point.add(h.normal));
        Vec3 direction = target.subtract(h.point);
        // close to zero may cause NAN
        if (direction.ifNearZero()) direction = h.normal;
        return new Ray(h.point, direction);
    }
}
