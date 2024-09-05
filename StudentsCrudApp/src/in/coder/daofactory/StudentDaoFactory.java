package in.coder.daofactory;

import in.coder.persistence.IStudentDao;
import in.coder.persistence.StudentDaoImpl;

public class StudentDaoFactory {
	private StudentDaoFactory() {
	}

	private static IStudentDao studentDao = null;

	public static IStudentDao getStudentDao() {
		if (studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
	}
}
