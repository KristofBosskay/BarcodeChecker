package barcodechecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductHandler {
    private ArrayList<Product> products;

    public ProductHandler() {
        this.products = new ArrayList<Product>();
        productStockUp();
    }
    public Product queryProduct(String barcode){
        int position = findProduct(barcode);
        if (position>=0) {
            return this.products.get(position);     
        }
        return null;
    }
    private int findProduct(String barcode){
        for (int i = 0; i < this.products.size(); i++) {
            Product prod = this.products.get(i);
            if(prod.getBarcode().equals(barcode)){
                return i;
            }
        }
        return -1;
    }
    private void productStockUp(){
        File f = new File("src\\products.csv");
        try{ 
            Scanner input = new Scanner(f,"iso-8859-2");
            while(input.hasNextLine()){
                String line = input.nextLine();
                String[] datas = line.split(";");
                String barcode = datas[0];
                String name = datas[1];
                int price = Integer.parseInt(datas[2]);
                if(findProduct(barcode)<0){
                    products.add(Product.newProduct(barcode, name, price));
                }else{
                    System.out.println(barcode+" vonalkód már szerepel, "+name+" nem került felvételre az adatbázisba.");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Hiba történt: " + e.getMessage()); 
            System.exit(1);
        }
    }
}
