package controller.commands;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

import java.io.IOException;

/**
 * Command for sharpen filter.
 */
public class Sharpen implements ImageProcessingCommand {
  private final String name;
  private final String destName;

  /**
   * Constructor to sharpen the image.
   *
   * @param name     Name of the image passed in.
   * @param destName Name of the new image once sharpened.
   */
  public Sharpen(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.sharpenImage(this.name, this.destName);
  }
}
