package rtc.shape;

import rtc.material.Material;
import rtc.utils.HitInfo;
import rtc.utils.Ray;

public abstract class Shape {
    public Material m;
    public HitInfo h = new HitInfo();
    /**
     * judge whether ray hit the shape and return info
     * @param r ray
     * @param minScale minimum of answer of equation, usually is 0
     * @param maxScale maximum of answer of equation, first hit scale
     * @return if hit
     */
    public abstract boolean ifHit(Ray r, double minScale, double maxScale);
}
