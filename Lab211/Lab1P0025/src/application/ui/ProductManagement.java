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

import java.text.ParseException;

public class ProductManagement {

    private IFileManager fileManager = new FileManager(FileManager.PRODUCT_FILE_NAME);

    private DataIOHelper inputHelper = DataIOHelper.getInstance();

    private ItemService<Product> itemService = new ProductService(
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
                    case 1 -> {
                        addNewEmployee();
                    }
                    case 2 -> {

                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }
                    case 5 -> {
                        isRunning = false;
                    }
                    default -> {
                        Menu.printRequireNotFound();
                    }
                }

            } while (isRunning);

        } catch (Exception e) {

        }
    }

    public Product getProduct() {
        String code = getProductCode();

        ProductType type = getProductType();

        String name = inputHelper.getStringWithMessage("Enter Product name: ");
        String maker = inputHelper.getStringWithMessage("Enter Product maker: ");
        String manufacturingDate = getManufacturingDate();
        String expirationDate = getExpirationDate(manufacturingDate);

        int quantity = getProductQuantity();

        return new Product(code, type, name, maker, manufacturingDate, expirationDate, quantity);
    }

    private String getProductCode() {
        String code;
        do {
            try {
                code = inputHelper.getStringWithMessage("Enter Product code:");

                DataValidation.requireNotNullEmpty(code, "Product code is not validated");
                break;
            } catch (Exception e) {
                DataIOHelper.printlnMessage(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);
        return code;
    }

    private ProductType getProductType() {
        do {
            try {
                int typeChoice;
                DataIOHelper.printlnMessage("Enter Product type: ");
                Menu.print(
                        "1. Daily usage" + "|" +
                        "2. Long life usage"
                );

                typeChoice = inputHelper.getIntegerNumber();

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
                DataIOHelper.printlnMessage(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);
    }

    private String getManufacturingDate() {
        return getProductDate("Enter Product manufacturing date");
    }

    private String getExpirationDate(String manufacturingDate) {
        String expirationDate;

        do {
            try {
                expirationDate = getProductDate("Enter Product expiration date");
                DateUtils.validateExpirationDate(manufacturingDate, expirationDate);
                break;
            } catch (Exception e) {
                DataIOHelper.printlnMessage(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);


        return expirationDate;
    }

    private String getProductDate(String msg) {
        String date;

        do {
            try {
                DataIOHelper.printlnMessage(msg);
                DataIOHelper.displayDateExample();
                DataIOHelper.printMessage("Date: ");

                date = inputHelper.getString();

                DateUtils.checkFormatDate(date);

                break;
            } catch (Exception e) {
                DataIOHelper.printlnMessage(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);

        return date;
    }

    private int getProductQuantity() {
        int quantity;
        do {
            try {
                quantity = inputHelper.getIntegerNumberWithMessage("Enter Product quantity: ");

                DataValidation.requirePositiveInteger(quantity, "Quantity of product must be positive integer");
                break;
            } catch (Exception e) {
                DataIOHelper.printlnMessage(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);

        return quantity;
    }

    private void addNewEmployee() {
        boolean isContinue;
        do {
            try {
                Product newEmployee = getProduct();
                itemService.add(newEmployee);
                System.out.println(">>Product added successfully.");
            } catch (Exception e) {
                System.out.println(">>" + e.getMessage());
            }

            DataIOHelper.printlnMessage("--------------------------------");
            DataIOHelper.printMessage("Do you want to continues(Y/N)? ");
            isContinue = inputHelper.getString().matches("[\\s{y, Y}]");
            DataIOHelper.printlnMessage("--------------------------------");

        } while (isContinue);
    }
}
