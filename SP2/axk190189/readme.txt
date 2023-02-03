Steps to Run the Project

*** Method 1 ***
Open the project folder in an IDE (Preferrably IntelliJ) and run the file DoublyLinkedList.java.

*** Method 2 ***
1. Extract the contents of the zip file.
2. Open a terminal window in the directory which contains the extracted folder.
    2.1 Compile the SinglyLinkedList.java file using:
        javac axk190189/SinglyLinkedList.java

    2.2 Compile the DoublyLinkedList.java file while providing path to SinglyLinkedList class files using:
        javac -cp . axk190189/DoublyLinkedList.java

    2.3 Run the DoublyLinkedList.java file using:
        java axk190189.DoublyLinkedList


Once the program is run, a doubly linkedList will be created with size 10 and elements from 0 to 9.
From the program menu, you can press 1 to move to the next element, 2 for previous element, 3 for remove and 4 to add
an element.
In order to exit the menu and the program, press any other number.