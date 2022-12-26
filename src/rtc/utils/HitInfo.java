package rtc.utils;

/**
 * to record the info of hit thing
 */
public class HitInfo {
    public Vec3 point;
    public double t;
    public Vec3 normal; // normalized direction, against the ray direction
    public boolean ifFrontFace; // if the ray is coming from outside

    public HitInfo() {}

    public void setHitInfo(Vec3 point, double t, Ray r, Vec3 outwardNormal) {
        this.point = point;
        this.t = t;
        this.ifFrontFace = r.direction().dot(outwardNormal) < 0d;
        this.normal = ifFrontFace ? outwardNormal : outwardNormal.minus();
    }
}
