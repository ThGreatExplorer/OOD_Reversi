# Hexagonal Reversi Game
**By Daniel Yu and Anika Sharma**

- [Model] (#Model)
  - [Colors] (#Colors)
  - [Coordindate System] (#Coordinate System)
  - 

## Model

### Colors 
We created a public enum representing the Players white and black, following the 
specifications of the assignment. But since it is an enum, we can easily allow 
for additional players by adding new enumerations. We gave the enum a next 
method which would just return the next Player in the sequence by ordinal to 
allow for players to switch turns.

### Coordinate System
The first design decision we had to make for this assignment was the coordinate
system for the Hexagonal Grid. We decided to use the Cube Coordinate System for
several reasons:
1. We wanted to track the q,r,s coordinates so when implementing our model we had
   the ability to directly generate the line across the q coordinate, r coordinate,
   and s coordinates which would make validating and performing moves easier.
2. Although the axial coordinate system simplifies the cube coordinate system into
   two coordinates while preserving the q+r+s = 1 equality. We felt that having
   the two coordinates while making our code seem simpler actually reduces readability
   since now the developer must remember and recompute that s coordinate.
This design decision now meant that the Hexagon object should have three fields,
one for each coordinate.

When generating the coordinate system of all the hexagons in the grid, we knew that:
1. the generated product must be a large **REGULAR** hexagon composed of unit hexagons
2. the distance must be equilateral to the origin from any hexagon on the edge, essentially
   forming concentric "rings" of hexagons for each unit hexagon distance away from the origin.
Thus, this made determining the origin really easy, as we could simply set the Hexagon
as the hexagon at the center of the coordinate system and generate Hexagons ring by ring
until we reached the specified distance away from the center.

We decided to arbitrarily set the direction that the hexagons would be generated in
as clockwise (either cw or ccw would have been equivalent).

### Board

#### Board Generation
The second design decision, building on our coordinate system, was to design the Board
class that would contain the previous logic of generating the board with the coordinate
system as well as serving to keep track of the state of the Board in a game of Reversi.

Thus, the Board will have all the logic of what a valid Hexagonal Board for the game 
**SHOULD BE** and updating the state of the Game when any move is played.

> Based on this idea, we made the Board only take in an integer specifying the size (i.e.
number of concentric hexagon rings the board should have). We made the decision to 
disallow the board to have less than a size of 2, meaning a board of only 1 hexagon and
the board of only 7 hexagons (starting state of 3 white, 3 black) would be disallowed
since in the first option, nothing could be moved and in the second option, the 
game would be trivial. So the game has to at least have 19 hexagons and 3 rings. Notes
that size is indexed at 0, so two rings would be size of 1 and three rings would be size 
of 2.

Then we implemented the generation following the specifications above of generating
in clockwise direction starting from the origin and rotating outward where the hexagon
to the left is generated first then the rest in clockwise order until the ring is 
completed in which case we repeat until size is reached. 

This was done using the idea of Neighbors in [redblobgames](www.redblobgames.com/grids/hexagons/)
and the cube_direction_vectors representing the 6 surrounding hexagons of a given hexagon
where we used cube_direction_vectors to recursively generate those neighboring hexagons 
until done.

#### Game State
For purposes of tracking the game state, i.e. which Hexagons are occupied and which 
Players are occupying said hexagons, we decided to implement a Hashmap containing 
**ONLY** the occupied hexagons as keys and the occupying player as values. Then in the model
we made sure to update the Board appropriately.

### Model
Based on our prior design decisions, this meant all we had to do was have the model
get the Game State, implement the logic of rules-keeping, then perform a move when 
valid and update the Game State.

So we gave the model a field containing the Board (representing the game state at 
any given time for the Model) and the current Player whose turn it is. We made
the decision to start the game with the first player as WHITE, since in board
games white always goes first. 

Through our logic, each model would generate a new Board which would track that
models game state, thus allowing the model to only have to handling getting the
game state from the Board then using that information to validate and perform 
moves, then updating the Board. This is all enforced in the specific methods we
implemented.


## Player
TODO

## View 
TODO

## Controller
TODO 