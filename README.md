# domasna
## PRVA ZADACA
Create a class `DN11`(in a file `DN11.java`) and a method in it `main()`.  **zavrseno**

## Network presentation classes
Write the class `Vozlisce` that represents the node (point) on the map. Each node should contain information about its y and x coordinates (types `double`), which will be determined as the latitude and longitude of the geographical point in decimal degrees (eg 46.050389 and 14.468778). **zavrseno**

Write a class `Cesta` that will represent the road between the two nodes. As an attribute, it should contain both nodes that connect them (type `Vozlisce`). In addition, it should contain information on the maximum permitted road speed (type `int`). In `Vozlisce` Add a list of roads `List<Cesta> ceste` that will include all the roads that connect this node to neighboring nodes. When creating a type object `Vozlisce`, the list of roads should be empty. In `Vozlisce` also add a method `dodajCesto(Cesta cesta)` that adds the road to the list of connected roads. **METODA DODAJ CESTO DA SE NAPRAVI**

Add class `Kraj` and `Crpalka`, class extension `Vozlisce`. `Kraj` should additionally contain information on the name of the place (type `String`) `Crpalka` information on the prices of one liter of 95-octane petrol and diesel (type `double`). **zavrseno**

Write another class `CestnoOmrezje` that will represent the entire road network. It should contain an attribute `Vozlisce[] vozlisca` that will contain all nodes and an attribute `Cesta[] ceste` that will contain all roads in the network. **zavrseno**

Attributes of all classes should be private, and you can add getter and setter methods to access them as needed. Also add appropriate constructors to set attribute values. If necessary, you can add other attributes to the task.
Tip: To help you solve some tasks, you can add an attribute to the class  `int id` that will hold the node index. **zavrseno**
  
## Reading data

Add a static method to the class `CestnoOmrezje` `CestnoOmrezje izDatoteke(String imeDatoteke)` to read the road network from the file ( example_network.txt ). The method should return an object of type `CestnoOmrezje` that contains nodes and roads as specified in the file. **TUKA SME**

The file in the first line contains the number of nodes N and the number of roads M separated by a space. Followed by N lines with descriptions of individual nodes. This is followed by M lines with road data.
Node data is as follows:

`<node_type> <x_coordinate> <y_coordinate> <additional_data ...>`
The node type is one of the values: node, location, or pump. Depending on the type of node, the data may contain additional information:

`vozlisce <x_coordinate> <y_coordinate> `
`kraj <x_coordinate> <y_coordinate> <ime_kraja> `
`crpalka <x_coordinate> <y_coordinate> <cena_95> <cena_diezel>`

Depending on the node type, create an object of the appropriate type ( `Vozlisce`,  `Kraj` or `Crpalka`) and save it in the node table.


**Attention : The name of a place can consist of several words. Be sure to read it correctly.**

Road data are in the following forms:

`<node_1> <node_2> <speed_limit>`

The nodes are given as indexes of the nodes given in the first part of the file (eg index 0 means the first node and so on). Based on this data, create a type object accordingly Cesta. Remember to add roads to both nodes by method `dodajCesto()`.

## Road list
Add a method to the class `Cesta` `double getDolzina()` that returns the length of the road in kilometers. Calculate it from the coordinates of the nodes. Take into account the simplified formula, which is an approximation for our places: one angular degree of latitude corresponds to 111.12 km and one angular degree of longitude corresponds to 77.4 km. **POCNATO**

Also, implement in class the method `toString()` to represent the road with a set of shapes, as in the following example:

`Road (0,1): length = 17.70 km, limit = 130 km / h`

The values in parentheses represent the indexes of the nodes connected by the road and the length the length of the road, rounded to two decimal places, which is obtained by calling the previously implemented method getDolzina().

## Running the program
You will run the program using the `main()` class method `DN11`, which should execute the appropriate part of the program according to the first argument (command). Implement a command `ceste` that reads the road network data from the specified file and stores it in the appropriate structure as described above. Then have it print out the data on all the roads in the road network. The print order should be the same as the road order in the input file.

The printout format is shown in the following example. On call

java DN11 road example_network.txt
the program prints

`The network contains the following roads: `
  
`Road (0,1): length = 4.96 km, limit = 130 km / h `
  
`Road (1,2): length = 3.88 km, limit = 110 km / h `
  
`Road (2,3): length = 3.95 km, limit = 110 km / h  `
  
`Road (3,4): length = 6.00 km, limit = 130 km / h  `
  
`Road (4,0): length = 8.63 km, limit = 130 km / h  `
  
`Road (0,6) ): length = 3.08 km, limit = 130 km / h `

