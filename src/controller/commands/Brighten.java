package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * This class represents the command to brighten an image by given increment.
 */
public class Brighten implements ImageProcessingCommand {

  private final int increment;

  private final String name;

  private final String destName;

  /**
   * Constructor for command to create a brightened image by given increment.
   *
   * @param increment integer to increment RGB components by.
   * @param name      name of image to be modified brightness.
   * @param destName  name of modified image.
   */
  public Brighten(int increment, String name, String destName) {
    this.increment = increment;
    this.name = name;
    this.destName = destName;
  }


  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.brightenImage(this.increment, this.name, this.destName);
  }
}
