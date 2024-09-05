package in.coder.service;

import in.coder.daofactory.StudentDaoFactory;
import in.coder.persistence.IStudentDao;
import in.ineuron.dto.Student;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao studentDao;

	@Override
	public String addStudent(String sname, Integer sage, String saddress, String sdob) {
		// TODO Auto-generated method stub
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.addStudent(sname, sage, saddress, sdob);
	}

	@Override
	public Student searchStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.searchStudent(sid);
	}

	@Override
	public String updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(sid);
	}

}
