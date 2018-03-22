# pizza-shop

## Problem statement
Pizza shop receives order and saves them in a text file however they are not in correct order.
The application will arrange the list in correct order.
 
## Prerequisites
JDK 7+

Maven is optional, since a wrapper is provided with the application

## Assumptions
* The input file should be in `UTF-8` format only
* The items will be sorted based on the pizza name
    * When there are more than one pizza with the same name, then they will be ordered by the timestamp
* The first line in the file will be considered as a header and will not be included for sorting
* The line items in the file other than the header will need to be in the following format
    * Any number of alphanumeric characters including space(s)
    * Followed by two or more spaces
    * Followed by Epoch timestamp
    * End of line
* If there are any line items not following the above pattern, then the process will quit with an error
* All timestamps will be displayed in the local timezone only with the format `MM/dd/yyyy hh:mm a`
```
    01/21/2018 07:47 PM 
    01/17/2018 11:53 AM
```
* System default line separator will be used to delimit the lines in the output file

## Design
The application is broadly classified in to the following packages.
* model - The data in the file is converted in to the classes defined in this package
* service - All data manipulation logic will be handled by the class(es) defined in this package
* exception - All application related exceptions are defined here

The process of sorting the pizzas is as follows.
* Read the pizzas related to the order from the given file and convert it into an `Order` object
* Sort the collected pizzas based on the name, and time the pizza was ordered (in case the same pizza has been ordered more than once)
* The sorted pizzas for the order will then be outputted to the destination file in the following format
```
Order		time
bread		01/17/1970 05:37 PM
bread		01/17/1970 06:20 PM
```

## Running the program
To build the project run the following command in the command line

    $ mvnw clean package
    
The build will generate an executable jar file in the `target` directory which will need to be run as follows.

    $ java -jar pizza-shop.jar -DorderInputFile=<path-to-order-file> [-DorderOutputFile=output.txt]

The system property `orderInputFile` is mandatory. The system property `orderOutputFile` is optional and will default to the value `output.txt`


This is just for testing...
