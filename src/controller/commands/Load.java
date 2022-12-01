package controller.commands;

import java.io.IOException;

import controller.ImageProcessingCommand;
import model.IImageProcessingModel;

/**
 * Class that represents the command that loads images from given path and stores to map.
 */
public class Load implements ImageProcessingCommand {

  private final String path;
  private final String name;

  /**
   * Constructor for command to load images into program.
   *
   * @param path image file path to load
   * @param name name of image to load
   */
  public Load(String path, String name) {
    this.path = path;
    this.name = name;
  }

  @Override
  public void run(IImageProcessingModel model) throws IOException {
    model.loadImage(this.path, this.name);
  }
}
