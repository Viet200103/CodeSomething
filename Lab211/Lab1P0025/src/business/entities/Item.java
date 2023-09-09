package business.entities;

abstract class Item {
    private String code;


    private Item() {

    }

    public Item(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
