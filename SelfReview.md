## Self Review

### Reflect on our design
- What do you wish you had done differently in your design/implementation? Did you learn any 
lessons from the code given to you, or the updates you had to make for your customers?


It was very interesting to first-hand experience the process of using and integrating code we had
never touched before, and to be on both sides of the equation. We definitely learned the importance
of having very clear and detailed documentation, so that someone can understand the ins and outs
of you code without needing to read through every line.

We would have decoupled the code more mainly the Hexagon class which we basicalyl used as a utils 
class and stuck all our functionality in, so it ended up being tightly coupled. We would have made
our abstract playing board class an interface instead of an abstract class, so that we could have
more generally decoupled that design.

We should have made BoardTile interfaces of which hexagon would implement.

### Interactions with providers
Our providers were lovely to work with. They had quick responses and were very helpful. They were
very knowledgeable about their code and knew the flaws and kinks it had.
