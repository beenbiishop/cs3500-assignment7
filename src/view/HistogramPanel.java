package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Class for Histogram Panel to display histogram.
 */
public class HistogramPanel extends JPanel {

  private List<int[]> histogram;

  /**
   * Histogram Panel initialization, sets histogram list of integer arrays to default 0 values.
   */
  public HistogramPanel() {
    super();
    ArrayList<int[]> dummy = new ArrayList<>();
    int[] dummyRed = new int[256];
    int[] dummyGreen = new int[256];
    int[] dummyBlue = new int[256];
    int[] dummyIntensity = new int[256];
    dummy.add(dummyRed);
    dummy.add(dummyGreen);
    dummy.add(dummyBlue);
    dummy.add(dummyIntensity);
    this.histogram = dummy;
  }

  /**
   * Gets histogram list of arrays.
   *
   * @return histogram list of arrays.
   */
  public List<int[]> getHistogram() {
    return this.histogram;
  }

  /**
   * Set list of histogram arrays to given list.
   *
   * @param histogram list of integer arrays.
   */
  public void setHistogram(List<int[]> histogram) {
    this.histogram = histogram;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    AffineTransform originalTransform = g2d.getTransform();

    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    for (int i = 0; i < this.histogram.get(0).length; i++) {
      g2d.setColor(new Color(255, 0, 0, 150));
      g2d.drawLine(i, 0, i, this.histogram.get(0)[i]);
      g2d.setColor(new Color(0, 255, 0, 150));
      g2d.drawLine(i, 0, i, this.histogram.get(1)[i]);
      g2d.setColor(new Color(0, 0, 255, 150));
      g2d.drawLine(i, 0, i, this.histogram.get(2)[i]);
      g2d.setColor(new Color(60, 60, 60, 150));
      g2d.drawLine(i, 0, i, this.histogram.get(3)[i]);
    }
    g2d.setTransform(originalTransform);
  }
}
