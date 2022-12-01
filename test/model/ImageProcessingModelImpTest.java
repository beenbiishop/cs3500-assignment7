package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Test class for model implementation.
 */
public class ImageProcessingModelImpTest {

  @Test
  public void testDefaultModel() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    assertTrue(m1.getImages().isEmpty());
  }

  @Test
  public void testLoadImage() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/res/Kindred.ppm", "kindred");
    Image kindred = ImageUtil.readPPM("src/res/Kindred.ppm");
    assertEquals(kindred, m1.getImages().get("kindred"));
  }

  @Test
  public void testFailLoad() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    String filename = "src/res/nonExistingFile.ppm";
    try {
      m1.loadImage(filename, "error");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "File " + filename + " not found!");
    }
  }

  @Test
  public void testSave() throws IOException {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    Image kindred = ImageUtil.readPPM("src/res/Kindred.ppm");
    m1.saveImage("src/res/Kindred.ppm", "kindred");
    assertEquals(kindred, m1.getImages().get("kindred"));
    assertTrue(Files.exists(Paths.get("src/res/Kindred.ppm")));
  }

  @Test
  public void testSaveInvalidFilePath() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.saveImage("src/res/testJunk", "kindred");
    } catch (IOException e) {
      assertEquals("Invalid file extension", e.getMessage());
    }
  }

  @Test
  public void testSaveInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.saveImage("src/res/Kindred.ppm", "error");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
  }

  @Test
  public void testTransformHorizontalInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.transformHorizontal("error", "kindred-horizontal");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testTransformHorizontal() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.transformHorizontal("kindred", "horizontal-kindred");
    Image horizontal = ImageUtil.readPPM("src/res/Kindred-Horizontal.ppm");
    assertEquals(horizontal, m1.getImages().get("horizontal-kindred"));
  }

  @Test
  public void testTransformVerticalInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.transformVertical("error", "kindred-vertical");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testTransformVertical() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.transformVertical("kindred", "vertical-kindred");
    Image vertical = ImageUtil.readPPM("src/res/Kindred-Vertical.ppm");
    assertEquals(vertical, m1.getImages().get("vertical-kindred"));
  }

  @Test
  public void testBrightInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.brightenImage(10, "error", "kindred-bright");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testBrighten() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.brightenImage(50, "kindred", "brighten-kindred");
    Image brighten = ImageUtil.readPPM("src/res/Kindred-Brighten-50.ppm");
    assertEquals(brighten, m1.getImages().get("brighten-kindred"));
  }

  @Test
  public void testRed() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.redComponent("kindred", "red-kindred");
    Image red = ImageUtil.readPPM("src/res/Kindred-Red.ppm");
    assertEquals(red, m1.getImages().get("red-kindred"));
  }


  @Test
  public void testRedInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.redComponent("error", "kindred-red");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testGreen() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.greenComponent("kindred", "green-kindred");
    Image green = ImageUtil.readPPM("src/res/Kindred-Green.ppm");
    assertEquals(green, m1.getImages().get("green-kindred"));
  }

  @Test
  public void testGreenInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.greenComponent("error", "kindred-green");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testBlue() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.greenComponent("kindred", "blue-kindred");
    Image blue = ImageUtil.readPPM("src/res/Kindred-Blue.ppm");
    assertEquals(blue, m1.getImages().get("blue-kindred"));
  }

  @Test
  public void testBlueInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.blueComponent("error", "kindred-blue");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testLuma() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.lumaComponent("kindred", "luma-kindred");
    Image luma = ImageUtil.readPPM("src/res/Kindred-Luma.ppm");
    assertEquals(luma, m1.getImages().get("luma-kindred"));
  }

  @Test
  public void testLumaInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.lumaComponent("error", "kindred-luma");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testIntensity() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.intensityComponent("kindred", "intensity-kindred");
    Image intensity = ImageUtil.readPPM("src/res/Kindred-Intensity.ppm");
    assertEquals(intensity, m1.getImages().get("intensity-kindred"));
  }

  @Test
  public void testIntensityInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.intensityComponent("error", "kindred-intensity");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testValue() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.valueComponent("kindred", "value-kindred");
    Image value = ImageUtil.readPPM("src/res/Kindred-Value.ppm");
    assertEquals(value, m1.getImages().get("value-kindred"));
  }

  @Test
  public void testValueInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.valueComponent("error", "kindred-value");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testBlur() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.blurImage("kindred", "blur-kindred");
    Image blur = ImageUtil.readPPM("src/res/Kindred-Blur.ppm");
    assertEquals(blur, m1.getImages().get("blur-kindred"));
  }

  @Test
  public void testBlurInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.blurImage("error", "kindred-blur");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testSharpen() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.sharpenImage("kindred", "sharpen-kindred");
    Image sharpen = ImageUtil.readPPM("src/res/Kindred-Sharpen.ppm");
    assertEquals(sharpen, m1.getImages().get("sharpen-kindred"));
  }

  @Test
  public void testSharpenInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.sharpenImage("error", "kindred-sharpen");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testGrayscale() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.grayscaleImage("kindred", "grayscale-kindred");
    Image sharpen = ImageUtil.readPPM("src/res/Kindred-Grayscale.ppm");
    assertEquals(sharpen, m1.getImages().get("grayscale-kindred"));
  }

  @Test
  public void grayscaleImageInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.grayscaleImage("error", "kindred-sharpen");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }

  @Test
  public void testSepia() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    m1.sepiaImage("kindred", "sepia-kindred");
    Image sepia = ImageUtil.readPPM("src/res/Kindred-Sepia.ppm");
    assertEquals(sepia, m1.getImages().get("sepia-kindred"));
  }

  @Test
  public void sepiaImageInvalidName() {
    IImageProcessingModel m1 = new ImageProcessingModelImp();
    m1.loadImage("src/res/Kindred.ppm", "kindred");
    try {
      m1.sepiaImage("error", "kindred-sharpen");
    } catch (IllegalArgumentException e) {
      assertEquals("No image error found.", e.getMessage());
    }
  }
}
