package in.coder.service;

import in.ineuron.dto.Student;

public interface IStudentService {
	public String addStudent(String sname, Integer sage, String saddress, String sdob);

	public Student searchStudent(Integer sid);

	public String updateStudent(Student student);

	public String deleteStudent(Integer sid);
}
