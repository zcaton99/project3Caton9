package proj3_caton;

import java.util.ArrayList;

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
    public String examineButtonMethodQuant(int quant, ArrayList<BikePart> bpDS) {
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
}
