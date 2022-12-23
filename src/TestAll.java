import utils.Test;
import utils.Vec3;

/**
 * Test functions for accuracy.
 */
public class TestAll {
    public static void main(String[] args) {
        testVec3();

        Test.over();
    }

    static void testVec3() {
        Vec3 v1 = new Vec3(3, 4, 0);
        Vec3 v2 = new Vec3(-3, 4, 0);
        Vec3 v3 = new Vec3();
        Test.equalDouble(Double.POSITIVE_INFINITY, v1.divide(v3).x());
        Test.equalDouble(5, v1.length());
        Test.equalDouble(7, v1.dot(v2));
        Test.equalDouble(1, v1.cross(v2).normalize().z());
    }
}
