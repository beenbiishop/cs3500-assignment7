package view;

import java.io.IOException;

/**
 * The interface for the features.
 */
public interface Features {

  void load(String filePath, String fileName) throws IOException;

  void save(String filePath, String fileName) throws IOException;

  void blue(String imageName, String destName);

  void red(String imageName, String destName);

  void green(String imageName, String destName);

  void blur(String imageName, String destName);

  void brighten(int increment, String imageName, String destName);

  void darken(int increment, String imageName, String destName);

  void grayscale(String imageName, String destName);

  void horizontal(String imageName, String destName);

  void vertical(String imageName, String destName);

  void intensity(String imageName, String destName);

  void luma(String imageName, String destName);

  void sepia(String imageName, String destName);

  void sharpen(String imageName, String destName);

  void value(String imageName, String destName);

  void quit();

  // TODO: Document new method

  /**
   * Transforms the given image into a mosaic using the given number of seeds.
   *
   * @param imageName the name of the image to be transformed.
   * @param destName  the name of the new image after it has been transformed.
   * @param numSeeds  the number of seeds to use for the mosaic.
   */
  void mosaic(String imageName, String destName, int numSeeds);

}
