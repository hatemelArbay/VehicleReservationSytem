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
// */
//import static org.junit.Assert.*;
//import org.junit.*;
//
//public class VehicleMaintenanceTest {
//	
//	private VehicleMaintenance vm;
//	
//	@BeforeClass
//	public static void setUpClass() {
//		System.out.println("Testing is running for the VehicleMaintenance class");
//	}
//	
//	@AfterClass
//	public static void tearDownClass() {
//		System.out.println("Testing done for the VehicleMaintenance class");
//	}
//	
//	@Before
//	public void setUp() {
//		vm = new VehicleMaintenance();
//		System.out.println("VehicleMaintenance object initialized before each test");
//	}
//	
//	@After
//	public void tearDown() {
//		System.out.println("This is running after each test");
//	}
//	
//		@Test
//	public void testInvalidAppointmentDate() {
//		System.out.println("Testing invalid appointment date");
//		boolean result = vm.scheduleMaintenance("user123", "ABC123", "2023-04-29", "John Doe", "john.doe@example.com", "1234567890", "1234123412341234", "05/25", "123");
//		assertFalse(result);
//	}
//	
//}