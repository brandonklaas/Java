package vzap.brandon.pojos;

public class Supplier {
	
	private String supplierID = null;
	private String supplierName = null;
	private String supplierPhoneNo = null;
	
	public Supplier(String supplierID, String supplierName, String supplierPhoneNo) {
		super();
		this.supplierID = supplierID;
		this.supplierName = supplierName;
		this.supplierPhoneNo = supplierPhoneNo;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierPhoneNo() {
		return supplierPhoneNo;
	}

	public void setSupplierPhoneNo(String supplierPhoneNo) {
		this.supplierPhoneNo = supplierPhoneNo;
	}

	@Override
	public String toString() {
		return "Supplier [supplierID=" + supplierID + ", supplierName=" + supplierName + ", supplierPhoneNo="
				+ supplierPhoneNo + "]";
	}

	

}
