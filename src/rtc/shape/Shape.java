package rtc.shape;

import rtc.utils.HitInfo;
import rtc.utils.Ray;

abstract class Shape {
    /**
     * judge whether ray hit the shape and return info
     * @param r ray
     * @param minScale minimum of answer of equation, usually is 0
     * @param maxScale maximum of answer of equation, first hit scale
     * @param h record the hitInfo
     * @return if hit
     */
    abstract boolean ifHit(Ray r, double minScale, double maxScale, HitInfo h);
}
