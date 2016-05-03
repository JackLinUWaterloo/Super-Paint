/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Desc: This is a linkedlist class with many acessing and mutating functions that allows the user
 *       to store data or manipulate them.
 */

public class LinkedList<T> {
    //instantiate necessary variables
    private int numberOfNodes = 0; 
    private ListNode<T> front = null;
    private ListNode<T> tail = null;
    
    //This method checks if the linked list is empty or not, does not take any parameters, returns a boolean value
    public boolean isEmpty() {
        return (front == null);
    }
    
    //This method deletes all the nodes in the linked list, takes no parameter and returns nothing
    public void makeEmpty() {
        front = null;
        numberOfNodes = 0;
    }
    
    //This method returns the size of the linked list, takes no parameters and returns an integer value
    public int size() {
        return numberOfNodes;
    }
    
    // This function adds a node to the front of the linked list, takes a generic parameter and returns nothing.
    public void addFront( T element ) {
        front = new ListNode<T>( element, front );
        numberOfNodes++;
        
        if (numberOfNodes == 1) {
            tail = front;
        }
    }
    
    //This method returns a reference to the data in the first node, or null if the list is empty, takes no parameters
    public T first() {
        if (isEmpty()) 
            return null;
        
        return front.getData();
    }
   
    //This method returns a reference to the data in the first node, or null if the list is empty, takes no paramters
    public T last() {
        if (isEmpty())
            return null;
        
        ListNode<T> end = front;
        for(int count = 0; count < numberOfNodes-1; count++){
            end = end.getNext();
        }
        return end.getData();
    }
            
    //This method removes a node from the front of the linked list (if there is one), returns a reference to the data
    //in the first node, or null if the list is empty, takes no parameter
    public T removeFront() {
        T tempData;
        
        if (isEmpty()) 
            return null;
        
        tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }
    
    //This method returns true if the linked list contains a certain element, or false otherwise, takes a generic
    //parater value.
    public boolean contains( T key ) {
        ListNode<T> searchNode;
        searchNode = front;
        while ( (searchNode != null) && (!key.equals(searchNode.getData())) ) {
            searchNode = searchNode.getNext();
        }
        return (searchNode != null);
    }
    
    //This method returns String representation of the linked list, takes no parameters
    public String toString() {
        ListNode<T> node;
        String linkedList = "FRONT ==> ";
        
        node = front;
        while (node != null) {
            linkedList += "[ " + node.getData() + " ] ==> ";
            node = node.getNext();
        }
        
        return linkedList + "NULL";
    }
    
    //This method insert a node in the list with a given key value, takes a generic Comparable value and
    // returns nothing
    @SuppressWarnings("unchecked")
    public void insert( Comparable<T> key ) {
        ListNode<T> before = null;
        ListNode<T> after = front;
        ListNode<T> newNode;        
        
        // Traverse the list to find the ListNode before and after our insertion point.
        while ((after != null) && (key.compareTo(after.getData()) > 0)) {
            before = after;
            after = after.getNext();
        }
        
        // Create the new node with link pointing to after
        newNode = new ListNode<T>( (T)key, after);
        
        // Adjust front of the list or set before's link to point to new node, as appropriate
        if (before == null) {
            front = newNode;
        }
        else {
            before.setNext(newNode);
        }
        numberOfNodes++;
    }
    
    //This method deletes a node in the list with a given key value, takes a generic value as parameter, and returns a
    //boolean value
    public boolean delete(T key){
        ListNode<T> before = null;
        ListNode<T> after = front;
        //check if list is not empty and if it contains the search key, proceed if both true
        if(isEmpty() == false && contains(key) == true){
            //traverse through the list to find the ListNode before and after the deletion point
            while ( (after != null) && (key.equals(after.getData()))==false) {
                before = after;
                after = after.getNext();
            }
            //check if deleting the first value, if so, set front pointer to next data address
            if(before == null){
                front = front.getNext();
            }
            //if deleting last value, set next last pointer to null
            else if(after.getNext() == null) {
                before.setNext(null);
            }
            else{
                before.setNext(after.getNext()); //if deleting in between, point before to the data after the deletion point
            }
            numberOfNodes--;
            return true;
        }
        else{
            return false;
        }
    }
    
    //This method adds an element to the end of the list, takes a generic value, and returns a generic value as well
    public T addEnd(T element) {
        ListNode<T> newNode = new ListNode<T>((T) element);
        //checks if tail is pointed to null, if so, simply add the element to the front
        if(isEmpty() == true) {
            addFront(element);
        }
        else { //if not, tail points to a new node and resets itself to the next data address
            
            tail.setNext(newNode);
            tail = tail.getNext();
        }
        numberOfNodes++;
        return element;
    }
    
    //This method deletes the last element of the list, takes no parameters, and returns nothing
    public void removeEnd() {
        ListNode<T> before = front;
        //check if there is only one node, if so, simply call removeFront
        if(numberOfNodes == 1){
            removeFront();
        }
        else if (isEmpty() != true){
            //get the second last node in the list
            for(int count = 1; count < numberOfNodes-1; count++)
            {
                before = before.getNext();
            }
            //points the second last node to null
            tail = before;
            tail.setNext(null);
        }
        
        if(size() > 0) {
            numberOfNodes--;
        }
    }
}