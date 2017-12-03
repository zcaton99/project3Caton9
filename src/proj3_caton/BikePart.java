/**
 * Creates objects of all the parts that will be within warehouses
 *
 * @author Josh
 */
package proj3_caton;

public class BikePart {
    String partName;
    int partNumber;
    double price;
    double salesPrice;
    boolean onSale;
    int quantity;

    /**
     * Constructor for object of all the parts that will be within warehouses.
     *
     * @param partName   describes the part name
     * @param partNumber denotes the part number in database
     * @param price      is the actual price
     * @param salesPrice is the price sold to customer
     * @param onSale     whether or not it is on sale
     * @param quantity   amount in warehouse
     */

    public BikePart(String partName, int partNumber, double price, double salesPrice, boolean onSale, int quantity) {
        this.partName = partName;
        this.partNumber = partNumber;
        this.price = price;
        this.salesPrice = salesPrice;
        this.onSale = onSale;
        this.quantity = quantity;
    }

    public BikePart(String l) {
        String[] broken = l.split(",");
        this.partName = broken[0];
        this.partNumber = Integer.parseInt(broken[1]);
        this.price = Double.parseDouble(broken[2]);
        this.salesPrice = Double.parseDouble(broken[3]);
        this.onSale = Boolean.parseBoolean(broken[4]);
        this.quantity = Integer.parseInt(broken[5]);
    }

    public static boolean isBikePart(String l) {
        String[] broken = l.split(",");
        try {
            BikePart test = new BikePart(broken[0], Integer.parseInt(broken[1]), Double.parseDouble(broken[2]),
                    Double.parseDouble(broken[3]), Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5]));
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * returns arguments
     *
     * @return returns all values of the BikePart with spaces in the form of a string
     */
    @Override
    public String toString() {
        //return "PartName: " + partName +" "+ "PartNum: " + partNumber+" "+ "Price: " + price +" "+ "SalesPrice: " + salesPrice+" "+ "On sale? " +onSale + " Quantity: "+ quantity;
        return partName + "," + partNumber + "," + price + "," + salesPrice + "," + onSale + "," + quantity;
    }

    /**
     * returns price
     *
     * @return returns double price
     */
    public double getPrice() {
        if (onSale) {
            return salesPrice;
        } else {
            return price;
        }
    }

    public double getTruePrice() {
        return price;
    }

    /**
     * @return returns double sales price
     */
    public double getSale() {
        return salesPrice;
    }

    /**
     * @return returns String part name
     */
    public String getName() {
        return partName;
    }

    /**
     * @return returns int part number
     */
    public int getNumber() {
        return partNumber;
    }

    /**
     * @return returns int quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for double price.
     *
     * @param price
     * @return returns double price after it has been set
     */
    public double setPrice(double price) {
        this.price = price;
        return price;
    }

    /**
     * @param salesPrice
     * @return double salesPrice after it has been set.
     */
    public double setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
        return salesPrice;
    }

    /**
     * @param onSale
     * @return boolean onSale after it has been set.
     */
    public boolean setonSale(boolean onSale) {
        this.onSale = onSale;
        return onSale;
    }

    /**
     * Getter for boolean onSale.
     *
     * @return returns boolean onSale.
     */
    public boolean getonSale() {
        return onSale;
    }

    /**
     * Setter for quantity.
     *
     * @param quantity
     * @return returns int quantity after it has been set.
     */
    public int setQuantity(int quantity) {
        this.quantity = quantity;
        return quantity;
    }
}
