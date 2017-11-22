package proj3_caton;

/**
 * This is a set of test cases to test reading in users as their actual type
 */
//todo add test cases for your stuff either in this class or in a different one
public class TestCases {
    private static SysAdmin bob = new SysAdmin();

    public static void main(String[] args) {
        //expected(userAddTest());
        expected(TestWarehouseMFromlist());
    }

    /**
     * This is used to test genlist and the sysadmin ability to add users and recall them
     *
     * @author Joseph Bermingham
     */
    private static int userAddTest() {
        bob.addUser("angel", "a", "SalesAssociate", "a", "a", "a", "a");
        bob.addUser("AnotherOne", "AnotherTwo", "OfficeManager", "g", "g", "g", "g");
        bob.addUser("i", "Think", "WarehoueManager", "its", "repeating", "dd", "d");
        bob.addUser("julio", "a", "SalesAssociate", "a", "a", "a", "3");
        return 0;
    }

    /*
     * @author Joseph Bermingham
     * This is testing to make sure that we can come back and add a user later and not have issues
     * it doesnt, this test is no longer used
     */
//    private static void addanotherUserTest() {
//        bob.addUser("SecondAdd", "SecondAddln", "OfficeManager", "bbbb", "dd", "ss", "sss");
//    }

    /**
     * @author Joseph Bermingham
     * This is going to be used to test warehoue Managers from the gernerated list
     * currently can add parts correctly
     * have not tested searching for parts
     */
    private static int TestWarehouseMFromlist() {
        Main.genList();
        WarehouseManager testBro = new WarehouseManager(Main.empList.get(3).toString());
        testBro.addfile("InitialInv.txt");
        //todo test for part searching
        return 1;
    }

    /**
     * Tests Sales Associates
     */
    private static int testSA() {
//todo test all of sales associate

        return 2;
    }

    /**
     * @author Joseph Bermingham
     * This is the output of the expected values vs the actual values
     */
    private static void expected(int whichOutPut) {
        if (whichOutPut == 0) {
            Main.genList();
            System.out.println("expected:\nangel,a,SalesAssociate,a,a,a,a\nAnotherOne,anothertwo,OfficeManager,b,b,b,b\n" +
                    "i,Think,WarehouseManager,c,c,c,c\njulio,a,SalesAssociate\n SecondAdd,SecondaddLN,OfficeManager");
            System.out.println("Actual");


            for (int i = 0; i < Main.empList.size(); i++) {
                System.out.println(Main.empList.get(i).toString());
            }
        } else if (whichOutPut == 1) {
            //for a different type of actual vs expected
            System.out.println("The expected value is all of initalinv.txt, should be checked manually");
        }
    }
}
