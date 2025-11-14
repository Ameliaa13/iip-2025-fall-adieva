public class MySetImpl<T> implements MySet<T> {

    private MyList<T> set;

    public MySetImpl() {
        set = new MyArrayList<>();
    }

    @Override
    public boolean put(T e) {
        for (int i = 0; i < set.size()-1; i++) {
            if (set.findByIndex(i).equals(e)) {
                System.out.println("Элемент уже есть в множестве ");
                return false;
            }
        }
        set.add(e);
        return true;
    }


    @Override
    public boolean contains(T e) {
        for (int i = 0; i < set.size()-1; i++) {
            if (set.findByIndex(i).equals(e))
                return true;
        }
        return false;
    }

    @Override
    public boolean remove(T e) {
        if (set.isEmpty()) {
            System.out.println("Множество пустое, нечего убирать ");
            return false;
        }
        set.remove(e);
        return true;
    }


    @Override
    public int size() {
        return set.size();
    }


    public void printSet() {
        if(set.isEmpty()) {
            System.out.println("MySet[]");
            return;
        }
        System.out.print("MySet[");
        for(int i = 0; i < set.size() - 1; i++) {
            System.out.print(set.findByIndex(i) + ", ");
        }
        System.out.println(set.findByIndex(set.size()-1) + "]");
    }
}