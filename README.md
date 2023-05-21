 ![GitHub contributors](https://img.shields.io/github/contributors/MightyArty/Ex2_Graphs?style=plastic) ![GitHub commit activity](https://img.shields.io/github/commit-activity/m/MightyArty/Ex2_Graphs?style=plastic)
# Autores: David Yosopov, Tom Shabalin and Lior Patael
# This is a graph algorithm in Java 
3'd project in OOP course at Ariel Univeristy using Java. For each given Json file we'll load the data and save it in our data structure for further calculations.
This project is mainly about directed graph, and it allows you to set and display a directed graph.
Our project contains API folder that includes interfaces for the assignment, ALGO folder where we implemented our algorithm,TEST folder and GUI folder for demonstration purposes
# Node class
Represent the NodeData functions (key,location,getWeight,setWeight...)
# Location class
Represent the GeoLocation functions (distance,getX,getY,getZ)
# Edata class
Represent the EdgeData functions (getSrc,getDest,getWeight,getInfo)
# DWGraph class
Represent the DirectedWeightedGraph functions (getNode,getEdge,addNode,connect,node/edgeIter...)
# DWGraphAlgorithm
Represent the DirectedWeightedGraphAlgorithms functions (init,copy,isConnected,shortestPathDist,center...)
# We used this algorithms in order to complete the task
- [Graph Center](https://en.wikipedia.org/wiki/Graph_center)
- [Travelling salesman problem](https://en.wikipedia.org/wiki/Travelling_salesman_problem)
- [Shortest Path Problem](https://en.wikipedia.org/wiki/Shortest_path_problem)
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
- [Floyd Warshall algorithm](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm)
# GUI class :
This class represent the GUI running simulator for our algorithem.<br/>
[![CLICK HERE](https://i.ibb.co/8KNR8KC/Screen-Shot-2021-12-13-at-18-09-11.png)](https://www.youtube.com/watch?v=rzKde6IoVKQ "CLICK HERE")
<br />
<br />(Click on the image to load the vdeio)This video shows how to load the jar file that we've made using termminal ( java -jar FILENAME.jar JSONFILENAME.json) although you can run the jar file directly from the folder / from the IDE. 
<br /> The GUI has top menu bar with several options: FILE TAB -  loading from Json,saving to Json,determine the program.
EDIT TAB: adding and removing nodes and edges,deleting the graph. GRAPH TAB: tsp,isconnected,shortest path,shortest path dist and center.
# Algorithms Results
![](https://i.ibb.co/S59Y5Dk/Screen-Shot-2021-12-13-at-17-55-20.png)
We've checked the results of our main algorithms through the test classes (using Junit)
