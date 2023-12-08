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
- was there anything specific you ran into???
