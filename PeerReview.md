## Peer Review

### Design critique
The provider's design
- i think this would be about the diagram you made, so just, good diagram? bad diagram? hard to work with diagram?
- something about the design being confusing with the set focus

### Implementation critique
Their implementation was not too hard to work with. We had to make a lot of adapters to convert from
a type used in the provider's code to a type used in our code. For example, converting one read-only
model to the other type or one type of strategy to another type. This, however, was to be expected 
and was not too complicated. Overall, their implementation was pretty easy to work with and there
was only one decoupling change we needed them to make. This change was in their MinMax strategy 
where they made a copy of the model by creating a new instance of a model and passing in the old
model as 

### Documentation critique
The documentation was unclear. While every method had javadoc and there was a README file, the 
comments weren't particularly explanatory and were more vague. It would have been nice to have more
explanations about the control flow and how the model, view, and controller, were talking to
each other.
There were also a couple instances of outdated documentation and changes made that were not 
documented. One example was continuously, through all the javadoc, saying a method would be 
returning a null when it was actually returning an enum, which we confirmed with the group.

### Design/code limitations and suggestions on how to address them
- We did run into multiple clarification issues because of javadocs that were not clear and we had
  to follow up:
  - The javadoc for the getMove() method in the IStrategy interface said that it would return a 
    null if the strategy was not able to make a move. However, the MinMax strategy was returning
    an enum instead of a null. We had to follow up with the group to confirm that the javadoc was
    outdated and that the strategy was supposed to return an enum.
  - the javadoc for the features interfaces and not explaining how they were interacting with the 
    view's focus and how the view's focus was being set. We had to follow up with the group, drawing
    a diagram and figuring out how the focus was being set.
- Limitations:
  - They did not implement chaining strategies in their main method, so we did not either. There was
    1 preset strategy configurations which we did implement.
  - their view did not resize vertically when the window was resized 
    (it did how resize horizontally)
  - they did not implement having the model take in players of any color, instead relying on the 
    view to enforce the correct player's turn through the use of focus and yourturn method. We had 
    to follow up with them to confirm this, and went with a simpler approach of having our model 
    adapter enforcing the correct player's turn.
  - their view also had four private classes that tightly coupled functionality 
    

