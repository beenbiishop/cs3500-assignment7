package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageUtil;

/**
 * The window for our view.
 */

public class JFrameView extends JFrame implements IView {

  private final JButton blurButton;
  private final JButton brightenButton;
  private final JButton darkenButton;
  private final JButton grayscaleButton;
  private final JButton horizontalButton;
  private final JButton verticalButton;
  private final JButton intensityButton;
  private final JButton lumaButton;
  private final JButton sepiaButton;
  private final JButton sharpenButton;
  private final JButton valueButton;
  private final JButton blueButton;
  private final JButton redButton;
  private final JButton greenButton;
  private final JButton loadButton;
  private final JButton saveButton;
  // TODO: Document new button field
  private final JButton mosaicButton;
  private final JLabel imageLabel;
  private String imageName;
  private Image displayImage;

  private HistogramPanel histogram;

  /**
   * The window for our view.
   */
  public JFrameView() {
    super();
    this.setTitle("Image Processing Program");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    add(mainPanel);
    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imageLabel = new JLabel(new ImageIcon());
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imageName = "";
    imagePanel.add(imageScrollPane);

    mainPanel.add(imagePanel);

    this.histogram = new HistogramPanel();
    JScrollPane histogramScrollPanel = new JScrollPane(this.histogram);
    histogramScrollPanel.setPreferredSize(new Dimension(275, 300));
    mainPanel.add(histogramScrollPanel);

    //file open
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    loadButton = new JButton("Load a file");
    loadButton.setActionCommand("Load file");
    buttonPanel.add(loadButton, BorderLayout.SOUTH);

    saveButton = new JButton("Save a file");
    saveButton.setActionCommand("Save file");
    buttonPanel.add(saveButton, BorderLayout.SOUTH);

    blueButton = new JButton("Blue Component");
    blueButton.setActionCommand("Blue Component");
    buttonPanel.add(blueButton);

    redButton = new JButton("Red Component");
    redButton.setActionCommand("Red Component");
    buttonPanel.add(redButton);

    greenButton = new JButton("Green Component");
    greenButton.setActionCommand("Green Component");
    buttonPanel.add(greenButton);

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    buttonPanel.add(blurButton);

    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    buttonPanel.add(brightenButton);

    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken");
    buttonPanel.add(darkenButton);

    grayscaleButton = new JButton("Grayscale");
    grayscaleButton.setActionCommand("Grayscale");
    buttonPanel.add(grayscaleButton);

    horizontalButton = new JButton("Horizontal");
    horizontalButton.setActionCommand("Horizontal");
    buttonPanel.add(horizontalButton);

    verticalButton = new JButton("Vertical");
    verticalButton.setActionCommand("Vertical");
    buttonPanel.add(verticalButton);

    intensityButton = new JButton("Intensity");
    intensityButton.setActionCommand("Intensity");
    buttonPanel.add(intensityButton);

    lumaButton = new JButton("Luma");
    lumaButton.setActionCommand("Luma");
    buttonPanel.add(lumaButton);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    buttonPanel.add(sepiaButton);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    buttonPanel.add(sharpenButton);

    valueButton = new JButton("Value");
    valueButton.setActionCommand("Value");
    buttonPanel.add(valueButton);

    // TODO: Document modification of constructor for new button
    mosaicButton = new JButton("Mosaic");
    mosaicButton.setActionCommand("Mosaic");
    buttonPanel.add(mosaicButton);

    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    pack();
  }

  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg, png, jpeg, bmp, & ppm",
          "jpg", "png", "ppm", "jpeg", "bmp");
      fileChooser.setFileFilter(filter);
      int retValue = fileChooser.showOpenDialog(this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        try {
          features.load(f.getPath(), f.getName());
          imageLabel.setIcon(new ImageIcon(displayImage));
          imageName = f.getName();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
    saveButton.addActionListener(evt -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg, png, & ppm", "jpg", "png",
          "ppm");
      fileChooser.setFileFilter(filter);
      int retValue = fileChooser.showSaveDialog(this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        try {
          features.save(f.getPath(), imageName);
          imageLabel.setIcon(new ImageIcon(displayImage));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
    blueButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.blue(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    redButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.red(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    greenButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.green(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    blurButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.blur(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    brightenButton.addActionListener(evt -> {
      int increment = getNumberInput("Input brighten increment value.");
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.brighten(increment, this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    darkenButton.addActionListener(evt -> {
      int decrement = getNumberInput("Input darken decrement value.");
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.darken(decrement, this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    grayscaleButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.grayscale(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    horizontalButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.horizontal(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    verticalButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.vertical(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    intensityButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.intensity(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    lumaButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.luma(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    sepiaButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.sepia(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    sharpenButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.sharpen(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    valueButton.addActionListener(evt -> {
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.value(this.imageName, destName);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
    // TODO: Document modification of this method to support mosaic
    mosaicButton.addActionListener(evt -> {
      int seeds = getNumberInput("Input mosaic seed amount.");
      String destName = JOptionPane.showInputDialog(this, "Provide destination name");
      features.mosaic(this.imageName, destName, seeds);
      imageLabel.setIcon(new ImageIcon(displayImage));
      imageName = destName;
    });
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh(model.Image image) {
    displayImage = ImageUtil.imageToImage(image);
    this.histogram.setPreferredSize(new Dimension(275, this.getMaxComponent()));
    this.repaint();
  }

  private int getMaxComponent() {
    int max = 0;
    for (int val : this.histogram.getHistogram().get(0)) {
      max = Math.max(val, max);
    }
    for (int val : this.histogram.getHistogram().get(1)) {
      max = Math.max(val, max);
    }
    for (int val : this.histogram.getHistogram().get(2)) {
      max = Math.max(val, max);
    }
    for (int val : this.histogram.getHistogram().get(3)) {
      max = Math.max(val, max);
    }
    return max;
  }

  private int getNumberInput(String message) throws NumberFormatException {
    String input = JOptionPane.showInputDialog(message);
    return Integer.parseInt(input);
  }

  public void setHistogram(List<int[]> histogram) {
    this.histogram.setHistogram(histogram);
  }

  @Override
  public void quit() {
    this.dispose();
  }

}
