package proj3_caton;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesAssociate extends Employee {
    private ArrayList<BikePart> van = new ArrayList<>();
    private Invoice thisInvoice;

    /**
     * @author Joseph Bermingham
     * This Should Sell your part by name or number
     * Should update the van, and addInv to a file that is already created with todays date
     */
    //make Sell work with files?
    String Sell(String partName, int partNumber, int quantity) throws IOException {
        double cost = 0.0;
        this.moveToList();
        BikePart soldPart = null;

        /*
         * if the part name field is empty use this version of seller to decrement the quantity and get the cost of the sale
         * */
        if (partName.equals("")) {
            for (int i = 0; i < van.size(); i++) {
                if (van.get(i).getNumber() == partNumber) {
                    int g = van.get(i).getQuantity();
                    if (g < quantity) {
                        return ("please put in a more reasonable amount you have " + g + " parts of the part " + van.get(i).getName() + " you can sell by number\n" + van.get(i).getNumber());
                    } else {
                        int a = g - quantity;
                        van.get(i).setQuantity(a);
                        cost += ((van.get(i).getPrice()) * quantity);
                        thisInvoice.addCost(cost);
                        soldPart = new BikePart(van.get(i).getName(), van.get(i).getNumber(), van.get(i).getTruePrice(), van.get(i).getSale(), van.get(i).getonSale(), quantity);
                        //invoice.setQuantity(quantity);
                        thisInvoice.addInv(soldPart);
                    }
                }
            }
        }
        /*
         * if part number is "empty" (-1), use this one
         */
        if (partNumber == -1) {
            for (int i = 0; i < van.size(); i++) {
                if (van.get(i).getName().equalsIgnoreCase(partName)) {
                    int g = van.get(i).getQuantity();
                    if (g < quantity) {
                        return ("please put in a more reasonable amount you have " + g + " of the part " + van.get(i).getName() + " can sell by name\n");
                    } else {
                        int a = g - quantity;
                        van.get(i).setQuantity(a);
                        cost += (van.get(i).getPrice() * quantity);
                        System.out.println("Cost in SA.sell: " + cost);
                        thisInvoice.addCost(cost);
                        soldPart = new BikePart(van.get(i).getName(), van.get(i).getNumber(), van.get(i).getTruePrice(), van.get(i).getSale(), van.get(i).getonSale(), quantity);
                        thisInvoice.addInv(soldPart);

                    }
                }
            }
        }
        //    System.out.println(thisInvoice.getCost() + "the invoices total cost at the end of sell");
        //  System.out.println(van.get(0).toString() + " van sub 0");
        writeToFile(van);
        return "You Sold " + quantity + " of the Part " + soldPart.getName() + "," + soldPart.getNumber() + "\n";
    }

    /**
     * @author Joseph Bermingham
     * when you enter this method it gets the text from the text field and Adds the contents of a file to addInv to this sales associate van from the main warehouse
     */

    public String LoadFile(String fileName) throws IOException {
        WarehouseManager a = new WarehouseManager();
        File loadFile = new File(fileName);
        try {
            //  System.out.println("Try has been entered");
            Scanner input = new Scanner(loadFile);
            ArrayList<BikePart> addList = new ArrayList<>();
            moveToList();
            // System.out.println(check.get(0).toString());
            //while there are parts in to be added break them up and addInv them to an arraylist of bike parts
            while (input.hasNext()) {
                String partString = input.nextLine();
                String[] broken = partString.split(",");
                BikePart d = new BikePart(broken[0],
                        Integer.parseInt(broken[1]),
                        Double.parseDouble(broken[2]), Double.parseDouble(broken[3]),
                        Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5]));

                if (a.findNumberbool(d.getNumber(), 0)) {
                    System.out.println("this is a.findnumber being true");
                    addList.add((new BikePart(broken[0],
                            Integer.parseInt(broken[1]),
                            Double.parseDouble(broken[2]), Double.parseDouble(broken[3]),
                            Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5]))));
                } else {
                 //   System.out.println("this is a.findunbmer being false");
                }

                for (BikePart in : addList) {
                    System.out.println("this is test in addpart "+in.toString());
                    boolean wasAdded = false;
                    for (BikePart wh : van) {
                        if (wh != null)
                            if (wh.getName().equalsIgnoreCase(in.getName()) && in.getNumber() == wh.getNumber()) {
                                wh.setQuantity(wh.getQuantity() + in.getQuantity());
                                wh.setPrice(in.getTruePrice());
                                wh.setonSale(in.getonSale());
                                wh.setSalesPrice(in.getSale());
                                wasAdded = true;
                            }
                    }
                    if (!wasAdded)
                        van.add(in);
                }
                writeToFile(van);
            }
        } catch (FileNotFoundException e) {
            return ("File Not Found. Please make sure you are using the correct file \nin the correct location\n");
        }

        return "File " + fileName + " Was Successfully loaded \n";
    }

    /**
     * @author Joseph Bermingham
     * This is a private utility class that clears van and then adds all of the parts in the sales van to it
     */
    private void moveToList() {
        van.clear();
        Scanner whlooker;

        try {
            whlooker = new Scanner(new File(this.getFirstName() + ".txt"));
            while (whlooker.hasNext()) {
                String partString = whlooker.nextLine();
                String[] broken = partString.split(",");
                //adds a bike part to the van
                van.add(new BikePart(broken[0],
                        Integer.parseInt(broken[1]),
                        Double.parseDouble(broken[2]),
                        Double.parseDouble(broken[3]),
                        Boolean.parseBoolean(broken[4]),
                        Integer.parseInt(broken[5])));
            }
        } catch (java.io.IOException g) {
            System.out.println("java.io.IOException in line 140 of the Sales Associate class.\n salesAssociatesName.txt not found");

        } catch (NullPointerException e) {
            System.out.println("null pointer exception in WarehouseManager line 114 \n Warehousedb.txt not found, whlooker Uninitialised");
        }
    }

    /**
     * @param db The arraylist of bikeparts you want to addInv to the van
     * @author Joseph Bermingham
     * a utility class that adds an arraylist to the users file
     */
    private void writeToFile(ArrayList<BikePart> db) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(super.getFirstName() + ".txt"));
        System.out.println("\n \n************************************************");
        for (BikePart h : db) {
            writer.println(h.toString());
            System.out.println(h.toString());
        }
        writer.close();
    }

    /*
     * this is a tester method for invoice
     */
    public void /*invoice*/ closeinvoice(String name) throws IOException {
        thisInvoice.closeinv(name);
        //return thisInvoice.toString();
    }

    /**
     * @param fName first name of sa
     * @param lName last name
     * @param uName username
     * @param Pword password
     * @param Email email
     * @author Joseph Bermingham
     * creates a sales associate and their invoice
     * pretty sure that the writer in here needs to not append
     */
    public SalesAssociate(String fName, String lName, String uType, String uName, String Pword, String Email, String pNumber) {
        super(fName, lName, uType, uName, Pword, Email, pNumber);
        thisInvoice = new Invoice(fName);
    }

    SalesAssociate(String e) {
        super(e);
        thisInvoice = new Invoice(super.getFirstName());
    }

    /**
     * Main method, for testing, replaced by SalesAsscTester
     */

    public static void main(String[] args) throws Exception {
        SalesAssociate andy = new SalesAssociate("andy", "a", "b", "c", "d", "email", "867-867-5309");
        Invoice andyInoice = new Invoice("Andy");
        andy.LoadFile("test.txt");
        andy.Sell("ZZZ_APPEARS_LAST", -1, 2);
        andy.Sell("", 1, 2);
        andyInoice.closeinv("tom");
    }
}