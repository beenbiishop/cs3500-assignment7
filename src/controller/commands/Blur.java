package controller.commands;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

import java.io.IOException;

/**
 * Command for blur filter.
 */
public class Blur implements ImageProcessingCommand {
  private final String name;
  private final String destName;

  /**
   * Constructor to blur the image.
   *
   * @param name     Name of the image passed in.
   * @param destName Name of the new image once blurred.
   */
  public Blur(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.blurImage(this.name, this.destName);
  }
}
