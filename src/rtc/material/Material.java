package rtc.material;

import rtc.utils.HitInfo;
import rtc.utils.Ray;
import rtc.utils.Vec3;

public abstract class Material {
    public Vec3 albedo;
    public abstract Ray scatter(Ray r, HitInfo h);
    protected Vec3 _randomUnitVec() {
        while (true) {
            Vec3 v = new Vec3(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1);
            if (v.lengthSquared() > 1) continue;
            return v;
        }
    }
}
