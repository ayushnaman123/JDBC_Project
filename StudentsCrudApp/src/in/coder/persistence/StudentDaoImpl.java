package in.coder.persistence;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import in.coder.util.JdbcUtil;
import in.ineuron.dto.Student;

public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	java.sql.Date sqlDob = null;

	@Override
	public String addStudent(String sname, Integer sage, String saddress, String sdob) {
		String sqlInsertQuery = "Insert into students(`name`, `age`,`address`, `dob`) values(?,?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {

				if (sdob != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udate = sdf.parse(sdob);
					long dValues = udate.getTime();

					sqlDob = new java.sql.Date(dValues);
				}

				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);
				pstmt.setDate(4, sqlDob);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected == 1) {
					return "success";
				}
			}
		} catch (IOException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		String sqlSelectQuery = "Select id, name, age, address, dob from students where id = ?";
		Student student = null;

		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null) {
				pstmt.setInt(1, sid);
			}

			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {
				if (resultSet.next()) {
					student = new Student();
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddress(resultSet.getString(4));

					java.sql.Date dob = resultSet.getDate(5);

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					student.setSdob(sdf.format(dob));

					
					return student;
				}

			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateStudent(Student student) {
		String sqlUpdateQuery = "Update students set name=?, age=?, address=?, dob=? where id=?  ";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			
			if(pstmt!=null) {
				
				if(student.getSdob()!=null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udate = sdf.parse(student.getSdob());
					long dValues = udate.getTime();

					sqlDob = new java.sql.Date(dValues);
				}
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());
				pstmt.setDate(4, sqlDob);
				pstmt.setInt(5, student.getSid());
				
				int rowsAffected = pstmt.executeUpdate();
				
				if(rowsAffected==1) {
					return "success";
				}
			}
		} catch (IOException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "Delete from students where id = ?";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlDeleteQuery);

			if (pstmt != null) {
				pstmt.setInt(1, sid);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected == 1) {
					return "Success";
				}else {
					return "not found";
				}
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}

		return "Failure";
	}

}
