package business.services;

public interface ItemService<T> {

    void add(T item) throws Exception;

    void delete(String itemCode) throws Exception;

    void printList() throws Exception;
}
