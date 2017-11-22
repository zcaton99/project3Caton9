/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj3_caton;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zachcaton
 */
public class SysAdminTest {
    
    public SysAdminTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    SysAdmin instance = new SysAdmin();
    

    /**
     * Test of addUser method, of class SysAdmin.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String fName = "zach";
        String lName = "caton";
        String userType = "manager";
        String userName = "catonzach";
        String password = "password";
        String email = "zach@mail.com";
        String pNumber = "123-123-1234";
        //SysAdmin instance = new SysAdmin();
        instance.addUser(fName, lName, userType, userName, password, email, pNumber);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of findByName method, of class SysAdmin.
     *  This should return null cause searching for name that doesn't exist
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "zach";
        //SysAdmin instance = new SysAdmin();
        Employee expResult = null;
        Employee result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    

    /**
     * Test of resetPassword method, of class SysAdmin.
     * @throws java.lang.Exception
     */
    @Test
    public void testResetPassword() throws Exception {
        System.out.println("resetPassword");
        String name = "";
        String newPass = "";
        //SysAdmin instance = new SysAdmin();
        instance.resetPassword(name, newPass);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    /**
     * Test of deleteUser method, of class SysAdmin.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String username = "catonzach";
        //SysAdmin instance = new SysAdmin();
        instance.deleteUser(username);
        // TODO review the generated test code and remove the default call to fail.
    }
}
