import rtc.RayTracing;
import rtc.material.Dielectric;
import rtc.material.Lambertian;
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
        Camera c = new Camera(2 * aspectRatio, 2, 1);
        // 2. add shapes
        // a sphere for example, center pos and radius was needed
        Vec3 center = new Vec3(0, 0, -1);
        Sphere shape1 = new Sphere(center, 0.5, new Lambertian(new Vec3(251, 122, 2)));
        center = new Vec3(-1, 0, -1);
        Sphere shape2 = new Sphere(center, 0.5, new Dielectric(1.5));
        center = new Vec3(0, -100.5, -1);
        Sphere shape3 = new Sphere(center, 100, new Metal(new Vec3(73, 156, 84), 0.5));
        // then add to ShapeList
        ShapeList s = new ShapeList();
        s.add(shape1);
        s.add(shape2);
        s.add(shape3);
        // 3. feed them to rtc and run!
        RayTracing rtc = new RayTracing((int)(400 * aspectRatio), 400, c, s, 100, 50);
        rtc.run("img/img1.png");
    }
}
