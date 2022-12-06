package controller;

import controller.commands.BlueComponent;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Darken;
import controller.commands.Grayscale;
import controller.commands.GreenComponent;
import controller.commands.Horizontal;
import controller.commands.IntensityComponent;
import controller.commands.Load;
import controller.commands.LumaComponent;
import controller.commands.Mosaic;
import controller.commands.RedComponent;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueComponent;
import controller.commands.Vertical;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import model.IImageProcessingModel;
import model.IMosaicImageProcessingModel;
import view.IView;
import view.MosaicFeatures;

/**
 * Controller class that runs the Image processing program and takes in user input.
 */
// TODO: Confirm change of features interface
public class ImageProcessingControllerImp implements ImageProcessingController, MosaicFeatures {

  private final IImageProcessingModel model;

  private final Readable readable;

  private final IView view;

  /**
   * Takes in the model readable and view  to pass them into the main method.
   *
   * @param model    Image processing Model.
   * @param readable The readable fields to create the image.
   * @param view     the window to put image in.
   */
  public ImageProcessingControllerImp(IImageProcessingModel model, Readable readable, IView view) {
    if (model == null || readable == null || view == null) {
      throw new IllegalArgumentException("Null value input.");
    }
    this.model = model;
    this.readable = readable;
    this.view = view;

  }

  // TODO: is this the right approach?

  /**
   * Constructs a new controller for the GUI that supports mosaic.
   *
   * @param model    the mosaic model to use
   * @param readable the readable to read from
   * @param view     the view to display on
   */
  public ImageProcessingControllerImp(IMosaicImageProcessingModel model, Readable readable,
      IView view) {
    this.model = model;
    this.readable = readable;
    this.view = view;
  }

  /**
   * Main method.
   *
   * @throws IOException if program cannot run commands
   */
  public void run() throws IOException {
    view.addFeatures(this);
    this.view.makeVisible();
    Scanner scan = new Scanner(this.readable);

    Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> new Load(s.next(), s.next()));
    knownCommands.put("save", (Scanner s) -> new Save(s.next(), s.next()));
    knownCommands.put("horizontal-flip", (Scanner s) -> new Horizontal(s.next(), s.next()));
    knownCommands.put("vertical-flip", (Scanner s) -> new Vertical(s.next(), s.next()));
    knownCommands.put("brighten", (Scanner s) -> new Brighten(s.nextInt(), s.next(), s.next()));
    knownCommands.put("darken", (Scanner s) -> new Darken(s.nextInt(), s.next(), s.next()));
    knownCommands.put("red-component", (Scanner s) -> new RedComponent(s.next(), s.next()));
    knownCommands.put("green-component", (Scanner s) -> new GreenComponent(s.next(), s.next()));
    knownCommands.put("blue-component", (Scanner s) -> new BlueComponent(s.next(), s.next()));
    knownCommands.put("value-component", (Scanner s) -> new ValueComponent(s.next(), s.next()));
    knownCommands.put("luma-component", (Scanner s) -> new LumaComponent(s.next(), s.next()));
    knownCommands.put("intensity-component",
        (Scanner s) -> new IntensityComponent(s.next(), s.next()));
    knownCommands.put("blur", (Scanner s) -> new Blur(s.next(), s.next()));
    knownCommands.put("sharpen", (Scanner s) -> new Sharpen(s.next(), s.next()));
    knownCommands.put("grayscale", (Scanner s) -> new Grayscale(s.next(), s.next()));
    knownCommands.put("sepia", (Scanner s) -> new Sepia(s.next(), s.next()));
    // TODO: document this change
    knownCommands.put("mosaic", (Scanner s) -> new Mosaic(s.next(), s.next(), s.nextInt()));
    while (scan.hasNext()) {
      ImageProcessingCommand c;
      String in = scan.next();

      Function<Scanner, ImageProcessingCommand> cmd = knownCommands.getOrDefault(in, null);
      if (in.equalsIgnoreCase("q")) {
        this.view.quit();
        return;
      }
      if (in.equalsIgnoreCase("-file")) {
        String filename = scan.next();

        try {
          scan = new Scanner(new FileInputStream(filename));
          StringBuilder builder = new StringBuilder();
          builder.append(scan.nextLine()).append(System.lineSeparator());

          new ImageProcessingControllerImp(this.model, new StringReader(builder.toString()),
              this.view).run();
          this.quit();
          return;
        } catch (IOException e) {
          throw new IOException(e.getMessage());
        }
      }
      if (cmd == null) {
        throw new IllegalArgumentException("Unknown command.");
      } else {
        c = cmd.apply(scan);
        c.run(this.model);
      }
    }
  }

  @Override
  public void save(String filepath, String fileName) throws IOException {
    this.model.saveImage(filepath, fileName);
    this.view.refresh(this.model.getImages().get(fileName));
  }

  @Override
  public void blue(String imageName, String destName) {
    this.model.blueComponent(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));

  }

  @Override
  public void red(String imageName, String destName) {
    this.model.redComponent(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void green(String imageName, String destName) {
    this.model.greenComponent(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void blur(String imageName, String destName) {
    this.model.blurImage(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void brighten(int increment, String imageName, String destName) {
    this.model.brightenImage(increment, imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void darken(int increment, String imageName, String destName) {
    this.model.darkenImage(increment, imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void grayscale(String imageName, String destName) {
    this.model.grayscaleImage(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void horizontal(String imageName, String destName) {
    this.model.transformHorizontal(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void vertical(String imageName, String destName) {
    this.model.transformVertical(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void intensity(String imageName, String destName) {
    this.model.intensityComponent(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void luma(String imageName, String destName) {
    this.model.lumaComponent(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void sepia(String imageName, String destName) {
    this.model.sepiaImage(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void sharpen(String imageName, String destName) {
    this.model.sharpenImage(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void value(String imageName, String destName) {
    this.model.valueComponent(imageName, destName);
    this.view.setHistogram(this.model.histogramList(destName));
    this.view.refresh(this.model.getImages().get(destName));
  }

  @Override
  public void load(String filepath, String fileName) {
    this.model.loadImage(filepath, fileName);
    this.view.setHistogram(this.model.histogramList(fileName));
    this.view.refresh(this.model.getImages().get(fileName));
  }

  @Override
  public void quit() {
    this.view.quit();
  }

  // TODO: document this change and confirm that it is correct
  @Override
  public void mosaic(String imageName, String destName, int seeds) {
    if (this.model instanceof IMosaicImageProcessingModel) {
      IMosaicImageProcessingModel mosaicModel = (IMosaicImageProcessingModel) this.model;
      mosaicModel.mosaicImage(imageName, destName, seeds);
      this.view.setHistogram(this.model.histogramList(destName));
      this.view.refresh(this.model.getImages().get(destName));
    } else {
      throw new IllegalStateException("Mosaic not supported by this controller's model.");
    }
  }

}
