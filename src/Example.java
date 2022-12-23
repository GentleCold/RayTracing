import rtc.RayTracing;
import rtc.render.Camera;
import rtc.render.Stage;

public class Example {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Camera c = new Camera(i * 16 / 9d, i, 1);
            Stage s = new Stage();
            RayTracing rtc = new RayTracing((int)(400 * (16 / 9d)), 400, c, s);
            rtc.run("img/img" + i + ".png");
        }
    }
}
