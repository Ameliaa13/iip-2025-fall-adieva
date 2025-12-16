public interface MyLinkedList<T> {

    Node<T> getFromStart();

    Node<T> getFromEnd();

    Node<T> getByIndex(int index);
    Node<T> getByData(T e);


    void addToStart(T e);
    void insert(T e, int index);
    void addToEnd(T e);


    void removeFromStart();
    void removeFromEnd();
    void removeByIndex(int index);


    boolean isEmpty();

    int size();

    void printLinkedList();


}
