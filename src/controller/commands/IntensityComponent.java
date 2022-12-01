package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * This class represents the command to create greyscale image from intensity component.
 */
public class IntensityComponent implements ImageProcessingCommand {

  private final String name;

  private final String destName;

  /**
   * Constructor for command to create intensity component greyscale image.
   *
   * @param name     the name of image to perform modification on.
   * @param destName the name of modified image.
   */
  public IntensityComponent(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.intensityComponent(this.name, this.destName);
  }
}
