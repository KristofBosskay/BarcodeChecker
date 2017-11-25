package barcodechecker;

import java.util.Scanner;

public class BarCodeChecker {
    
    private static Scanner scanner= new Scanner(System.in);
    private static ProductHandler ph = new ProductHandler();
    
    public static void main(String[] args) {
               boolean quit=false;
        while(!quit){
            System.out.println("Adja meg a vonalkódot:");
            String barcode  = scanner.nextLine();
            switch(barcode){
                case "kilepes":
                    System.out.println("\nA program sikeresen leállt");
                    quit=true;
                    break;
                default:
                    productQuery(barcode);
                    delay();
                    break;
            }
        }
    }
    public static void productQuery(String barcode){
        if(ph.queryProduct(barcode)==null){
            System.out.println("Vonalkód nem található\n");
            return;
        }    
        Product p = ph.queryProduct(barcode);
        System.out.println("Terméknév: "+p.getName()+" - Ár: "+p.getPrice()+" Ft\n");
        
    }
    private static void delay(){
        try {
            Thread.sleep(6000);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }   
}
