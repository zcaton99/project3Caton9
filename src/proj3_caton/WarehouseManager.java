package proj3_caton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Joseph Bermingham
 */
public class WarehouseManager extends Employee {
    private File warehousedb = new File("Warehousedb.txt");
    private PrintWriter writer;
    private Scanner in = null;
    private ArrayList<BikePart> warehouse=new ArrayList<>();

    public WarehouseManager(String aa, String a, String b, String c, String d, String e, String f) {
        super(aa, a, b, c, d, e, f);

    }

    /**
     * using this for testing, allows you to construct a warehouse manager from a toString
     *
     * @param lengthy the toString of an employee
     */
    WarehouseManager(String lengthy) {
        super(lengthy);

    }

    public WarehouseManager() {
        super("cooper", "cooper", "WarehouseManager", "cooper", "c", "cooper", "123");

    }

    /**
     * @param fileName The name of the file you want added to warehousedb.txt. MUST HAVE .TXT on the end
     *                 State: untested, pulls file that you want to addInv, but DOES NOT addInv that to the warehouse
     * @author Joseph Bermingham
     */
    public void addfile(String fileName) {
        try {
            in = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception in addFile \n the file you are trying to addInv does not exist or can not be found make sure you addInv .txt at the end");
        }

        ArrayList<BikePart> addList = new ArrayList<>();
        while (in.hasNext()) {
            String partString = in.nextLine();
            String[] broken = partString.split(",");
            //adds a bike part to the addList
            addList.add((new BikePart(broken[0],
                    Integer.parseInt(broken[1]),
                    Double.parseDouble(broken[2]), Double.parseDouble(broken[3]),
                    Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5]))));
            //  System.out.println(partString + "This is tester output in Warehouse Manager addFile Method");
        }
            /*
             * The idea here is to take in the array list of parts to be added, addInv them to the arraylist of things already there combining the ones that are the same and adding the new ones,
             * and updating the information as needed
             */
        moveToList();
        BikePart addpart = null;
        //outer loop is what is in the warehouse, inner loop is what is being added
       // System.out.println(warehouse.isEmpty() + "This is warehosue.isempty");
        if (!warehouse.isEmpty()) {
            for (BikePart wh : warehouse) {
                boolean wasAdded = false;
                for (BikePart ad : addList) {
                    //This is what compares the parts that i have to the parts that i am adding. if it is not added wasAdded remains false and it is added at the end as a new item
                    if (wh.getNumber() == ad.getNumber() && wh.getName().equalsIgnoreCase(ad.partName)) {
                        wh.setQuantity(wh.getQuantity() + ad.getQuantity());
                        wh.setPrice(ad.getTruePrice());
                        wh.setonSale(ad.getonSale());
                        wh.setSalesPrice(ad.getSale());
                        wasAdded = true;
                     //   System.out.println(wasAdded + " this is wasAdded");
                    }
                    addpart = ad;
                }
                if (!wasAdded) {
                    warehouse.add(addpart);//could theoretically be null
                  //  System.out.println("this is adding things to the warehouse");
                }
            }
        } else {
            for (BikePart ad : addList) {
                warehouse.add(ad);
            }
        }
        try {
            writer = new PrintWriter(new FileWriter(warehousedb, false));//Might need to be false
        } catch (java.io.IOException g) {
            System.out.println("IOException on line 28 of warehouse manager\n warehouseDB not found");
        }
        for (BikePart i : warehouse) {//This is were everything is added back into the print writer. it might have to not append here.
            writer.println(i);
            //System.out.println(i.toString() + "This is the part i am adding");
        }
      //  System.out.println("if you are getting copies of the warehouse look at setting the writer \n in warehouse manager to append = false");
        writer.close();
    }


    /**
     * Finds a part in the warehouse by name
     *
     * @param name The name of the part you are looking for
     */
    public String findName(String name) {
        moveToList();
        String part = "Part " + name + " not Found";
        for (BikePart b : warehouse) {
            if (b.getName().equalsIgnoreCase(name)) {
                System.out.println(b.toString());
                part = b.toString();
            }
        }
        return part;
    }

    /**
     * finds a part in the warehouse by name
     *
     * @param number the id of the part you are looking for
     */
    public String findNumber(int number) {
        moveToList();
        String part = "Part with id " + number + " was not Found";
        for (BikePart b : warehouse) {
            if (b.getNumber() == number) {
                System.out.println(b.toString());
                part = b.toString();
            }
        }
        return part;
    }

    public boolean findNumberbool(int number,int quant) {
      moveToList();
      boolean part = false;
       for (BikePart b : warehouse) {
            if (b.getNumber() == number&&b.getQuantity()>=quant) {
                System.out.println(b.toString());
                part = true;
                //b.quantity=(b.quantity-quant);
            }
        }
        return part;
    }
    /**
     * changes field that is An array list containing all of the parts in the warehouse
     *
     * @author Joseph Bermingham
     * moveToList is a private utility method that adds all of the parts in the warehouse to an arraylist and returns it
     */
    private void moveToList() {
        ArrayList<BikePart> retList = new ArrayList<>();
        warehouse.clear();
        Scanner whlooker = null;
        try {
            whlooker = new Scanner(new File("Warehousedb.txt"));
        } catch (java.io.IOException g) {
            System.out.println("java.io.IOException in move to list in Warehouse Manager.\n Warehousedb.txt not found");
        }
        try {
            while (whlooker.hasNext()) {
                String partString = whlooker.nextLine();

                // System.out.println(t + "       t");
                String[] broken = partString.split(",");
                //adds a bike part to the addList
                warehouse.add(new BikePart(broken[0], Integer.parseInt(broken[1]), Double.parseDouble(broken[2]), Double.parseDouble(broken[3]), Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5])));
             //   System.out.println(partString + "This is tester in Warehouse Manager LoadFile Method");
            }
        } catch (NullPointerException e) {
            System.out.println("null pointer exception in WarehouseManager line 114 \n Warehousedb.txt not found, whlooker Uninitialised");
        }

    }
}


