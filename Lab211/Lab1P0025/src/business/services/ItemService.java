package business.services;

public interface ItemService<T> {

    void add(T item) throws Exception;

    void printList() throws Exception;
}
