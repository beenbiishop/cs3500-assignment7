package controller;

import model.IMosaicImageProcessingModel;

/**
 * Represents a command to mosaic an image.
 *
 * <p>
 * Effectively the same as {@link ImageProcessingCommand}, but provides support for the new
 * {@link IMosaicImageProcessingModel} model as the method parameter.
 * </p>
 */
// TODO: Document new interface
public interface MosaicImageProcessingCommand {

  /**
   * Command that runs the mosaic filter on this object's image using the method in the model.
   *
   * @param model represents the mosaic image processing model.
   * @throws IllegalArgumentException if controller fails to process command.
   */
  void run(IMosaicImageProcessingModel model) throws IllegalArgumentException;
}
