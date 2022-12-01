This project is a successful implementation of an Image Processing Program respecting the MVC design
pattern.

All functionality required, except the "text" command-line argument, works correctly as intended,
including:

- Load
- Save
- Blue component
- Red component
- Green component
- Value component
- Intensity component
- Luma component
- Brighten
- Darken
- Grayscale
- Blur
- Sepia
- Sharpen
- Vertical flip
- Horizontal flip
- -file command (NEEDS TESTING)

To use the program, simply run the program from the main method in the class named "
ImageProcessingProgram" located in the src folder. The program will open up an interactive GUI. In
order to use the program, notice the buttons in the bottom button panel of the GUI. Load an image to
display in the ImagePanel before attempting any other commands. An example to use is the Kindred.ppm
and Kindred.png files in the res folder, but any image on your computer suffices. All non-save
commands require a temporary image name for the program to store the newly created image in the
model. NOTE that this does NOT save an image, an image must be manually saved using the save button,
where one can choose a destination for the file on your computer before naming the file to save. (
NOTE: Only JPG, JPEG, PNG, BMP, and PPM extensions are supported).

Between the image panel and the buttons panel is the histogram that automatically updates to show
the red, green, blue, and intensity component histograms for the image currently displayed on the
image panel. In order to see the full histogram, the GUI window must be maximized to scroll through
and see its full display.

Command-line arguments:

The following details how to execute each script command:

- load image-path image-name
- save image-path image-name
- red-component image-name dest-image-name
- green-component image-name dest-image-name
- blue-component image-name dest-image-name
- value-component image-name dest-image-name
- intensity-component image-name dest-image-name
- luma-component image-name dest-image-name
- horizontal-flip image-name dest-image-name
- vertical-flip image-name dest-image-name
- brighten increment image-name dest-image-name
- darken increment image-name dest-image-name
- grayscale image-name dest-image-name
- blur image-name dest-image-name
- sepia image-name dest-image-name
- sharpen image-name dest-image-name
- -file path-of-script

NOTE: We have yet to implement the -text command-line feature. Additionally, our JAR works as
intended (not included for hw7 submission as file is too large and also not required), opening the
program from command prompt or upon opening the JAR file, and runs the -file command. In other
words, the following command-line arguments work as intended:

- java -jar Program.jar -file path-of-script-file
- java -jar Program.jar

The only missing feature is the -text command-line argument which is yet to be implemented.
