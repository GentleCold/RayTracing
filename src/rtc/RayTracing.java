package rtc;

import rtc.material.Material;
import rtc.render.Camera;
import rtc.shape.ShapeList;
import rtc.utils.HitInfo;
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
    private final ShapeList shapeList;
    private final int imageWidth;
    private final int imageHeight;
    private final int pixelSamples;
    private final int depth;
    private final int[] imageData;

    /**
     * default width set to 1920, height set to 1080
     * @param camera camera config
     * @param shapeList shapeList config
     * @param pixelSamples pixel samples for antialiasing
     * @param depth max reflection times of ray
     */
    public RayTracing(Camera camera, ShapeList shapeList, int pixelSamples, int depth) {
        this(1920, 1080, camera, shapeList, pixelSamples, depth);
    }

    /**
     * @param imageWidth output img width
     * @param imageHeight output img height
     * @param camera camera config
     * @param shapeList shapeList config
     * @param pixelSamples pixel samples for antialiasing
     * @param depth max reflection times of ray
     */
    public RayTracing(int imageWidth, int imageHeight, Camera camera, ShapeList shapeList, int pixelSamples, int depth) {
        assert pixelSamples > 0; // must be greater than 1
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.camera = camera;
        this.shapeList = shapeList;
        this.pixelSamples = pixelSamples;
        this.depth = depth;
        imageData = new int[imageWidth * imageHeight];
    }

    /**
     * render and store the img
     * @param fileLoc Location of png to store the result
     */
    public void run(final String fileLoc) {
        long startTime = System.currentTimeMillis();
        /* -------------------- start render -------------------- */
        int pixel = 0;
        // silly1: must generate img from left top corner per line
        for (int j = imageHeight - 1; j >= 0; j--) {
            System.out.print('\r');
            System.out.print("remain: " + j);
            for (int i = 0; i < imageWidth; i++) {
                Vec3 color = new Vec3();
                // antialiasing
                for (int k = 0; k < pixelSamples; k++) {
                    // silly4: must generate a random ray every time
                    double u = (i + Math.random()) / (imageWidth - 1);
                    double v = (j + Math.random()) / (imageHeight - 1);
                    color = color.add(_rayColor(camera.getRay(u, v), depth)); // silly3: must override
                }
                imageData[pixel++] = _rgb2Int(color.divide(pixelSamples));
            }
        }
        /* -------------------- end render -------------------- */
        long endTime = System.currentTimeMillis();
        System.out.printf("Render finish in %d ms, writing to file...\n", endTime - startTime);

        try {
            _output(fileLoc);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    /**
     * calculate the color according to ray
     * @param r ray
     * @return rgb vector
     */
    private Vec3 _rayColor(Ray r, int leftDepth) {
        if (leftDepth <= 0) {
            return new Vec3(); // no color
        }
        // hit render, set 0.001 to avoid shadow acne
        if (shapeList.ifHit(r, 0.001, Double.POSITIVE_INFINITY)) {
            HitInfo h = shapeList.h;
            Material m = shapeList.m;
            Ray reflectRay = m.scatter(r, h);
            return _rayColor(reflectRay, leftDepth - 1).multiply(m.albedo); // simulate light absorption
        }
        // background render, linear gradient
        Vec3 e = r.direction().normalize();
        var t = 0.5 * (e.y() + 1.0);
        return new Vec3(1, 1, 1).multiply(1 - t).add(new Vec3(0.5, 0.7, 1.0).multiply(t));
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

    private int _rgb2Int(Vec3 color) {
        // gamma correction, gamma = 2
        int r = (int)(255 * Math.sqrt(color.x()));
        int g = (int)(255 * Math.sqrt(color.y()));
        int b = (int)(255 * Math.sqrt(color.z()));
        return (r << 16) | (g << 8) | b;
    }
}
