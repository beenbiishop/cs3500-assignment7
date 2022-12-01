package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * This class represents the command to save an image.
 */
public class Save implements ImageProcessingCommand {

  private final String path;
  private final String name;


  /**
   * Constructor call for command to save an image.
   *
   * @param path the path to get to the image.
   * @param name the name of the image.
   */
  public Save(String path, String name) {
    this.path = path;
    this.name = name;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.saveImage(this.path, this.name);
  }
}
