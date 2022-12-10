# Mosaic Image Processor

_By Ben Bishop and Smita Rosemary_

Note: see the original USEME file (in src/USEME.md) for better instructions.

## How to use

As with the original program, the terminal view and the GUI view are combined. Run the program by
copying the file in `src/res/ImageProcessor.jar` to another folder, and run using the same command
as the original program.

## Overview

To implement the mosaic feature we had to:

1. Create a new model and interface (IMosaicProcessingModel) that extends the original model. This
   is because image transformations are made in the model, and we needed to add a new method to
   support mosaic.
2. Modify the GUI interface to support the mosaic command. The class was not extensible. We:
    * Added a new JButton field
    * Initialized the new JButton field in the constructor
    * Updated the addFeatures method to add the mosaic action listener
3. Modify the controller to support the mosaic command. The class was not extensible. We:
    * Added a new command object for Mosaics, after adding a new interface to support the new model
    * Modified the run method to check for the "mosaic" command and run the mosaic command
    * Modified the controller to take in the new model object in the constructor instead