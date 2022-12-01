package model;

import java.util.Objects;

/**
 * This class represents an Image object. For readability purposes, each array of integers in the 3d
 * array is representative of the RGB components of a pixel in the image.
 */
public class Image {

  private final int[][][] image;
  private final int height;
  private final int width;

  private final int maxValue;

  /**
   * representation of an image.
   *
   * @param imageArray 3d array representation of the image
   * @param maxValue   the value for all color channels.
   */
  public Image(int[][][] imageArray, int maxValue) {
    this.image = imageArray;
    this.height = this.image.length;
    this.width = this.image[0].length;
    this.maxValue = maxValue;

  }


  /**
   * Get integer value of the red component of pixel in the given location of the image's 3d array
   * representation.
   *
   * @param height height location of pixel
   * @param width  width location of pixel
   * @return red component integer value
   */
  public int getRed(int height, int width) {
    return this.image[height][width][0];
  }

  /**
   * Get integer value of the green component of pixel in the given location of the image's 3d array
   * representation.
   *
   * @param height height location of pixel
   * @param width  width location of pixel
   * @return green component integer value
   */
  public int getGreen(int height, int width) {
    return this.image[height][width][1];
  }

  /**
   * Get integer value of the blue component of pixel in the given location of the image's 3d array
   * representation.
   *
   * @param height height location of pixel
   * @param width  width location of pixel
   * @return blue component integer value
   */
  public int getBlue(int height, int width) {
    return this.image[height][width][2];
  }


  /**
   * Gets the height location of a pixel on the image.
   *
   * @return height integer value
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the width location of a pixel on the image.
   *
   * @return width integer value
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the maxValue of the RGB components on the image.
   *
   * @return max integer value of image
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  public int[][][] getImage() {
    return this.image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == this) {
      return true;
    }
    if (!(o instanceof Image)) {
      return false;
    }
    Image other = (Image) o;
    return this.maxValue == other.maxValue && this.image == other.image
        && this.height == other.height && this.width == other.width;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.image, this.height, this.width, this.maxValue);
  }

}
