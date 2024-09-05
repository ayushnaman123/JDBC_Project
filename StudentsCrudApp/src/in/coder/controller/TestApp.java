package in.coder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import in.coder.service.IStudentService;
import in.coder.servicefactory.StudentServiceFactory;
import in.ineuron.dto.Student;
/*
 * @author Ayush Naman
 * 
 *  */
public class TestApp {
    static {
    	System.out.println("<<<<<<<<<<<<<<<<<Welcome to Student Crud App>>>>>>>>>>>>>>>");
    	System.out.println("============================================================");
    	System.out.println("-------------------------------------------------------------");
    	System.out.println();
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println();
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. Exit");
			System.out.print("ENTER YOUR CHOICE, PRESS[1,2,3,4,5]");
			String choice = br.readLine();
			
			switch(choice) {
			case "1":insertOperation();
			break;
			case "2":selectOperation();
			break;
			case "3":updateOperation();
			break;
			case "4":deleteOperation();
			break;
			case "5":System.out.println(":::::::::::THANKS FOR USING APPLICATION:::::::::::");
			System.exit(0);
			default:System.out.println("INVALID OPTION PLEASE TRY AGAIN WITH VALID OPTION");
			}
		}

	}

	private static void insertOperation() throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the student name: ");
		String sname = br.readLine();

		System.out.print("Enter the student age: ");
		String sage = br.readLine();

		System.out.print("Enter the student address: ");
		String saddress = br.readLine();
		
		System.out.print("Enter the student dob(dd-MM-yyyy)");
		String sdob = br.readLine();
		
		
		
		String msg = studentService.addStudent(sname, Integer.parseInt(sage), saddress, sdob);
				

		if (msg.equalsIgnoreCase("success")) {
			System.out.println("Record Successfully Inserted...");
		} else {
			System.out.println("Record Insertion Failed...");
		}
		

	}

	
	private static void selectOperation() throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the sudent id:: ");
		String sid = br.readLine();

		Student student = studentService.searchStudent(Integer.parseInt(sid));

		if (student != null) {
			System.out.println(student);
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSDOB");
			System.out.println(student.getSid() + "\t" + student.getSname() + "\t" + student.getSage() + "\t"
					+ student.getSaddress()+"\t" +student.getSdob());
		} else {
			System.out.println("Record Not Found For The Given ID: " + sid);
		}

		
	}

	
	private static void deleteOperation() throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the sudent id:: ");
		String sid = br.readLine();

		String msg = studentService.deleteStudent(Integer.parseInt(sid));

		if (msg.equalsIgnoreCase("success")) {
			System.out.println("Record Deleted Successfully");
		} else if(msg.equalsIgnoreCase("not found")){
			System.out.println("Record Not Found For The Given ID: " + sid);
		} else {
			System.out.println("Record Deletion Failed: ");
		}

	}
	
	private static void updateOperation() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the student id: ");
		String sId = br.readLine();
		
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(Integer.parseInt(sId));
		
		if(student!=null) {
			Student newStudent = new Student();
			
			System.out.println("Student id is: "+student.getSid());
			newStudent.setSid(student.getSid());
			
			System.out.print("Student name is: "+student.getSname()+" | Enter new name:: ");
			String newName = br.readLine();
			
			if(newName.equals("") || newName == "") {
				newStudent.setSname(student.getSname());
			}else {
				newStudent.setSname(newName);
			}
			
			System.out.print("Student age is: "+student.getSage()+" | Enter new age:: ");
			String newAge = br.readLine();
			
			if(newAge.equals("") || newAge == "") {
				newStudent.setSage(student.getSage());
			}else {
				newStudent.setSage(Integer.parseInt(newAge));
			}
			
			System.out.print("Student address is: "+student.getSaddress()+" | Enter new address:: ");
			String newAddress = br.readLine();
			
			if(newAddress.equals("") || newAddress == "" ) {
				newStudent.setSaddress(student.getSaddress());
			}else {
				newStudent.setSaddress(newAddress);
			}
			
			System.out.print("Student dob is: "+student.getSdob()+" | Enter new dob (dd-MM-yyyy):: ");
			String newDob = br.readLine();
			
			if(newDob.equals("") || newDob == "") {
				newStudent.setSdob(student.getSdob());
			}else {
				newStudent.setSdob(newDob);
			}
			
			
			String status = studentService.updateStudent(newStudent);
			
			if(status.equalsIgnoreCase("success")) {
				System.out.println("Record Updated Successfully...");
			}else {
				System.out.println("Record Updation Failed");
				
			}
		}else {
			System.out.println("Record not found for the given id: "+sId);
		}
	}

}
