package proj3_caton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class officeManager extends Employee {
    private static ArrayList<BikePart> bpDS = new ArrayList<>();
    static ArrayList<BikePart> bpDS2 = new ArrayList<>();
    public officeManager(String a, String b, String bb, String c, String d, String e, String f) {
        super(a, b, bb, c, d, e, f);
    }

    /**
     * findByName compares all BikeParts in the warehouse data set and returns a BikePart if its name is equal to the name given.
     * @param name
     * @return returns BikePart b
     */
    private static BikePart findByName(String name){
        for (BikePart b : bpDS)
            if (b.getName().equals(name))
                return b;
        return null;
    }
    /**
     * this method goes through the bike part warehouse and looks for parts with less than a hardcoded quantity (10)
     * @param bpDS
     * //todo addInv the ability to create a file of the needed parts
     */
    public String checkQuant(ArrayList<BikePart> bpDS) {
        for (BikePart bp : bpDS){
            if (bp.getQuantity() <= 10) {
                bpDS2.add(bp);
            }
        }
        return bpDS2.toString();
    }
    // todo add methods for by partname and quantity
    public String examineButtonMethodname(String partname, ArrayList<BikePart> bpDS) {
        for (BikePart bp : bpDS)
            if (bp.getName().equals(partname)) {
                if (bp.getonSale())
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getSale() + "," + " Quantity: " + bp.getQuantity() + "\n");
                else
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getPrice() + "," + " Quantity: " + bp.getQuantity() + "\n");
            }
        return("");
    }
    public String examineButtonMethodNum(int num, ArrayList<BikePart> bpDS) {
        for (BikePart bp : bpDS)
            if (bp.getNumber()==num) {
                if (bp.getonSale())
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getSale() + "," + " Quantity: " + bp.getQuantity() + "\n");
                else
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getPrice() + "," + " Quantity: " + bp.getQuantity() + "\n");
            }
        return("");
    }
    public String examineButtonMethodQuant(int quant, ArrayList<BikePart> bpDS) { //TODO: add functionality so that greater than, less than or equal to quantity is optional
        for (BikePart bp : bpDS)
            if (bp.getQuantity()==quant) {
                if (bp.getonSale())
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getSale() + "," + " Quantity: " + bp.getQuantity() + "\n");
                else
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getPrice() + "," + " Quantity: " + bp.getQuantity() + "\n");
            }
        return("");
    }
    
    public void orderParts(BikePart bp, int count){
        File f = new File("warehousedb.txt");
        int orderpart = bp.getNumber();
                    try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                        ArrayList<BikePart> bpArray2 = new ArrayList<>();
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] entries = line.split(",");
                            int result = Integer.parseInt(entries[1]);
                            double result2 = Double.parseDouble(entries[2]);
                            double result3 = Double.parseDouble(entries[3]);
                            Boolean result4 = Boolean.valueOf(entries[4]);
                            int result5 = Integer.parseInt(entries[5]);
                        
                            if(orderpart==result){
                                result5 = result5 + count;
                                System.out.println("Bought "+ count + " " +entries[0]);                              
                            } 
                            BikePart bpAdd = new BikePart(entries[0],result,result2,result3,result4,result5);
                            bpArray2.add(bpAdd);  //adding back the parts to the array after they have been altered.   
                    }
                    PrintWriter writer = new PrintWriter(f, "UTF-8");
                    for (BikePart bpAlternate : bpArray2){                       
                        writer.println(bpAlternate.toString().replace(" ", ",")); 
                    }
                    writer.close();    
                } 
                catch(FileNotFoundException fnfe){
                    System.out.println(fnfe.getMessage());       
                } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
}
