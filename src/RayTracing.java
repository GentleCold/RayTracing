import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class RayTracing {
    public static void main(String[] args) {
        try {
            new RayTracing().outPut();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    final int WIDTH = 256;
    final int HEIGHT = 256;
    final int imageSize = WIDTH * HEIGHT;
    int[] imageData = new int[imageSize];

    void outPut() throws IOException {
        String outputFileLoc = "img/img1.png";
        File outputFile = new File(outputFileLoc);
        if (!outputFile.exists())
            if (outputFile.createNewFile()) System.out.println("create " + outputFileLoc + " successfully!");
            else System.out.println("create " + outputFileLoc + " failed!");

        int pixel = 0;
        for (int i = HEIGHT; i > 0; i--) {
            for (int j = 0; j < WIDTH; j++) {
                double r = (double)i / HEIGHT;
                double g = (double)j / WIDTH;
                double b = 0.25;

                int ir = (int)(r * 255);
                int ig = (int)(g * 255);
                int ib = (int)(b * 255);

                int pixelValue = (ir << 16) | (ig << 8) | ib;
                imageData[pixel++] = pixelValue;
            }
        }

        BufferedImage bufferImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        bufferImg.setRGB(0, 0, WIDTH, HEIGHT, imageData, 0, WIDTH);
        ImageIO.write(bufferImg, "png", outputFile);
    }
}
