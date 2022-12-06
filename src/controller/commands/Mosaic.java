package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

public class Mosaic implements ImageProcessingCommand {

  private int seeds;

  /**
   * Constructor for command to make a mosaic version of an image.
   *
   * @param seeds     the number of seeds
   */
  public Mosaic(int seeds) {
    this.seeds = seeds;

  }

  /**
   * Image processing command that changes the state of the model.
   *
   * @param model represents image processing model.
   * @throws IOException if controller fails to process command.
   */
  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.mosaicImage(this.seeds);
  }
}
