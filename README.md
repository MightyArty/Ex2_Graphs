![GitHub commit activity](https://img.shields.io/github/commit-activity/m/MightyArty/Ex2_Graphs?style=plastic) ![GitHub contributors](https://img.shields.io/github/contributors/MightyArty/Ex2_Graphs?style=plastic)
# Autores: David Yosopov, Artem Shabalin and Lior Patael
# This is a graph algorithm in Java 
3'nd project in OOP course at Ariel Univeristy using Java. For each given Json file we'll load the data and save it in our data structure for further calculations.
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
# GUI class :
This class represent the GUI running simulator for our algorithem.
[![CLICK HERE](https://img.youtube.com/vi/rzKde6IoVKQ/mqdefault.jpg)](https://www.youtube.com/watch?v=rzKde6IoVKQ "CLICK HERE")
<br />
<br />In this video we can see case B5.json and Calls_d.csv.
<br />Red full dot means new call invokes (correlated with the source floor) ,the dot becomes empty in the moment the elevator reached his destination.
<br />The green dots works the same but for destinations, when call invokes green full dot appears in the destination floor
