package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * This class represents the command to create greyscale image from luma component.
 */
public class LumaComponent implements ImageProcessingCommand {

  private final String name;

  private final String destName;


  /**
   * Constructor for command to create luma component greyscale image.
   *
   * @param name     the name of image to perform modification on.
   * @param destName the name of modified image.
   */
  public LumaComponent(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.lumaComponent(this.name, this.destName);
  }
}
