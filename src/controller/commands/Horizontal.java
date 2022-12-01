package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * Command for horizontal flip transformation.
 */
public class Horizontal implements ImageProcessingCommand {
  private final String name;

  private final String destName;

  /**
   * Constructor to flip the image horizontally.
   *
   * @param name     Name of the image passed in.
   * @param destName Name of the new image once flipped.
   */
  public Horizontal(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }


  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.transformHorizontal(this.name, this.destName);
  }
}
