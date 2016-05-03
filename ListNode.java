/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Desc: This class provides the nodes for a linked list.
 */

public class ListNode<T> {
    //instantiate necessary variables
    private T data;
    private ListNode<T> next;
     
    // Constructor: No reference to next node provided so make it null 
    public ListNode( T nodeData ) {
        this( nodeData, null);
    }
     
    // Constructor: Set data and reference to next node.
    public ListNode( T nodeData, ListNode<T> nodeNext ) {
        data = nodeData;
        next = nodeNext;
    }
     
    // Accessor: Return the data for current ListNode object
    public T getData() {
        return data;
    }
     
    // Accessor: Return reference to next ListNode object
    public ListNode<T> getNext() {
        return next;
    }
     
    // Mutator: Set new data for current ListNode object
    public void setData( T newData ) {
        data = newData;
    }
     
     
    // Mutator: Set new reference to the next node object
    public void setNext( ListNode<T> newNext ) {
        next = newNext;
    }
}