package apitesting.testdata;

public class CreateUser {
	
	private String accountno;
	private String departmentno;
	private String salary;
	private String pincode;
	
	public CreateUser(String accountno,  String departmentno, String salary, String pincode){
		this.pincode = pincode;
		this.salary =salary;
		this.accountno = accountno;
		this.departmentno = departmentno;
	}
	
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getDepartmentno() {
		return departmentno;
	}
	public void setDepartmentno(String departmentno) {
		this.departmentno = departmentno;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
//	{"accountno":"TA-6789767","departmentno":"7","salary":"899000","pincode":"678900"}

}
