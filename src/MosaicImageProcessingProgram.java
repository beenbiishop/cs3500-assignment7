import controller.ImageProcessingController;
import controller.ImageProcessingControllerImp;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import model.IImageProcessingModel;
import model.MosaicImageProcessingModelImpl;
import view.IView;
import view.JFrameView;

/**
 * Runs the image processing program that supports mosaics.
 */
public class MosaicImageProcessingProgram {

  /**
   * Starts the image processing program that supports mosaics.
   *
   * @param args the command line arguments
   * @throws IOException if the program cannot run commands
   */
  public static void main(String[] args) throws IOException {
    IImageProcessingModel model = new MosaicImageProcessingModelImpl();
    IView view = new JFrameView();
    Reader in = new InputStreamReader(System.in);
    ImageProcessingController controller = new ImageProcessingControllerImp(model, in, view);
    controller.run();
  }
}
