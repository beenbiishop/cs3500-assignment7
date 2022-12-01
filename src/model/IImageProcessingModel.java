package model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is an interface that represents the operations offered by the Image Processing model. An
 * object of the model represents an Image processing program.
 */
public interface IImageProcessingModel {

  /**
   * Loads the image into the program.
   *
   * @param path the path to find the image.
   * @param name the name of the image.
   * @throws IllegalArgumentException Throws an error if the image cannot be read or path is wrong.
   */
  void loadImage(String path, String name) throws IllegalArgumentException;

  /**
   * Saves the image linked to the inputted name to the inputted path.
   *
   * @param path Path to where the image should be saved.
   * @param name Name of the image.
   * @throws IOException              if the image cannot be saved because the path is invalid.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void saveImage(String path, String name) throws IOException, IllegalArgumentException;

  /**
   * Transforms the image corresponding to the inputted name to a horizontally flipped version and
   * stores the image to the given destination name.
   *
   * @param name     Path to the save location.
   * @param destName name of the new image after it has been stretched.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void transformHorizontal(String name, String destName) throws IllegalArgumentException;

  /**
   * Transforms the image corresponding to the inputted name to a vertically flipped version and *
   * stores the image to the given destination name.
   *
   * @param name     Path to the save location.
   * @param destName name of the new image after it has been stretched.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void transformVertical(String name, String destName) throws IllegalArgumentException;

  /**
   * Brightens an image corresponding to the given name by the given increment and stores the image
   * to the given destination name.
   *
   * @param increment the amount the image should be brightened.
   * @param name      the name of the image you want to be brightened.
   * @param destName  the name of the new image after it has been brightened.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void brightenImage(int increment, String name, String destName) throws IllegalArgumentException;

  /**
   * Darkens an image corresponding to the given name by the given increment and stores the image to
   * the given destination name.
   *
   * @param increment the amount the image should be darkened.
   * @param name      the name of the image you want to be brightened.
   * @param destName  the name of the new image after it has been brightened.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void darkenImage(int increment, String name, String destName) throws IllegalArgumentException;


  /**
   * Produces the greyscale version of the image with the RGB components being set to the red
   * component of the pixel.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void redComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the greyscale version of the image with the RGB components being set to the green
   * component of the pixel.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void greenComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the greyscale version of the image with the RGB components being set to the blue
   * component of the pixel.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void blueComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the greyscale version of the image with the RGB value being the luma value for each
   * pixel.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void lumaComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the greyscale version of the image with the RGB value being the intensity value for
   * each pixel, which is calculated as the average of the three components for each pixel.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void intensityComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the greyscale version of the image with the RGB value being the maximum value of the
   * three components for each pixel.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void valueComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the blurred version of the image with the blur filter.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void blurImage(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the sharpened version of the image with the sharpened filter.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void sharpenImage(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the greyscale version of the image with the color transformation.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void grayscaleImage(String name, String destName) throws IllegalArgumentException;

  /**
   * Produces the sepia version of the image with the color transformation.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void sepiaImage(String name, String destName) throws IllegalArgumentException;

  Map<String, Image> getImages();

  /**
   * Takes name, finds image stored to name and returns histogram list of integer arrays for its
   * components.
   *
   * @param name name of image to create histogram.
   * @return histogram list of integer arrays.
   */
  List<int[]> histogramList(String name);
}
