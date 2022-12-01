package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * This class represents the command to create greyscale image from red component.
 */
public class RedComponent implements ImageProcessingCommand {

  private final String name;

  private final String destName;

  /**
   * Constructor for command to create blue component greyscale image.
   *
   * @param name     the name of the image to perform modification.
   * @param destName the name of modified image.
   */
  public RedComponent(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.redComponent(this.name, this.destName);
  }
}
