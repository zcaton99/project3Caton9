package proj3_caton;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * This is a set of test cases to test reading in users as their actual type
 */
public class TestCases {
    public static void main(String[] args) {
        System.out.println("starting a set of test cases");
        PrintWriter writer = null;
        SysAdmin bob = new SysAdmin();
        bob.addUser("angel","a","SalesAssociate","a","a","a","a");
        bob.addUser("AnotherOne","AnotherTwo","OfficeManager","g","g","g","g");
        bob.addUser("i","Think","WarehoueManager","its","repeating","dd","d");

        try {
            writer = new PrintWriter(new FileWriter("users.txt", true));
        } catch (Exception e) {
            System.out.println("Exception in the tester");
            e.printStackTrace();
        }
//        writer.println("a,a,SalesAssociate,a,a,a,a");
//        writer.println("b,b,OfficeManager,b,b,b,b");
//        writer.println("c,c,WarehouseManager,c,c,c,c");
//        writer.close();

        Main.genList();
        System.out.println("expected:\na,a,SalesAssociate,a,a,a,a\nb,b,OfficeManager,b,b,b,b\nc,c,WarehouseManager,c,c,c,c");
        System.out.println("Actual");


        for (int i = 0; i < Main.empList.size(); i++) {
            System.out.println(Main.empList.get(i).toStringTest());
        }
//        (SalesAssociate)Main.empList.get(1).Sell();
    }
}
