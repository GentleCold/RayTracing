import rtc.RayTracing;
import rtc.material.Dielectric;
import rtc.material.Lambertian;
import rtc.material.Material;
import rtc.material.Metal;
import rtc.render.Camera;
import rtc.shape.ShapeList;
import rtc.shape.Sphere;
import rtc.utils.Vec3;

public class Example {
    public static void main(String[] args) {
        // aspect ratio is recommended
        double aspectRatio = 16 / 9d;
        // 1. config the camera, default position was (0, 0, 0) and face negative half z-axis
        Camera c = new Camera(new Vec3(-2, 2, 1), new Vec3(0, 0, -1), new Vec3(0, 1, 0), aspectRatio, 2, 180);
        // 2. add shapes
        // a sphere for example, center pos and radius was needed
        ShapeList s = new ShapeList();
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                Material m;
                double r = Math.random();
                if (r < 0.3) {
                    m = new Metal(new Vec3(Math.random() * 255, Math.random() * 255, Math.random() * 255), Math.random());
                } else if (r < 0.9) {
                    m = new Lambertian(new Vec3(Math.random() * 255, Math.random() * 255, Math.random() * 255));
                } else {
                    m = new Dielectric(1.5);
                }
                s.add(new Sphere(new Vec3(x * 2 + Math.random(), 0, -z * 2 + Math.random()), 0.5, m));
            }
        }
        Sphere bottom = new Sphere(new Vec3(0, -100.5, -1), 100, new Lambertian(new Vec3(240, 167, 50)));
        // then add to ShapeList
        s.add(bottom);
        // 3. feed them to rtc and run!
        RayTracing rtc = new RayTracing((int)(1080 * aspectRatio), 1080, c, s, 100, 50);
        rtc.run("img/img1.png");
    }
}
