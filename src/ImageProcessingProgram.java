import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImp;
import model.IImageProcessingModel;
import model.ImageProcessingModelImp;
import view.IView;
import view.JFrameView;

/**
 * Processes the image.
 */
public class ImageProcessingProgram {
  /**
   * Processes the Image.
   * @param args The photo to be processed.
   * @throws IOException cannot find the photo.
   */
  public static void main(String []args) throws IOException {
    IImageProcessingModel model = new ImageProcessingModelImp();
    IView view = new JFrameView();
    Reader in = new InputStreamReader(System.in);
    ImageProcessingController controller = new ImageProcessingControllerImp(model,in,view);
    controller.run();
  }
}
