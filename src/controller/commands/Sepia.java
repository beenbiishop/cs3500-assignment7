package controller.commands;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

import java.io.IOException;

/**
 * Command for grayscale transform.
 */
public class Sepia implements ImageProcessingCommand {
  private final String name;
  private final String destName;

  /**
   * Constructor to transform the image to grayscale.
   *
   * @param name     Name of the image passed in.
   * @param destName Name of the new grayscale image.
   */
  public Sepia(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.sepiaImage(this.name, this.destName);
  }
}
