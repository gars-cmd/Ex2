# Ex 2


third assignment of OOP course



## Project theme


In this assignment we need to create a directed weighted graph and implement in it several functions.



## Project structure


All the files that were implemented are in the src directory.


[![UMLFinal.jpg](https://i.postimg.cc/QtHWj3nR/UMLFinal.jpg)](https://postimg.cc/0M1NCLvf)



The Graph Object contain an array List of Nodes.


Each node has as parameter a Hashmap of the Edges that coming out of it to their node.



## Algorithms


All the usage of the algorithms are made in the DWGAlgo class.


1. IsConnected()


- This function check if a graph is strongly connected (if there is a path from each node to each node).


- The algorithm realise a first BFS to mark all the nodes has visited, then reverse all the edges of the nodes and set visited tag of them to nonvisited and proceed a final BFS. If at the end all the nodes are visited then the graph is connected.



2. ShortestPath()


- This function return the weight of the shortestPath from node x to node y.


- For the Algorithm we procceed to a dijska with the help of it we update all the minimum distance from x to all others node and just pick the weight we wanted




3. ShortestPathDist()


- This function return a List of the ordered shortest path from source node to destination node


- The algorithm also proceed to a Dijska Algorithm that also store in an Array the parents of each node then we just need to run over the array and add to our list the value stored in the array in place destination then stored the value stored in the next destination add to return at the source place in the array.




4. Center()


- This function return the node that is located at the center of the graph .


- The Algorithm based on the K-center problem and use Dijska Algorithm. We iterate over all the nodes and store the weight of the shortest path from each node to each node, for each node if the minimal weight from a source node to it is lesser than the eccentricity then define the eccentricity to be it.After that we will compare all the minimal eccentricity we found with the node that is linked to and get the minimal one to be our center.


5. tsp()


- has not been done




## Libraries


- To read and write to Json file we use the org.json librarie.


- To realise the GUI we use the swing librarie.




## Test


- There is a directory Test that contain Junit Classes of all the Class we implemented.


- In the data directory multiple json file that represent graph, the G0.json file is a simple graph to make the test.



## Results



## Menu Bar
there is two main button:
- "File" to load graph from json or to save graph to json 
- "Algo" to make operation on the graph or to modified it :
  - delete point.
  - Add point.
  - Delete edge.
  - shortestPathDist -> print the weight of the shortest between two nodes.
  - shortestPath -> print the shortest path between two nodes.
  - connect -> create edge between two point .
  - isConnect -> check if the graph is strongely connected.
  - center -> return the coordinates of the center of the graph.
  - tsp
 



## More info
 - due to our use of arrayList to store the nodes  , we didn't succeed to make the possibility to removes all nodes but each time remove the node with the biggest id 
 
 
## pictures
[![pic1.jpg](https://i.postimg.cc/ZRcCCPDG/Screenshot-1.jpg)](https://postimg.cc/VrJYhtwg)
[![pic2.jpg](https://i.postimg.cc/HnvntYm3/Screenshot-4.jpg)](https://postimg.cc/5Qvf4M6F)
[![pic3.jpg](https://i.postimg.cc/wxw1zVNJ/Whats-App-Image-2021-12-13-at-16-12-57.jpg)](https://postimg.cc/K4gGrtJv)
[![pic4.jpg](https://i.postimg.cc/xT7qJ1t1/Whats-App-Image-2021-12-13-at-16-14-04-1.jpg)](https://postimg.cc/7f3qjDFF)





  
  


