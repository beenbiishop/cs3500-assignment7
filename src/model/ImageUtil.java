package model;

import javax.imageio.ImageIO;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


/**
 * This class contains utility methods to read a PPM image from file and create image object, as
 * well as creating a file from an image object.
 */
public class ImageUtil {


  /**
   * Read an image file in the PPM format and return an Image object.
   *
   * @param filepath file path location.
   * @return image representation of image file.
   * @throws IllegalArgumentException if the file can not be found.
   */
  public static Image readPPM(String filepath) throws IllegalArgumentException {
    int width;
    int height;
    int maxValue;

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filepath));
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      }

      //now set up the scanner to read from the string we just built
      sc = new Scanner(builder.toString());

      String token;

      token = sc.next();
      if (!token.equals("P3")) {
        throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
      }
      width = sc.nextInt();
      System.out.println("Width of image: " + width);
      height = sc.nextInt();
      System.out.println("Height of image: " + height);
      maxValue = sc.nextInt();
      System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

      int[][][] imageArray = new int[height][width][3];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          imageArray[i][j][0] = r;
          int g = sc.nextInt();
          imageArray[i][j][1] = g;
          int b = sc.nextInt();
          imageArray[i][j][2] = b;
        }
      }

      return new Image(imageArray, maxValue);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filepath + " not found!");
    }

  }

  /**
   * Takes an image and creates a ppm file of image at given path location.
   *
   * @param image given image object
   * @param path  designation path location
   * @throws IOException if fails to write file.
   */
  public static void makePPMFile(Image image, String path) throws IOException {
    Appendable builder = new StringBuilder();
    builder.append("P3" + System.lineSeparator());
    builder.append(image.getWidth() + System.lineSeparator());
    builder.append(image.getHeight() + System.lineSeparator());
    builder.append("255" + System.lineSeparator());
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        builder.append(image.getRed(i, j) + System.lineSeparator());
        builder.append(image.getGreen(i, j) + System.lineSeparator());
        builder.append(image.getBlue(i, j) + System.lineSeparator());
      }
    }

    FileOutputStream outputStream = new FileOutputStream(path);
    String str = builder.toString();
    outputStream.write(str.getBytes());
    outputStream.close();
  }

  /**
   * Takes in a image and reads it into an array.
   * @param filename The name of the file.
   * @return an imageArray of the photo.
   */
  public static Image readImage(String filename) {
    int splitIndex = filename.lastIndexOf('.');
    String extension = (splitIndex == -1) ? "" : filename.substring(splitIndex + 1);
    if (extension.equals("ppm")) {
      return readPPM(filename);
    } else {
      try {
        BufferedImage bImage = ImageIO.read(new File(filename));
        if (bImage == null) {
          throw new IOException("Invalid file type");
        }
        int[][][] imageArray = new int[bImage.getHeight()][bImage.getWidth()][3];
        for (int i = 0; i < bImage.getHeight(); i++) {
          for (int j = 0; j < bImage.getWidth(); j++) {
            Color pixel = new Color(bImage.getRGB(j, i));

            imageArray[i][j][0] = pixel.getRed();
            imageArray[i][j][1] = pixel.getGreen();
            imageArray[i][j][2] = pixel.getBlue();
          }
        }

        return new Image(imageArray, 255);
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  /**
   * Takes an image and creates a file of image at given path location.
   * File type is determined by the extension.
   *
   * @param image given image object
   * @param path  designation path location
   * @throws IOException if fails to write file.
   */
  public static void makeFile(Image image, String path) throws IOException {
    int splitIndex = path.lastIndexOf('.');
    String extension = (splitIndex == -1) ? "" : path.substring(splitIndex + 1);
    if (extension.equals("ppm")) {
      makePPMFile(image, path);
    } else {
      BufferedImage bImage = imageToImage(image);
      File outputFile = new File(path);
      if (!ImageIO.write(bImage, extension, outputFile)) {
        throw new IOException("Invalid file extension");
      }
    }
  }

  /**
   * Buffers and image.
   * @param image the photo passed in.
   * @return the buffered image.
   */
  public static BufferedImage imageToImage(Image image) {

    BufferedImage bImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color currentPixel = new Color(image.getRed(i, j), image.getGreen(i, j),
                image.getBlue(i, j));

        bImage.setRGB(j, i, currentPixel.getRGB());
      }
    }
    return bImage;
  }
}
