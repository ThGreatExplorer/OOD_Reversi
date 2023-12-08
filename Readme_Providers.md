**Note: refer back to the original README.md for the rest of the project description,
but refer to this document for how to run the project with the providers code.**

The interfaces we were given were fairly straightforward to implement. They were flexible but at
 the same time tightly coupled with the view design were the setfocus and removefocus methods 
relied on the view implemented focus functionality. The code was well-structured.

## Changes for Part IV to allow functionality with providers code
1. Strategies:
    1. Created a strategy adapter class to use the provider's strategy, yet return a move in the
       format of an array of q, r, s.
    2. Added to getStrategy() in Reversi class to make sure only Player 2 had access to the
       additional strategies from the providers

## Successful Features        
1. Implemented functionality with all the providers strategies
2. Implemented an adapter for the provider's hexCoord,TokenStatus enums
3. Implemented adapters for the provider's read-only model and the read-write model
4. Implemented the provider's feature interfaces and had them work with our adapters to play
   the game as human, ai, and with all strategy combinations

## Other Features
1. We made the decision to not implement the provider's IController interface since it was empty
   and commented never to be used.
2. We learned that our providers did not implement chaining strategies in their main method, so we 
   did not either. There was 1 preset strategy configurations which we did implement.

