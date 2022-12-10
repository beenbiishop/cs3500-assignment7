package controller.commands;

import controller.MosaicImageProcessingCommand;
import model.IMosaicImageProcessingModel;

/**
 * Represents a command to mosaic an image.
 */
public class Mosaic implements MosaicImageProcessingCommand {

  private final int seeds;
  private final String name;
  private final String destName;

  /**
   * Constructor for command to create a mosaic image by given seeds.
   *
   * @param seeds    integer to represent number of seeds.
   * @param name     name of image to be modified.
   * @param destName name of modified image.
   */
  public Mosaic(String name, String destName, int seeds) {
    this.name = name;
    this.destName = destName;
    this.seeds = seeds;
  }

  @Override
  public void run(IMosaicImageProcessingModel model) throws IllegalArgumentException {
    model.mosaicImage(this.name, this.destName, this.seeds);
  }
}
