# Code Critique

_by Ben Bishop and Smita Rosemary_

## Questions to consider (REMOVE)

* How flexible are the interfaces you were given?
* How good was the implementation?
* What are some specific strengths of their design and/or implementation?
* What are some specific limitations of their design?
* How convenient was it to use their code?
* How cleanly written and well-documented was their code?
* If you needed to request changes from them, what were they and why were they needed?

## Notes (REMOVE)

1. Model is not easily extendable. It was hinted repeatedly that future transformations would need
   to be supported, but you cannot currently add new transformations without adding a new method to
   the model. This is a violation of the open-closed principle.
2. Many mutable fields that should not be mutable are exposed via getter methods (ex. getImages())
3. The controller is not easily extendable either. Command pattern was "implemented" but not in a
   way that makes implementing new features any easier. All it does is call a method in the model.
4. For some reason, the GUI and terminal view are merged into one main method. The terminal view
   should be abstracted from the GUI view – it should be one or the other, not both when you run.
5. Tests are not comprehensive. They only test the model, there is aspects of the controller and the
   model that should be tested.
6. GUI is not very user-friendly (but might not be relevant?)

## Review (REMOVE HEADLINE)

Lorem ipsum blah blah blah