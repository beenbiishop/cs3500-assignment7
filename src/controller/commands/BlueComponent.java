package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * This class represents the command to create greyscale image from blue component.
 */
public class BlueComponent implements ImageProcessingCommand {

  private final String name;

  private final String destName;

  /**
   * Constructor for command to create blue component greyscale image.
   *
   * @param name     the name of image to perform modification on.
   * @param destName the name of modified image.
   */
  public BlueComponent(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }


  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.blueComponent(this.name, this.destName);
  }
}
