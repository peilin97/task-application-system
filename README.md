# HW9 writeup
## 1. Builder:
 ```Todo```: Seperate the construction of Todo object, so that the same construction process can create different representations   
 ```TodoSystem```: Seperate the construction of TodoSystem object, so that the same construction process can create different representations   
 ```Filter```: Seperate the construction of Filter object, so that the same construction process can create different representations
## 2. Singleton:
 ```TodoSystem```: make sure the project only initiates one Options
## 3. Factory:
 ```InstrucitonFactory```: to support multiple implementations of IInstruction objects, methods can decide which subclass to return
## 4. Iterator:
 ```CategoryIterator```: separates the structure of a collection from the process of iterating over it by creating a class that encapsulates a particular approach to traversing the collection   
 ```IncompleteTodoIterator```: separates the structure of a collection from the process of iterating over it by creating a class that encapsulates a particular approach to traversing the collection
## 5. Adapter:
 ```IInstruction```: make 3 instructor classes that inherit IInstruction class and change functionality to follow different instructions
## 6. Decorator:
 ```ErrorLoggerDecorator```: extends the functionality of ErrorLogger