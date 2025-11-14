class MyLinkedListImpl<T> implements MyLinkedList<T>{

    private Node<T> head;

    @Override
    public void addToStart(T e) {
        Node<T> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
    }


    @Override
    public void addToEnd(T e) {
        Node<T> newNode = new Node<>(e);
        newNode.next = null;

        Node<T> current = head;
        while(current != null) {
            if (current.next == null) {
                current.next = newNode;
                break;
            }
            current = current.next;
        }
    }


    @Override                            
    public void insert(T e, int index) {
        if (index < 0 || index > size()) {
            System.out.println("Invalid index");
            return;
        }
        Node<T> newNode = new Node<>(e);

        Node<T> current = head;

        if (index == 0) {
            addToStart(e);
            return;
        }

        for (int i = 0; i < index-1; i++)
            current = current.next;

        newNode.next = current.next;
        current.next = newNode;
    }


    @Override
    public void removeFromStart() {
        head = head.next;
    }


    @Override
    public void removeFromEnd() {
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.next == null) {
                current.next = null;
                break;
            }
            current = current.next;
        }
    }


    @Override
    public void removeByIndex(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Invalid index");
            return;
        }

        Node<T> current = head;

        if (index == 0) {
            removeFromStart();
            return;
        }

        for (int i = 0; i < index-1; i++) {
            current = current.next;
        }

        current.next = current.next.next;
    }


    @Override
    public Node<T> getFromStart() {
        return head;
    }


    @Override
    public Node<T> getFromEnd() {
        Node<T> current = head;
        Node<T> prev = head;
        
        while (current != null) {
            prev = current;
            current = current.next;
        }
        return prev;
    }


    @Override
    public Node<T> getByIndex(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Invalid index");
            return null;
        }

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }


    @Override
    public Node<T> getByData(T e) {
        Node<T> current = head;
        while(!current.getData().equals(e)) {
            if (current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }


    @Override
    public boolean isEmpty() {
        return head == null;
    }


    @Override
    public int size() {
        if (isEmpty())
            return 0;
        int c = 0;
        Node<T> current = head;
        while (current != null) {
            current = current.next;
            c++;
        }
        return c;
    }


    @Override
    public void printLinkedList() {
        Node<T> current = head;
        while (current != null) {
            current.printData();
            current = current.next;
        }
    }
}