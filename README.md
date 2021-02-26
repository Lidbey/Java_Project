# Java_Project
Simple game frame, where you can(in code) change if you want a Hexagonal or Squared world, and then You can add animals and human - who you control

1. To add any animal(or plant), just click on any place on the map and choose an animal you want to choose
* you can also add a human, which is controlled by you (and you have a special ability that can be 
used once)

2. You can control a human by W A S D buttons (and Q Z in case of Hexagonal world)

3. After the side is choosen, you can click next turn to go to next turn.

Features:
1. Animals can copulate if they are the same race and walk on each other during a turn.
2. Animals can eat other animal if they walk on the animal with lower strength
3. Each animal race has different properties(including some special options, like turtle walks on
average only once each 3-4 turns, but he can block attacks, antelope jumps 2 places each turn etc)
4. Of course, all animals has different strength properties
5. A human can use special ability
6. There are also plants, all with different properties(like one type of plant boosts your strength,
others kills everything around, and they spread a bit differently than animals)
...


TO CHANGE TO HEXAGONAL GRID(OR THE SIZE OF A WORLD):
-Go to UI.java
-Uncomment HexWorld x=...
-Comment GridWorld z=...
-Uncomment UsrInterface y=...(x)
-Comment UserInterface y=...(z)

You can also change X and Y size of the world, it is self-adjusting, just remember that it will adjust up
to some point(for example, if you choose 1x30 world, of course it won't be adjusted too well)
