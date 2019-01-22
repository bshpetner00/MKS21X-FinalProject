# guapGang
Final Casino Project - Ben Shpetner, Alexander Zou, Period 3

---

Welcome to Big Guap Casino! Here we have a grand total of five (5) casino games you can play!
- Video Poker, Ceelo, Slots, Blackjack, and Roulette

To compile, type in (the terminal):

javac -cp lanterna.jar:. Casino.java

To run, type in:

java -cp lanterna.jar:. Casino

For Windows systems, for compiling type in:

javac -cp "lanterna.jar;." Casino.java

And for certain Windows systems where for some reason just using "java" won't let it run, type in (after compiling):

javaw -cp "lanterna.jar;." Casino

All Directions on how to play the game will be displayed when running it.
Have Fun!

---

Development Log:

1/4/19
- Created repo
- Started work on Slots (Alex) and Ceelo (Ben)

1/5/19
- Alex:
  - Created scaffolds of Slots and Game classes

1/6/19
- Ben:
  - Tested tentative build of Lanterna on Windows, needs testing on Linux

1/7/19
- Alex:
  - Merged branches so Game and Slots classes are available to put in map
  - Started on map and struggled to get new terminal stuff to work on windows

1/8/19
- Alex:
  - Got map to work on home computers (sort of)
  - Tried (but failed) to create slots on map
- Ben:
  - Made substantial progress in ceelo roll and calculate functions, restructred the program

1/9/19
- Alex:
  - Some success in creating basic map stuff
  - Learned about Screen and KeyStrokes
- Ben:
  - Redesigned ceelo to make it more in line with the structure of the casino

1/10/19
- Alex:
  - Started and implemented Player class
  - Finished implementing Slots

1/12/19
- Alex:
  - Finished Player stuff in terminal
  - Started and (tentatively) finished Deck and Cards
- Ben:
  - Created video poker scaffold
  - Finished and implemented ceelo into the casino

1/13/19
- Ben:
  - 75% done with video poker
  - Tested all the betting and moving to make sure that the casino works as intended on all systems for the demo

1/14/19
- Ben:
  - Slight improvements to video poker

1/15/19
- Ben:
  - Fixed blackjack bugs
- Alex:
  - Added directions for Slots and Blackjack

1/16/19
- Ben:
  - Finalized prize output for vpoker
- Alex:
  - Added rule pages for Slots and Blackjack
  - Began to beautify game pages

1/17/18
Ben:
- Completely finished vpoker, need to implement

1/18/19
- Ben:
  - Started vpoker implementation

1/20/19
- Ben:
  - Edits to Poker
  - Partial implementation of Poker
- Alex:
  - Started and (essentially) finished Roulette class
  - Successfully implemented basic version of Roulette

1/21/19
- Ben: 
  - Created Roulette color betting system
  - Fixed bugs with Roulette betting
  - Fully implemented Vpoker
  - Decorated Big Guap Casino and made it a little more inviting
- Alex:
  - Polished up Roulette rules screen and directions
  - Bug fixes
  - Directions and description in the README file
