package controller.commands;

import controller.ImageProcessingCommand;
import java.io.IOException;
import model.IImageProcessingModel;

public class Mosaic implements ImageProcessingCommand {

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
  public void run(IImageProcessingModel model) throws IOException {
    model.mosaicImage(this.name, this.destName, this.seeds);
  }

}
