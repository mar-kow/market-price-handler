This implementation is based on architectural pattern called Hexagonal Architecture.

[App](src%2Fmain%2Fjava%2Forg%2Fexample%2Fprice%2FApp.java) 
class demonstrates how market prices from csv file are fed into the system and then processed.

[LatestPriceController](src%2Fmain%2Fjava%2Forg%2Fexample%2Fprice%2Fadapters%2Fcontrollers%2FLatestPriceController.java)
is a rough example of controller with all its limitations mentioned in the class comment.

Not everything was unit tested due to time constraints but at least I tried to test all domain components.