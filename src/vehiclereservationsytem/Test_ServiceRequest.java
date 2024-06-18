///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package vehiclereservationsytem;
//
///**
// *
// * @author hatem
// */import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class Test_ServiceRequest {
//
//    private ServiceRequest serviceRequest;
//
//    @BeforeClass
//    public static void setUpClass() {
//        System.out.println("Testing is Running for the class");
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//        System.out.println("Testing Done for the class");
//    }
//
//    @Before
//    public void setUp() {
//        serviceRequest = new ServiceRequest();
//        System.out.println("This object needed before each test");
//    }
//
//    @After
//    public void tearDown() {
//        System.out.println("This is running after each test");
//    }
//
//    @Test
//    public void testMakeServiceRequest() {
//        System.out.println("Test Make Service Request Method");
//
//        // Preconditions
//        Customer customer = new Customer("John", "Doe", "johndoe@example.com", "password");
//        Vehicle vehicle = new Vehicle("ABC123", "Sedan", "Honda", "Civic", 2018, "Red", 50000);
//        customer.addVehicle(vehicle);
//
//        // Main Path
//        boolean result = serviceRequest.makeServiceRequest(customer, ServiceType.CHECKUP, vehicle, "2023-05-05", "10:00");
//        assertTrue(result);
//
//        // Postconditions
//        assertTrue(serviceRequest.getRequests().size() == 1);
//        ServiceRequest.Request request = serviceRequest.getRequests().get(0);
//        assertEquals(request.getCustomer(), customer);
//        assertEquals(request.getVehicle(), vehicle);
//        assertEquals(request.getType(), ServiceType.CHECKUP);
//        assertEquals(request.getDate(), "2023-05-05");
//        assertEquals(request.getTime(), "10:00");
//    }
//
//    @Test
//    public void testMakeServiceRequestWithoutVehicle() {
//        System.out.println("Test Make Service Request Method Without Vehicle");
//
//        // Preconditions
//        Customer customer = new Customer("John", "Doe", "johndoe@example.com", "password");
//
//        // Alternative Path
//        boolean result = serviceRequest.makeServiceRequest(customer, ServiceType.CHECKUP, null, "2023-05-05", "10:00");
//        assertFalse(result);
//    }
//
//    @Test
//    public void testMakeServiceRequestWithInvalidDate() {
//        System.out.println("Test Make Service Request Method With Invalid Date");
//
//        // Preconditions
//        Customer customer = new Customer("John", "Doe", "johndoe@example.com", "password");
//        Vehicle vehicle = new Vehicle("ABC123", "Sedan", "Honda", "Civic", 2018, "Red", 50000);
//        customer.addVehicle(vehicle);
//
//        // Alternative Path
//        boolean result = serviceRequest.makeServiceRequest(customer, ServiceType.CHECKUP, vehicle, "2023-05-35", "10:00");
//        assertFalse(result);
//    }
//
//    @Test
//    public void testMakeServiceRequestCancel() {
//        System.out.println("Test Make Service Request Method Cancel");
//
//        // Preconditions
//        Customer customer = new Customer("John", "Doe", "johndoe@example.com", "password");
//        Vehicle vehicle = new Vehicle("ABC123", "Sedan", "Honda", "Civic", 2018, "Red", 50000);
//        customer.addVehicle(vehicle);
//
//        // Main Path
//        boolean result = serviceRequest.makeServiceRequest(customer, ServiceType.CHECKUP, vehicle, "2023-05-05", "10:00");
//        assertTrue(result);
//
//        // Alternative Path
//        boolean cancelResult = serviceRequest