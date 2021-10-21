package com.jawad.mthree.vendingmachine.model;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;

import java.io.*;
import java.util.*;

public class VendingMachineDaoImpl implements VendingMachineDao {

    private List<Product> products = new ArrayList<>();
    public final String ROSTER_FILE;
    public static final String DELIMITER = "::";

    public VendingMachineDaoImpl() {
        ROSTER_FILE= "collection.txt";
    }

    @Override
    public List<Product> getProducts() throws VendingMachinePersistenceException {
        loadFromFile();
        return products;
    }

    @Override
    public void decrementStock(Product product) throws VendingMachinePersistenceException {
        product.setQuantity(product.getQuantity()-1);
        writeToFile();
    }

    @Override
    public void removeProduct(Product product) throws VendingMachinePersistenceException {
        products.remove(product);
        writeToFile();
    }

    @Override
    public void addProduct(Product product) throws VendingMachinePersistenceException {
        products.add(product);
        writeToFile();
    }

    private Product unmarshallProduct(String inputLine) {
        // convert line from file into a product object
        String[] partitions = inputLine.split(DELIMITER);

        String name = partitions[0];
        double price = Double.parseDouble(partitions[1]);
        int quantity = Integer.parseInt(partitions[2]);

        Product newProduct = new Product(name, price, quantity);
        return newProduct;
    }

    private String marshallProduct(Product product) {
        // convert product object into line to write to file
        String line = "";
        line += product.getName() + DELIMITER;
        line += product.getPrice() + DELIMITER;
        line += product.getQuantity();

        return line;
    }

    private void loadFromFile() throws VendingMachinePersistenceException {
        System.out.println("Loading from file");
        List<Product> newList = new ArrayList<>();
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load Product Collection into memory.", e);
        }

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Product newProduct = unmarshallProduct(line);

            newList.add(newProduct);
        }
        products = newList;
        scanner.close();
    }

    private void writeToFile() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE), false);
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save Product data.", e);
        }

        List<Product> products = getProducts();

        for(Product p : products) {

            String line = marshallProduct(p);

            out.println(line);

            out.flush();
        }
        out.close();
    }

}
