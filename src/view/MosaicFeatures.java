package view;

/**
 * Represents the interface for a mosaic feature.
 */
public interface MosaicFeatures extends Features {

  /**
   * Transforms the given image into a mosaic using the given number of seeds.
   *
   * @param imageName the name of the image to be transformed.
   * @param destName  the name of the new image after it has been transformed.
   * @param numSeeds  the number of seeds to use for the mosaic.
   */
  void mosaic(String imageName, String destName, int numSeeds);
}
