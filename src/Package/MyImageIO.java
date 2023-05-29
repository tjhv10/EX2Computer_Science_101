package Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This is an attatched class for assignment 2 in introduction to computer science course,
 * Ariel University - 2021-2022.
 * @author Asaly Saed
 *
 */

public class MyImageIO {
    /**
     *
     * @param fileName - A path to an existing image in the PC (to be loaded). (Ex: "C://Documents and Settings/user1/Desktop/cat.jpg").
     * @return RGB image (img) represented as a 3D array, where img[0] - Red channel,
     * img[1] - Green channel, img[2] - Blue channel.
     */
    public static int[][][] readImageFromFile(String fileName) {
        try {
            BufferedImage im = ImageIO.read(new File(fileName));
            int[][][] img=new int[3][im.getHeight()][im.getWidth()];
            for (int i = 0; i < im.getHeight(); i++) {
                for (int j = 0; j < im.getWidth(); j++) {
                    Color c = new Color(im.getRGB(j, i));
                    img[0][i][j] =c.getRed();
                    img[1][i][j]=c.getGreen();
                    img[2][i][j]=c.getBlue();
                }
            }
            return img;
        } catch (IOException e) {
//			e.printStackTrace();
            System.err.println("Error: The given path is incorrect, please check it.");
        }
        return null;
    }
    /**
     *
     * @param fileName - A path to an image in the PC (to be saved). (Ex: "C://Documents and Settings/user1/Desktop/catCopy").
     * @param pixels - RGB image represented as a 3D array to be saved in the given path.
     */
    public static void writeImageToFile(String fileName, int[][][] pixels) {
        BufferedImage im = new BufferedImage(pixels[0][0].length, pixels[0].length, BufferedImage.TYPE_INT_RGB);
        Graphics g = im.createGraphics();
        for (int i = 0; i < pixels[0].length; i++) {
            for (int j = 0; j < pixels[0][0].length; j++) {
                g.setColor(new Color(pixels[0][i][j],pixels[1][i][j],pixels[2][i][j]));
                g.fillRect(j, i, 1, 1);
            }
        }
        try {
            fileName = fileName + ".jpg";
            ImageIO.write(im, "jpg", new File(fileName));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param fileName - A path to an  image in the PC.
     * (Example: "C://Documents and Settings/user1/Desktop/catCopy" - where "catCopy" is the name of the new image).
     * @param pixels - gray-scale image represented as a 2D array to be saved in the given path.
     */
    public static void writeImageToFile(String fileName, int[][] pixels) {
        BufferedImage im = new BufferedImage(pixels[0].length, pixels.length, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                im.setRGB(j, i,pixels[i][j]);
            }
        }
        try {
            fileName = fileName + ".jpg";
            ImageIO.write(im, "jpg", new File(fileName));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
