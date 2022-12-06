package model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an implementation of the Image processing model, utilizing a hashMap to map
 * given inputted image names to image objects.
 */
public class ImageProcessingModelImp implements IImageProcessingModel {

  final private Map<String, Image> images;

  /**
   * Represents the implementation model object, initializing the HashMap to an empty HashMap.
   */
  public ImageProcessingModelImp() {
    this.images = new HashMap<>();
  }

  @Override
  public void loadImage(String path, String name) throws IllegalArgumentException {
    this.images.put(name, ImageUtil.readImage(path));
    System.out.println("Image loaded successfully.");

  }

  @Override
  public void saveImage(String path, String name) throws IllegalArgumentException, IOException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    ImageUtil.makeFile(this.images.get(name), path);
    System.out.println("Image saved successfully.");
  }

  @Override
  public void transformHorizontal(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] transformedImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth() / 2; j++) {
        transformedImage[i][j][0] = image.getRed(i, (image.getWidth() - 1) - j);
        transformedImage[i][j][1] = image.getGreen(i, (image.getWidth() - 1) - j);
        transformedImage[i][j][2] = image.getBlue(i, (image.getWidth() - 1) - j);
        transformedImage[i][(image.getWidth() - 1) - j][0] = image.getRed(i, j);
        transformedImage[i][(image.getWidth() - 1) - j][1] = image.getGreen(i, j);
        transformedImage[i][(image.getWidth() - 1) - j][2] = image.getBlue(i, j);
      }
    }
    this.images.put(destName, new Image(transformedImage, image.getMaxValue()));
    System.out.println("Horizontal transformation complete");
  }

  @Override
  public void transformVertical(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] transformedImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight() / 2; i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        transformedImage[i][j][0] = image.getRed((image.getHeight() - 1) - i, j);
        transformedImage[i][j][1] = image.getGreen((image.getHeight() - 1) - i, j);
        transformedImage[i][j][2] = image.getBlue((image.getHeight() - 1) - i, j);
        transformedImage[(image.getHeight() - 1) - i][j][0] = image.getRed(i, j);
        transformedImage[(image.getHeight() - 1) - i][j][1] = image.getGreen(i, j);
        transformedImage[(image.getHeight() - 1) - i][j][2] = image.getBlue(i, j);
      }
    }
    this.images.put(destName, new Image(transformedImage, image.getMaxValue()));
    System.out.println("Vertical transformation complete");

  }

  @Override
  public void brightenImage(int increment, String name, String destName)
      throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] brightenedImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        brightenedImage[i][j][0] = clampInt(image, image.getRed(i, j) + increment);
        brightenedImage[i][j][1] = clampInt(image, image.getGreen(i, j) + increment);
        brightenedImage[i][j][2] = clampInt(image, image.getBlue(i, j) + increment);
      }
    }
    this.images.put(destName, new Image(brightenedImage, image.getMaxValue()));
    System.out.println("Brightened image by " + increment);

  }

  @Override
  public void darkenImage(int increment, String name, String destName)
      throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] darkenedImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        darkenedImage[i][j][0] = clampInt(image, image.getRed(i, j) - increment);
        darkenedImage[i][j][1] = clampInt(image, image.getGreen(i, j) - increment);
        darkenedImage[i][j][2] = clampInt(image, image.getBlue(i, j) - increment);
      }
    }
    this.images.put(destName, new Image(darkenedImage, image.getMaxValue()));
    System.out.println("Brightened image by " + increment);

  }

  private int clampInt(Image image, int value) {
    if (value > image.getMaxValue()) {
      return image.getMaxValue();
    }
    return Math.max(value, 0);
  }

  @Override
  public void redComponent(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] greyscaleImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        greyscaleImage[i][j][0] = image.getRed(i, j);
        greyscaleImage[i][j][1] = image.getRed(i, j);
        greyscaleImage[i][j][2] = image.getRed(i, j);
      }
    }
    this.images.put(destName, new Image(greyscaleImage, image.getMaxValue()));
    System.out.println("Red component greyscale created");

  }

  @Override
  public void greenComponent(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] greyscaleImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        greyscaleImage[i][j][0] = image.getGreen(i, j);
        greyscaleImage[i][j][1] = image.getGreen(i, j);
        greyscaleImage[i][j][2] = image.getGreen(i, j);
      }
    }
    this.images.put(destName, new Image(greyscaleImage, image.getMaxValue()));
    System.out.println("Green component greyscale created");

  }

  @Override
  public void blueComponent(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] greyscaleImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        greyscaleImage[i][j][0] = image.getBlue(i, j);
        greyscaleImage[i][j][1] = image.getBlue(i, j);
        greyscaleImage[i][j][2] = image.getBlue(i, j);
      }
    }
    this.images.put(destName, new Image(greyscaleImage, image.getMaxValue()));
    System.out.println("Blue component greyscale created");

  }

  @Override
  public void lumaComponent(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] greyscaleImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int lumaVal = clampInt(image,
            (int) (0.2126 * image.getRed(i, j) + 0.7152 * image.getGreen(i, j)
                + 0.0722 * image.getBlue(i, j)));
        greyscaleImage[i][j][0] = lumaVal;
        greyscaleImage[i][j][1] = lumaVal;
        greyscaleImage[i][j][2] = lumaVal;
      }
    }
    this.images.put(destName, new Image(greyscaleImage, image.getMaxValue()));
    System.out.println("Luma component greyscale created");

  }

  @Override
  public void intensityComponent(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] greyscaleImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int intensityVal = clampInt(image,
            (image.getRed(i, j) + image.getGreen(i, j) + image.getBlue(i, j)) / 3);
        greyscaleImage[i][j][0] = intensityVal;
        greyscaleImage[i][j][1] = intensityVal;
        greyscaleImage[i][j][2] = intensityVal;
      }
    }
    this.images.put(destName, new Image(greyscaleImage, image.getMaxValue()));
    System.out.println("Intensity component greyscale created");

  }

  @Override
  public void valueComponent(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    int[][][] greyscaleImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int[] tempArray = {image.getRed(i, j), image.getGreen(i, j), image.getBlue(i, j)};
        int maxVal = tempArray[0];
        for (int v : tempArray) {
          if (v > maxVal) {
            maxVal = v;
          }
        }
        greyscaleImage[i][j][0] = maxVal;
        greyscaleImage[i][j][1] = maxVal;
        greyscaleImage[i][j][2] = maxVal;
      }
    }
    this.images.put(destName, new Image(greyscaleImage, image.getMaxValue()));
    System.out.println("value component greyscale created");

  }

  @Override
  public void blurImage(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    double[][] blurFilter = {{1.0 / 16, 1.0 / 8, 1.0 / 16}, {1.0 / 8, 1.0 / 4, 1.0 / 8},
        {1.0 / 16, 1.0 / 8, 1.0 / 16}};
    this.images.put(destName, applyFilter(image, blurFilter));
    System.out.println("Blurred image created");
  }

  @Override
  public void sharpenImage(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    double[][] sharpenFilter = {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};
    this.images.put(destName, applyFilter(image, sharpenFilter));
    System.out.println("Sharpened image created");
  }

  @Override
  public void grayscaleImage(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    double[][] grayscaleTransform = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
    this.images.put(destName, applyTransform(image, grayscaleTransform));
    System.out.println("Grayscale image created");
  }

  @Override
  public void sepiaImage(String name, String destName) throws IllegalArgumentException {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    double[][] sepiaTransform = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};
    this.images.put(destName, applyTransform(image, sepiaTransform));
    System.out.println("Sepia image created");
  }

  /**
   * Produces a filtered image corresponding to the provided filter.
   *
   * @param filter the filter to apply to the image.
   * @return the image with the filter applied.
   * @throws IllegalArgumentException if the filter is invalid.
   */
  private Image applyFilter(Image image, double[][] filter) throws IllegalArgumentException {
    int filterSize = filter.length;
    if (filterSize % 2 != 1 || filterSize != filter[0].length) {
      throw new IllegalArgumentException("Filter must be square with odd size.");
    }
    int filterCenter = (filter.length - 1) / 2;
    int filterStart = -filterCenter;

    int[][][] filteredImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        double filteredRed = 0;
        double filteredGreen = 0;
        double filteredBlue = 0;
        for (int iFilter = filterStart; iFilter < filterStart + filterSize; iFilter++) {
          if (i + iFilter < 0 || i + iFilter >= image.getHeight()) {
            continue;
          }
          for (int jFilter = filterStart; jFilter < filterStart + filterSize; jFilter++) {
            if (j + jFilter < 0 || j + jFilter >= image.getWidth()) {
              continue;
            }
            double filterVal = filter[filterCenter + iFilter][filterCenter + jFilter];
            filteredRed += image.getRed(i + iFilter, j + jFilter) * filterVal;
            filteredGreen += image.getGreen(i + iFilter, j + jFilter) * filterVal;
            filteredBlue += image.getBlue(i + iFilter, j + jFilter) * filterVal;
          }
        }
        filteredImage[i][j][0] = clampInt(image, (int) filteredRed);
        filteredImage[i][j][1] = clampInt(image, (int) filteredGreen);
        filteredImage[i][j][2] = clampInt(image, (int) filteredBlue);
      }
    }
    return new Image(filteredImage, image.getMaxValue());
  }

  /**
   * Produces a transformed image corresponding to the provided transform.
   *
   * @param transform the transform to apply to the image.
   * @return the image with the transform applied.
   * @throws IllegalArgumentException if the transform is invalid.
   */
  private Image applyTransform(Image image, double[][] transform) throws IllegalArgumentException {
    int transformSize = transform.length;
    if (transformSize != 3 || transform[0].length != 3) {
      throw new IllegalArgumentException("Transform must be a 3x3 matrix");
    }

    int[][][] transformedImage = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        for (int channel = 0; channel < 3; channel++) {
          transformedImage[i][j][channel] = clampInt(image,
              (int) (image.getRed(i, j) * transform[channel][0]
                  + image.getGreen(i, j) * transform[channel][1]
                  + image.getBlue(i, j) * transform[channel][2]));
        }
      }
    }
    return new Image(transformedImage, image.getMaxValue());
  }

  public Map<String, Image> getImages() {
    return this.images;
  }

  @Override
  public List<int[]> histogramList(String name) {
    if (this.images.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = this.images.get(name);
    ArrayList<int[]> histogram = new ArrayList<>();
    int[] redPixels = new int[256];
    int[] greenPixels = new int[256];
    int[] bluePixels = new int[256];
    int[] intensityPixels = new int[256];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int intensityVal = clampInt(image,
            (image.getRed(i, j) + image.getGreen(i, j) + image.getBlue(i, j)) / 3);
        redPixels[image.getRed(i, j)] += 1;
        greenPixels[image.getGreen(i, j)] += 1;
        bluePixels[image.getBlue(i, j)] += 1;
        intensityPixels[intensityVal] += 1;
      }
    }
    histogram.add(redPixels);
    histogram.add(greenPixels);
    histogram.add(bluePixels);
    histogram.add(intensityPixels);
    return histogram;
  }

  @Override
  public void mosaicImage(int seeds) throws IllegalArgumentException {
  }
}
