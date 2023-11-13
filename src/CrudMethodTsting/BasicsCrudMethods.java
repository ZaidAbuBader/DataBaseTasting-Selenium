package CrudMethodTsting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicsCrudMethods {
	Connection con = null;
	Statement stm = null;
	ResultSet rs;

	@BeforeTest
	public void Setup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "ZZz123??");

	}

	@Test(priority = 1)
	public void AddNewData() throws SQLException {
		stm = con.createStatement();
		String InsertQuery1 = "insert into customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,city,country)"
				+ "	values (2040,'osama','bader','shaker',0795032341,'abu aland','amman','jordan')";
		int InsertRow = stm.executeUpdate(InsertQuery1);
		if (InsertRow > 0) {
			System.out.println("The row is added correctly");
		} else {
			System.out.println("Sorry,the row is not added");
		}
	}

	@Test(priority = 2)
	public void UpdateData() throws SQLException {
		stm = con.createStatement();
		String UpdateQuery1 = " Update customers set city= 'irbid' where customerNumber=2040  ";
		int updateRow = stm.executeUpdate(UpdateQuery1);
		if (updateRow > 0) {
			System.out.println("The data is updated correctly");
		} else {
			System.out.println("Sorry the data is not updated");
		}
	}

	@Test(priority = 3)
	public void SelectOneCustomer() throws SQLException {
		stm = con.createStatement();

		// rs = stm.executeQuery("select * from customers ");

		rs = stm.executeQuery("select * from customers where customerNumber=2040");

		while (rs.next()) {
			int customernumber1 = rs.getInt("customerNumber");
			String customerName1 = rs.getString("customerName");
			String contactFirstName1 = rs.getString("contactFirstName");
			String contactLastName1 = rs.getString("contactLastName");
			String CityName1 = rs.getString("city");

//			System.out.println("the customer number is:  " + customernumber1);
//			System.out.println("the customer Name is:   " + customerName1);
//			System.out.println("the customer FirstName is :   " + contactFirstName1);
//			System.out.println("the customer LastName is :   " + contactLastName1);
			//System.out.println("the customer City is : @@@  " + CityName1);
			if (CityName1.equals("irbid")) {
				System.out.println("the new city is updated to irbid");
			} else {
				System.out.println("the updated city is filed");
			}

		}
	}

	@Test(priority = 4)
	public void DleteDate() throws SQLException {
		stm = con.createStatement();
		String DeleteQuery1 = " Delete from customers where customerNumber=2040  ";
		int DeleteRow = stm.executeUpdate(DeleteQuery1);
		if (DeleteRow > 0) {
			System.out.println("The data is deleted correctly");
		} else {
			System.out.println("Sorry the data is not deleted");
		}
	}

	@AfterTest
	public void PostTest() {

	}

}
