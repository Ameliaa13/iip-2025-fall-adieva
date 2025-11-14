class Node<T> {

    private T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void printData() {
        System.out.println(data);
    }
    public T getData() {
        return data;
    }

}