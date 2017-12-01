import proj3_caton.Invoice;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Joseph Bermingham
 */
public class InvoiceBundle {
    ArrayList<Invoice> Bundle = new ArrayList<>();

    public String addInvoice(Invoice invoice) {
        Bundle.add(invoice);
        return "Invoice Sucessfully added";
    }

    /**
     * @param name     the name of the client see the internal statement
     * @param AsscName the name of the associate you want whose invoices you want
     * @return returns a list of all of the invoices that are of the name asscName
     * @throws NullPointerException
     */
    public ArrayList<Invoice> getInvoice(String AsscName, String name) throws NullPointerException {
        ArrayList<Invoice> retlist = new ArrayList<>();
        for (Invoice a : Bundle) {
            if (a.getOwner().equalsIgnoreCase(AsscName))
                //todo client name useability?
                try {
                    a.closeinv(name);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("you have encountered an io error in getInvoice");
                }
            retlist.add(a);
        }
        return retlist;
    }
}

