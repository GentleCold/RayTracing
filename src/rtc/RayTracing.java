package rtc;

import rtc.render.Camera;
import rtc.render.Stage;
import rtc.utils.Ray;
import rtc.utils.Vec3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Main process of raytracing.
 * @author GentleCold
 */
public class RayTracing {
    private final Camera camera;
    private final Stage stage;
    private final int imageWidth;
    private final int imageHeight;
    private final int[] imageData;

    public RayTracing(Camera camera, Stage stage) {
        this(1920, 1080, camera, stage);
    }

    public RayTracing(int imageWidth, int imageHeight, Camera camera, Stage stage) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.camera = camera;
        this.stage = stage;
        imageData = new int[imageWidth * imageHeight];
    }

    /**
     * render and store the img
     * @param fileLoc Location of png to store the result
     */
    public void run(final String fileLoc) {
        int pixel = 0;
        for (int j = imageHeight; j > 0; j--) {
            for (int i = 0; i < imageWidth; i++) {
                double u = (double)i / imageWidth;
                double v = (double)j / imageHeight;

                int pixelData = _rayColor(new Ray(Camera.origin, camera.lowerLeftCorner()
                        .add(camera.horizontal().multiply(u))
                        .add(camera.vertical().multiply(v))
                        .subtract(Camera.origin)));
                imageData[pixel++] = pixelData;
            }
        }

        System.out.println("Render finish, writing to file...");
        try {
            _output(fileLoc);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private int _rayColor(Ray r) {
        Vec3 d = r.direction().normalize();
        var t = 0.5 * (d.y() + 1.0);
        return _vec2Int(new Vec3(1, 1, 1).multiply(1 - t).add(new Vec3(0.5, 0.7, 1.0).multiply(t)));
    }

    private void _output(final String fileLoc) throws IOException {
        File outputFile = new File(fileLoc);
        if (!outputFile.exists()) {
            System.out.println("File " + fileLoc + " does not exist, creating file...");
            if (outputFile.createNewFile()) System.out.println("create " + fileLoc + " successfully!");
            else { System.out.println("create " + fileLoc + " failed!"); return; }
        }
        BufferedImage bufferImg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        bufferImg.setRGB(0, 0, imageWidth, imageHeight, imageData, 0, imageWidth);
        if (ImageIO.write(bufferImg, "png", outputFile)) {
            System.out.println("write " + fileLoc + " successfully! :)");
        } else {
            System.out.println("write " + fileLoc + " failed!");
        }
    }

    private int _vec2Int(Vec3 color) {
        int r = (int)(255 * color.x());
        int g = (int)(255 * color.y());
        int b = (int)(255 * color.z());
        return (r << 16) | (g << 8) | b;
    }
}
