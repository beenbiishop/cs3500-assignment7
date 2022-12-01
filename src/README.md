Image Processing Program

This project utilizes the MVC design to create a PPM image processing program.
Below will be an in-depth explanation of the program.

Summary of Changes:

* We changed the way our ImageProcessingController by taking out the run method 
  and having that throw the IOExeption. We did this because it made it easier to test. 
We also updated Implementation to handle all of the commands properly and 
run multiple commands in order.

* We stopped trying to hand a single image and a group of images to ImageProcessingModelImp. We now just handit a Hashmap of images.


Model:

The model consists of three classes and one interface.

* The IImageProcessingModel interface is an interface that represents the operations offered by the
  Image Processing model. An object of the model represents an Image processing program. The model
  offers various different states of the program, which are essentially operations for image
  processing.
    * loadImage: given a file path and an image name, loads an image file to create an image and
      maps
      the image name to the created image. Throws exception if unable to create image from file.
    * saveImage: given a path and an image name, gets image mapped to image name to save to file
      path.
      Throws exception if no image name exists in program.
    * Horizontal and Vertical transitions: given image name and destination image name, gets image
      mapped to designated image name and performs corresponding transition, storing (or mapping)
      new
      modified image
      to the destination image name. Throws exception if no image name exists in program.
    * greyscale components (red, green, blue, luma, intensity, value): given image name and
      destination image name, gets image mapped to given name and creates a greyscale image based on
      corresponding component, storing (or mapping) new modified image to destination image name.
      Throws exception if no image name exists in program.
* The ImageProcessingModelImp represents an implementation of the image processing model.
  The implementation contains two private fields, an Image to represent the active processing image
  and a Hashmap to store Images to image names (string values). The constructor of this
  implementation
  initializes the map to an empty hashMap.

* The Image class represents an image object. For readability purposes, each array of integers in
  the 3d array is representative of the RGB components of a pixel in the image.
    * Made up of four private final fields.
        * 3d integer array representation of the image
        * the height of the image
        * the width of the image
        * the maximum value of the RGB components in each pixel of the image.
    * The Image class contains the methods getHeight, getWidth, getMaxValue to access the values of
      its private fields. Additionally, the getRed, getGreen, and getBlue methods return the
      corresponding
      RGB components based on the pixel location in the image.
* The ImageUtil class is simply a utility class to read a PPM image from file and create image
  object, as well as creating a file from an image object.
    * The readFile method reads a file at a file path and creates an image object accordingly. The
      method throws an exception if the given file path is invalid or if the file type is not ppm.
    * The makeFile method takes an image and creates a ppm file based on its properties. Throws
      exception if method is unable to write file.

Controller:

The controller consists of 12 classes and one interface.

* The ImageProcessingCommand represents an interface for controller commands for the image
  processing program. It contains a method that runs commands on a given Image processing model.
* The controller class runs the Image processing program and awaits user input. When the program is
  executed, the controller waits for known ImageProcessingCommands and processes such commands once
  they are ran given corresponding user input. If the user inputs "q", the program quits. If unknown
  command is inputted, then an IllegalArgumentException is thrown.
* Commands:
    * The Load command class runs the load method from the model on user input.
    * The Save command class runs the save method from the model on user input.
    * The Horizontal command class runs the horizontal method from the model on user input
    * The Vertical command class runs the vertical method from the model on user input.
    * The Brighten command class runs the brighten method from the model on user input.
    * The valueComponent command class runs the valueComponent method from the model on user input.
    * The lumaComponent command class runs the lumaComponent method from the model on user input.
    * The intensityComponent class command runs the intensityComponent method from the model on user
      input.
    * The BlueComponent class command runs the blueComponent method from the model on user input.
    * The GreenComponent class command runs the greenComponent method from the model on user input.
    * The RedComponent class command runs the redComponent method from the model on user input.
  

Script to be executed:
#load Kindred.ppm and call it 'kindred'
load src/Kindred.ppm kindred

#brighten kindred by adding 10  
brighten 10 kindred kindred-brighter

#flip kindred vertically
vertical-flip kindred kindred-vertical

#flip the vertically flipped kindred horizontally
horizontal-flip kindred-vertical kindred-vertical-horizontal

#create a greyscale using only the value component, as an image kindred-greyscale
value-component kindred kindred-greyscale

#save kindred-brighter
save src/kindred-brighter.ppm kindred-brighter

#save kindred-greyscale
save src/kindredValue.ppm kindred-value

#overwrite kindred with another file
load src/kindredValue.ppm kindred

Class diagram:

see 'src/diagram.png'

Source: The "Kindred.ppm" image used for this source was taken by me, Fernando Garcia, and I
authorize the
image's use for this project.
  