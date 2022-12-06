package model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

/**
 * Test class for Image Util.
 */
public class ImageUtilTest {

  @Test
  public void testReadPPM() {
    Image i1 = ImageUtil.readPPM("src/Kindred.ppm");
    Image i2 = new Image(i1.getImage(), 255);
    assertEquals(i1, i2);
  }

  // TODO: wrong file path again
  @Test
  public void testMakeFilePPM() throws IOException {
    Image i1 = ImageUtil.readImage("src/Kindred.ppm");
    ImageUtil.makeFile(i1, "src/Kindred.ppm");
    Image i2 = ImageUtil.readPPM("src/Kindred.ppm");
    assertEquals(i1, i2);
  }

  // TODO: wrong file path again
  @Test
  public void testMakeFilePPMInvalid() {
    Image i1 = ImageUtil.readImage("src/Kindred.ppm");
    try {
      ImageUtil.makeFile(i1, "invalid path");
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Invalid file extension", e.getMessage());
    }
  }

  @Test
  public void testReadImageInvalid() {
    try {
      ImageUtil.readImage("invalid path");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }

  }
}