//package vehiclereservationsytem;
//
//import static org.junit.Assert.*;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class testPayment {
//
//    private payment paymentProcessing;
//
//    @BeforeClass
//    public static void setUpClass() {
//        System.out.println("Testing is running for the TestPayment class");
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//        System.out.println("Testing done for the TestPayment class");
//    }
//
//    @Before
//    public void setUp() {
//        System.out.println("This object is needed before each test");
//    }
//
//    @After
//    public void tearDown() {
//        System.out.println("This is running after each test");
//    }
//
//    @Test
//    public void testProcessPayment() {
//        System.out.println("Test Process Payment Method");
//        Customer customer = new Customer("hatem","01210137949","rehab","hatemel3arby@gmail.com","hatemMohammed","123");
//
//        paymentProcessing = new payment(200,2);
//        paymentProcessing.setC(customer);
//        paymentProcessing.setCardHolderNum("1234567891111111");
//        paymentProcessing.setPaymentDate("2023-05-01");
//boolean result = paymentProcessing.processPayment();
//
//        assertTrue(result);
//        assertEquals("1234567891111111", paymentProcessing.getBilling().getCardNumber());
//    }
//}
//
//
//
////    @Test
////    public void testProcessPaymentWithIncorrectBillingInfo() {
////        System.out.println("Test Process Payment Method With Incorrect Billing Info");
////
////         
////      
////        Customer customer = new Customer("hatem","01210137949","hatem.elaraby@gmail.com","hatem","123");
////        Appointment appointment = new Appointment(customer, "rent car");
////
////        
////        boolean result = paymentProcessing.processPayment(200,2);
////        paymentProcessing.setBilling("11111111111111112","08/26",1234)
////
////       
////        assertFalse(result);
////    }
//
//    
//}
