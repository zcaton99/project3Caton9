package proj3_caton;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Joseph Bermingham
 */
public class Invoice {
    private static PrintWriter invoice;
    private ArrayList<BikePart> invoiceList = new ArrayList<>();
    private boolean created = false;
    private double cost = 0.0;
    private String owner;
    Date date = new Date();

    //todo add a date field, add a list of invoices ability

    public double getCost() {
        return cost;
    }

    /**
     * @param asscName Name of the associate creating the invoice
     *                 This class manages the formatting and output of arrays.
     *                 it is sales associate specific
     * @author Joseph Bermingham
     */
    public Invoice(String asscName) {
        File check;
        owner = asscName;
        check = new File(asscName + "invoice.txt");
        if (!check.exists()) {
            try {
                invoice = new PrintWriter(new FileWriter(asscName + "invoice.txt", true));//Should the append be true? that is a question i will answer later
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                invoice.println("Sales Invoice for " + asscName + "'s Van Sales, " + dateFormat.format(date));
                invoice.println("PartName   PartNumber  Price   Sales   Price    Quantity   TotalCost");
                created = true;
            } catch (IOException e) {
                System.out.println("IOException in InvoiceCreation");
            }
        } else {
            created = true;
        }
    }


    /**
     * operation that happens after when you sell things to add their cost and information to the array
     *
     * @param Part The part that you want to addInv to your invoice
     * @author Joseph Bermingham
     */
    void addInv(BikePart Part) {
        // System.out.println(Part.getQuantity() + " the incoming parts quantity");
        if (created) {
            boolean found = false;
            boolean noRep = true;
            for (int i = 0; i < invoiceList.size(); i++) {
                if (invoiceList.get(i).getNumber() == Part.getNumber() && noRep) {
                    BikePart a = invoiceList.get(i);
                    a.setQuantity(invoiceList.get(i).getQuantity() + Part.getQuantity());

                    found = true;
                    noRep = false;
                    // System.out.println("the part was added to the quantity of another");
                }
            }
            if (!found) {
                invoiceList.add(Part);

            }
            //  System.out.println(invoiceList.get(0).toString() + " This is the invoice array sub zero toString after the addInv method");

        } else {
            System.out.println("No invoice has been started for this user. Please Contact the Coders who messed this up");
        }

    }

    /**
     * adds the cost of the sold part to cost
     *
     * @param add the cost of the part you are selling.
     */
    public void addCost(double add) {
        cost += add;
    }

    /**
     * @author Joseph Bermingham
     * is used in the close method to do the adding properly
     */
    private void begin() throws IOException {
        try {

            Scanner parse = new Scanner(new File(owner + "invoice.txt"));
            if (parse.hasNext()) {
                System.out.println("This is owner in Invoice: " + owner + "\n");
                System.out.println(parse.nextLine());
                System.out.println(parse.nextLine());
                String hold = parse.nextLine();
                System.out.println("This is hold in Invoice.begin: "+hold);
                invoiceList.add(new BikePart(hold));
            }
            invoice = new PrintWriter(new FileWriter(owner + "invoice.txt"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            invoice.println("Sales Invoice for " + owner + "'s Van Sales, " + dateFormat.format(date));
            invoice.println("PartName   PartNumber  Price   Sales   Price    Quantity   TotalCost");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name The Name of the Client/Person buying the parts
     * @return Returns a File With the invoice in it.CURRENTLY TRYING TO HAVE IT RETURN THE COST AS WELL in an ArrayList of objects
     * im not sure if the For Each loop works yet.
     * @author Joseph Bermingham
     */
    public void closeinv(String name) throws IOException {
        //  StringBuffer retstring;
        begin();
        for (BikePart h : invoiceList) {
            invoice.println(h.toString());
            System.out.println("The part being added to the invoice: " + h.toString());
            // retstring=new StringBuffer("The part being added to the invoice: " + h.toString());
        }
        //retstring.append("Parts Purchased by " + name + " for $" + cost + "\n");
        invoice.println("Parts Purchased by bob for $" + cost + "\n");
        invoice.close();
        // return retstring;
    }

    public String getOwner() {
        return owner;
    }
}




