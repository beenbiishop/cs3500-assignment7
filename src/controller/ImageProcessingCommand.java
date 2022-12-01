package controller;

import java.io.IOException;

import model.IImageProcessingModel;

/**
 * Class represents an interface for controller commands for the image processing program.
 */
public interface ImageProcessingCommand {
  /**
   * Image processing command that changes the state of the model.
   *
   * @param model represents image processing model.
   * @throws IOException if controller fails to process command.
   */
  void run(IImageProcessingModel model) throws IOException;
}
