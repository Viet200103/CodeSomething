package application.ui;

import application.utilities.DataIOHelper;
import business.entities.Product;
import business.services.ItemService;
import business.services.ProductService;
import business.utilities.DateUtils;
import business.utilities.DataValidation;
import business.utilities.ProductType;
import data.managers.FileManager;
import data.managers.IFileManager;
import data.repositories.ProductRepository;

public class ProductManagement {

    private final IFileManager fileManager = new FileManager(FileManager.PRODUCT_FILE_NAME);

    private final DataIOHelper inputHelper = DataIOHelper.getInstance();

    private final ItemService<Product> itemService = new ProductService(
            ProductRepository.getInstance(fileManager)
    );

    public void displayMenu() {

        try {
            int choice;
            boolean isRunning = true;

            do {
                System.out.println("******Employee Management******");
                Menu.print(
                        "1. Add a product" + "|" +
                                "2. Update a product information" + "|" +
                                "3. Delete product" + "|" +
                                "4. Show all product" + "|" +
                                "5. Stop"
                );
                choice = inputHelper.getIntegerNumber();
                System.out.println("--------------------------------");

                switch (choice) {
                    case 1 -> processWithUserLoop(this::addNewProduct);
                    case 2 -> updateProduct();
                    case 3 -> deleteProduct();
                    case 4 -> {
                        DataIOHelper.printlnMessage(">>>>>> Product List >>>>>>");
                        itemService.printList();
                        DataIOHelper.printlnMessage("--------------------------------");
                    }
                    case 5 -> isRunning = false;
                    default -> Menu.printRequireNotFound();
                }

            } while (isRunning);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Product getProduct() {
        String code = getProductCode();

        ProductType type = getProductType(false);

        String name = getProductName();
        String maker = getProductMaker();
        String manufacturingDate = getManufacturingDate(false);
        String expirationDate = getExpirationDate(manufacturingDate);

        int quantity = getProductQuantity(false);

        return new Product(code, type, name, maker, manufacturingDate, expirationDate, quantity);
    }

    private String getProductName() {
        return inputHelper.getStringWithMessage("Enter Product name: ");
    }

    private String getProductMaker() {
        return inputHelper.getStringWithMessage("Enter Product maker: ");
    }

    private String getProductCode() {
        String code;
        do {
            try {
                code = inputHelper.getStringWithMessage("Enter Product code:");

                DataValidation.requireNotNullEmpty(code, "Product code is not validated");
                break;
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);
        return code;
    }

    private ProductType getProductType(boolean acceptEmpty) {
        do {
            try {
                int typeChoice;
                DataIOHelper.printlnMessage("Enter Product type: ");
                Menu.print(
                        "1. Daily usage" + "|" +
                        "2. Long life usage"
                );

                if (acceptEmpty) {
                    String sType = inputHelper.getString();
                    if (sType.isBlank()) {
                        return ProductType.EMPTY;
                    }

                    try {
                        typeChoice = Integer.parseInt(sType);
                    }catch (Exception e) {
                        throw new IllegalArgumentException("The product type is not valid");
                    }

                } else {
                    typeChoice = inputHelper.getIntegerNumber();
                }

                switch (typeChoice) {
                    case 1 -> {
                        return ProductType.DAILY;
                    }
                    case 2 -> {
                        return ProductType.LONG_LIFE;
                    }
                    default -> throw new IllegalArgumentException("The product type is not valid");
                }
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);
    }

    private String getManufacturingDate(boolean acceptEmpty) {
        return getProductDate(acceptEmpty, "Enter Product manufacturing date");
    }

    private String getExpirationDate(String manufacturingDate) {
        String expirationDate;

        do {
            try {
                expirationDate = getProductDate(true, "Enter Product expiration date");
                DateUtils.validateExpirationDate(manufacturingDate, expirationDate);
                break;
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);


        return expirationDate;
    }

    private String getProductDate(boolean acceptEmpty, String msg) {
        String date;

        do {
            try {
                DataIOHelper.printlnMessage(msg);
                DataIOHelper.displayDateExample();
                DataIOHelper.printMessage("Date: ");

                date = inputHelper.getString();

                if (date.isBlank() && acceptEmpty) return date;

                DateUtils.checkFormatDate(date);

                break;
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);

        return date;
    }

    private int getProductQuantity(boolean acceptEmpty) {
        int quantity;
        do {
            try {
                String str = inputHelper.getStringWithMessage("Enter Product quantity: ");

                if (str.isBlank() && acceptEmpty) {
                    return -1;
                }

                DataValidation.requirePositiveInteger(str, "Quantity of product must be positive integer");
                quantity = Integer.parseInt(str);
                break;
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);
        return quantity;
    }

    private void addNewProduct() {
        try {
            Product newEmployee = getProduct();
            itemService.add(newEmployee);
            System.out.println(">>Product added successfully.");
        } catch (Exception e) {
            System.out.println(">>" + e.getMessage());
        }
    }

    private void deleteProduct() {
        String code = inputHelper.getStringWithMessage("Enter Product code: ");

        DataIOHelper.printlnMessage("Data of product will be delete and can not restore");
        DataIOHelper.printMessage("Do you want to continue(Y?N)? ");

        String action = inputHelper.getString();

        if (action.matches("[\\s{y, Y}]")) {
            itemService.delete(code);
        }
        DataIOHelper.printlnMessage("--------------------------------");
    }

    private void updateProduct() {
        String code = inputHelper.getStringWithMessage("Enter product code: ");

        try {
            Product oldProduct = itemService.productExist(code);
            DataIOHelper.printlnNotification(oldProduct.toString());

            ProductType productType = getProductType(true);
            if (productType == ProductType.EMPTY) {
                productType = oldProduct.getType();
            }

            String newName = ifBlank(getProductName(), oldProduct.getName());
            String maker = ifBlank(getProductMaker(), oldProduct.getMaker());

            String manufacturingDate = ifBlank(
                    getManufacturingDate(true),
                    oldProduct.getManufacturingDate()
            );

            String expirationDate = getExpirationUpdate(
                    oldProduct.getExpirationDate(), manufacturingDate
            );

            int quantity = getProductQuantity(true);

            if (quantity == -1) {
                quantity = oldProduct.getQuantity();
            }

            Product newProduct = new Product(
                    oldProduct.getCode(),
                    productType,
                    newName,
                    maker,
                    manufacturingDate,
                    expirationDate,
                    quantity
            );

            itemService.update(newProduct);
        } catch (Exception e) {
            DataIOHelper.printlnNotification(e.getMessage());
        }

        DataIOHelper.printlnMessage("--------------------------------");
    }

    private String getExpirationUpdate(String oldDate, String manufacturingDate) {
        String expirationDate;

        do {
            try {
                expirationDate = ifBlank(
                        getProductDate(true, "Enter Product expiration date"),
                        oldDate
                );
                DateUtils.validateExpirationDate(manufacturingDate, expirationDate);
                break;
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);

        return expirationDate;
    }

    private String ifBlank(String expect, String replace) {
        if (expect.isBlank()) {
            return replace;
        }
        return expect;
    }

    private void processWithUserLoop(Runnable action) {
        boolean isContinue;
        do {
            action.run();
            DataIOHelper.printlnMessage("--------------------------------");
            DataIOHelper.printMessage("Do you want to continue(Y/N)? ");
            isContinue = inputHelper.getString().matches("[\\s{y, Y}]");
            DataIOHelper.printlnMessage("--------------------------------");
        } while (isContinue);
    }
}
