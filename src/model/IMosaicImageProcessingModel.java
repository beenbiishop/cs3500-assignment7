package model;

/**
 * Represents the interface for an image processing model that supports the mosaic filter.
 */
public interface IMosaicImageProcessingModel extends IImageProcessingModel {

  /**
   * Produces a mosaic of the given image.
   *
   * @param name     the name of the image you want changed.
   * @param destName the name of the new image after it has been changed.
   * @param numSeeds the number of seeds to use for the mosaic.
   * @throws IllegalArgumentException if there is no image with given name.
   */
  void mosaicImage(String name, String destName, int numSeeds) throws IllegalArgumentException;

}
