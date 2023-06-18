# Project 3 Prep

**1. What distinguishes a hallway from a room? How are they similar?**

> Hallways should have a width of 1 or 2 tiles and a random length.
Rooms and hallways must have walls that are visually distinct from floors.

**2. Can you think of an analogy between the process of 
drawing a knight world and randomly generating a world 
using rooms and hallways?**

> 1.  The commonality between the two algorithms is that they both start with a grid in a lattice form. Additionally, they both have rules for movement and room placement, resulting in the creation of patterns or maps.commonality between the two algorithms is that they both start with a grid in a lattice form. Additionally, they both have rules for movement and room placement, resulting in the creation of patterns or maps.

**3. If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually 
get to the full knight world.**

> **[Methods]**
> 1. generate_rooms(): generates a set of rooms with random sizes and positions within the world.
     generate_hallways(): generates a set of hallways connecting the rooms in a logical way, such as by proximity or connectivity.
> 2. place_objects(): randomly places objects like treasure, enemies, or obstacles within the rooms and hallways of the world.
     render(): displays the world in some graphical format, such as a top-down view or a 3D model.

**4. This question is open-ended. Write some classes 
and methods that might be useful for Project 3. Think 
about what helper methods and classes you used for the lab!**

> **[Classes]**
> 1. Room: represents a rectangular room in the world, with properties like size, position, and contents (such as treasure, enemies, or obstacles).
> 2. Hallway: represents a straight or curved corridor connecting two rooms, with properties like length, width, and direction.
> 3. World: represents the overall structure of the world, containing a collection of rooms and hallways, as well as any global properties like the size and shape of the world.

> **[Helper classes and Methods]**
> 1. Point: a simple class representing a point in 2D space, with x and y coordinates.
> 2. Direction: an enumeration representing the four cardinal directions (north, south, east, west).
> 3. is_within_bounds(): a utility method that checks whether a point lies within a given rectangle.
> 4. find_adjacent_points(): a utility method that finds all points adjacent to a given point, in a specified direction.