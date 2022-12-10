# Code Critique

_by Ben Bishop and Smita Rosemary_

Design critique, implementation critique, documentation critique, design/code strengths, design/code
limitations and suggestions on how to address them.

# Design critique: strengths and limitations

The biggest critique for our providers was on how they designed their Model interfaces and classes.
While their code was under the guise of using the command design pattern in the controller, it
wasn't seen in the model. This made the model difficult to work with in terms of adding and
implementing new functionality. When the time came to add the new features, in order to not modify
their code, we had to make a new Model that extended the old one. This lead to having to make a new
MosaicImageProcessingCommand as the run() method in their Commands took in their old model.
Additionally, their model included the different transformations that their processor supported.
Having the image transformations in the model is a violation of the single responsibility principle.
The model should only be responsible for storing and retrieving images, not for transforming them.
In terms of their model's design, we would give them a grade of 0.1 mainly because they only
modified their previous files instead of adding new classes an interfaces. Another major issue we
saw in how they implemented their GUI controller. Instead of making a new separate controller, they
modified the previous one to work with both a terminal view and a GUI View. This made running the
program open up both the terminal and GUI at the same time. To add on, to show an error message, our
providers' code simply crashed without hints, the only message being the error in the terminal. Some
strengths we saw in their program was that their GUI was fully functional and was easy to navigate
and use (from a user's perspective). This also made it easy to check if our mosaic implementation
was functioning correctly using the view.

# Implementation Critique

Some critiques in terms of how they implemented their model includes their definition of an image,
and some methods they implemented such as getImage(). Storing an image as a 3d array is not a good
idea, especially when the pixels are given during construction. This is a waste of memory, hard to
interpret, and hard to work with. It is also possible to pass in uneven arrays (e.g. only red and
green values but no blue), and this is never checked during construction, so it is possible to get
an array out of bounds exception from an issue with one pixel. The Image class also exposes the 3d
array to the user through their getImage() method, which is a violation of encapsulation. It is easy
to mutate an image object after creation, and while it seems this is unintentional, the
Javadoc/method signature should include a warning about it. Their implementation of the run() method in their commands should not have taken in a model interface. 
Because of this implementation, we ended up having to create a separate interface for just the Mosaic command as it had to take in a Mosaic Model instead of the old one.

# Documentation Critique

As mentioned previously, we definitely found some issues with their Javadocs. One of the biggest was
seen in the Features interface. Here there were no Javadocs to be found at all for both the
interface and all its methods. We also believe that the Javadocs they do have could've been longer
with more of an explanation of the methods and classes' functionality. Some of their Javadoc also
didn't describe the method parameters.

# Suggestions to addressing these issues.

Our biggest suggestion is definitely to properly follow the Command Design Pattern in the model as
well. This would mean making separate classes for all the transformations instead of just
implementing them as methods in the model. Doing so would reduce the need to modify their previous
classes and would make their code extensible. Additionally, to not expose the 3D array to the user,
instead of returning just that array, returning a copy of that array will not violate encapsulation.
To reduce code duplication in their implementations of their features, with their current
implementation, they could aim to abstract the method and create helper methods for the specific
cases (RGB, value, luma, and intensity components, and the different filters + color
transformations). To make their program a little more user-friendly (from the error handling side of
things), our provider could catch these exceptions where they would be thrown and display a
different more helpful message.