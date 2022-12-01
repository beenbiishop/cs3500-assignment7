package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * This class represents command to create greyscale image from value component.
 */
public class ValueComponent implements ImageProcessingCommand {

  private final String name;
  private final String destName;

  /**
   * Constructor for command to create value greyscale image.
   *
   * @param name     the name of image to perform modification on.
   * @param destName the name of modified image.
   */
  public ValueComponent(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.valueComponent(this.name, this.destName);
  }
}
