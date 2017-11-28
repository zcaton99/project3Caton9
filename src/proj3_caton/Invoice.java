package proj3_caton;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private PrintWriter invoice;
    private ArrayList<BikePart> invoiceList = new ArrayList<>();
    private boolean created = false;
    private double cost = 0.0;
    //todo add a date field, add a list of invoices ability

    public double getCost() {
        return cost;
    }
//todo add date functionality
    /**
     * @param asscName Name of the associate creating the invoice
     *                 This class manages the formatting and output of arrays.
     *                 it is sales associate specific
     * @author Joseph Bermingham
     */
    public Invoice(String asscName) {
        try {
            invoice = new PrintWriter(new FileWriter(asscName + "invoice.txt", true));//Should the append be true? that is a question i will answer later
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            invoice.println("Sales Invoice for " + asscName + "'s Van Sales, " + dateFormat.format(date));
            invoice.println("PartName   PartNumber  Price   Sales   Price    Quantity   TotalCost");
            created = true;
        } catch (IOException e) {
            System.out.println("IOException in line 30 of Invoice");
        }
    }

    /**
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
     * @param add the cost of the part you are selling.
     */
    public void addCost(double add) {
        cost += add;
    }

    /**
     * @param name The Name of the Client/Person buying the parts
     * @return Returns a File With the invoice in it.CURRENTLY TRYING TO HAVE IT RETURN THE COST AS WELL in an ArrayList of objects
     * im not sure if the For Each loop works yet.
     * @author Joseph Bermingham
     */
    public void close(String name) {
        for (BikePart h : invoiceList) {
            invoice.println(h.toString());
        }
        invoice.println("Parts Purchased by " + name + " for $" + cost + "\n");
        invoice.close();
    }
}




