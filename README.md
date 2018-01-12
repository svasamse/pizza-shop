# pizza-shop

## Problem statement
Pizza shop receives order and saves them in a text file however they are not in correct order.
The application will arrange the list in correct order. 

## Assumptions
* The file should be in `UTF-8` format only
* The program will be executed using Java 7 and over only
* The items will be sorted based on the topping name
    * When there are more than one topping with the same name, then they will be ordered by the timestamp
* The first line in the file will be considered as a header and will not be included for sorting
* The line items in the file other than the header will need to be in the following format
    * Any number of alphanumeric characters including space(s)
    * Followed by two or more spaces
    * Followed by Epoch timestamp
    * End of line
* If there are any line items not following the above pattern, then the process will quit with an error
* All timestamps will be displayed in the local timezone only with the format `MM/dd/yyyy hh:mi a`
```
    01/21/2018 07:47 PM 
    01/17/2018 11:53 AM
```

## Running the program
To build the project run the following command in the command line

    $ mvn clean package
    
The build will generate an executable jar file in the `target` directory which will need to be run as follows.

    $ java -jar pizza-shop.jar -DorderFile=<path-to-order-file>

The order file is a mandatory parameter and the process will exit returning an error if it is not provided.