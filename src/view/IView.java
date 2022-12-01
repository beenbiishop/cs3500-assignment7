package view;


import java.util.List;

import model.Image;

/**
 * The interface for our view class for the Image Processing Model.
 */
public interface IView {

  /**
   * Adds feature to view.
   * @param features features to add to view.
   */
  void addFeatures(Features features);

  /**
   * Makes view visible.
   */
  void makeVisible();

  /**
   * Signal the view to draw itself.
   */
  void refresh(Image image);

  /**
   * Takes component values from model for an image to set to the histogram Panel.
   * @param histogramList list of component value arrays.
   */
  void setHistogram(List<int[]> histogramList);

  /**
   * Quits program.
   */
  void quit();
}
