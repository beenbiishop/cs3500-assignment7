package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MosaicImageProcessingModelImpl implements IMosaicImageProcessingModel {

  private final IImageProcessingModel model = new ImageProcessingModelImp();


  @Override
  public void loadImage(String path, String name) throws IllegalArgumentException {
    model.loadImage(path, name);
  }

  @Override
  public void saveImage(String path, String name) throws IOException, IllegalArgumentException {
    model.saveImage(path, name);

  }

  @Override
  public void transformHorizontal(String name, String destName) throws IllegalArgumentException {
    model.transformHorizontal(name, destName);

  }

  @Override
  public void transformVertical(String name, String destName) throws IllegalArgumentException {
    model.transformVertical(name, destName);

  }

  @Override
  public void brightenImage(int increment, String name, String destName)
      throws IllegalArgumentException {

    model.brightenImage(increment, name, destName);


  }

  @Override
  public void darkenImage(int increment, String name, String destName)
      throws IllegalArgumentException {
    model.brightenImage(increment, name, destName);

  }

  @Override
  public void redComponent(String name, String destName) throws IllegalArgumentException {
    model.redComponent(name, destName);
  }

  @Override
  public void greenComponent(String name, String destName) throws IllegalArgumentException {
    model.greenComponent(name, destName);
  }

  @Override
  public void blueComponent(String name, String destName) throws IllegalArgumentException {
    model.blueComponent(name, destName);
  }

  @Override
  public void lumaComponent(String name, String destName) throws IllegalArgumentException {
    model.lumaComponent(name, destName);
  }

  @Override
  public void intensityComponent(String name, String destName) throws IllegalArgumentException {
    model.intensityComponent(name, destName);

  }

  @Override
  public void valueComponent(String name, String destName) throws IllegalArgumentException {
    model.valueComponent(name, destName);

  }

  @Override
  public void blurImage(String name, String destName) throws IllegalArgumentException {
    model.blurImage(name, destName);
  }

  @Override
  public void sharpenImage(String name, String destName) throws IllegalArgumentException {
    model.sharpenImage(name, destName);
  }

  @Override
  public void grayscaleImage(String name, String destName) throws IllegalArgumentException {
    model.grayscaleImage(name, destName);
  }

  @Override
  public void sepiaImage(String name, String destName) throws IllegalArgumentException {
    model.sepiaImage(name, destName);
  }

  @Override
  public Map<String, Image> getImages() {
    return model.getImages();
  }

  @Override
  public List<int[]> histogramList(String name) {
    return model.histogramList(name);
  }

  @Override
  public void mosaicImage(String name, String destName, int numSeeds)
      throws IllegalArgumentException {
    Map<String, Image> loadedImages = this.model.getImages();
    if (loadedImages.get(name) == null) {
      throw new IllegalArgumentException("No image " + name + " found.");
    }
    Image image = loadedImages.get(name);
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] mosaicImage = new int[height][width][3];

    // generate seeds
    Random random = new Random();
    List<int[]> seeds = new ArrayList<>();
    for (int i = 0; i < numSeeds; i++) {
      int[] seed = new int[2];
      seed[0] = random.nextInt(height);
      seed[1] = random.nextInt(width);
      seeds.add(seed);
    }

    // set each pixel to the color of the closest seed
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int[] closestSeed = seeds.get(0);
        int closestSeedDist = Integer.MAX_VALUE;
        for (int[] seed : seeds) {
          int yDist = Math.abs(seed[0] - i);
          int xDist = Math.abs(seed[1] - j);
          int seedDist = yDist + xDist;
          if (seedDist < closestSeedDist) {
            closestSeed = seed;
            closestSeedDist = seedDist;
          }
        }
        mosaicImage[i][j][0] = image.getRed(closestSeed[0], closestSeed[1]);
        mosaicImage[i][j][1] = image.getGreen(closestSeed[0], closestSeed[1]);
        mosaicImage[i][j][2] = image.getBlue(closestSeed[0], closestSeed[1]);
      }
    }

    // store the new image
    // TODO: document using exposed map from getter reasoning – from Vido
    loadedImages.put(destName, new Image(mosaicImage, image.getMaxValue()));
    System.out.println("Mosaic created");

  }

}
