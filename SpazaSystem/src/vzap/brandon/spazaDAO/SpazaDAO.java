package vzap.brandon.spazaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import vzap.brandon.dbConnection.MySQL_Connection;
import vzap.brandon.pojos.Product;
import vzap.brandon.pojos.Supplier;

public class SpazaDAO implements SpazaDaoInterface {

	private MySQL_Connection mysqlConnection = null;
	private Connection conn = null;
	private String [] productIDs;
	private PreparedStatement pStmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public static final String ALL_PRODUCTS = "SELECT * FROM producttable";
	public static final String ONE_PRODUCT = "SELECT * FROM producttable WHERE productID = ?";
	public static final String ADD_PRODUCT = "INSERT INTO producttable VALUES(?,?,?,?,?,?,?,?);";
	public static final String DELETE_PRODUCT = "DELETE FROM producttable WHERE productID = ?";
	public static final String COUNT_ALL = "SELECT COUNT(*) FROM producttable";
	public static final String UPDATE_PROD = "UPDATE producttable " + 
											 "SET productID = ?, productName = ?," + 
											 "productDescription = ? , pricePerUnit = ? ," +
											 "numberUnitsInStock = ? , supplierID = ?, " +
											 "supplierName = ? , supplierPhoneNo = ? " +
											 "WHERE productID = ? ";
	
	
	public SpazaDAO()
	{
		
		mysqlConnection = new MySQL_Connection();
		try {
			conn=mysqlConnection.getConnection();
			saveProductrIDs();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean updateProduct(String productID,
								 String newProductName,
								 String newProductDesc,
								 double newPrice,
								 int newNumInStock,
								 String newSupplierID,
								 String newSupplierName,
								 String newSupplierPhone) 
	{
		
		for(int i = 0; i < productIDs.length; i++)
		{
			//if(productIDs[1].equals(productID))
			{
				try {
					pStmt = conn.prepareStatement(UPDATE_PROD);
					pStmt.setString(1, productID);
					pStmt.setString(2, newProductName);
					pStmt.setString(3, newProductDesc);
					pStmt.setDouble(4, newPrice);
					pStmt.setInt(5, newNumInStock);
					pStmt.setString(6, newSupplierID);
					pStmt.setString(7, newSupplierName);
					pStmt.setString(8, newSupplierPhone);
					pStmt.setString(9, productID);
					
					pStmt.executeUpdate();
					return true;
				} 
				catch (SQLException e) {
					
					JOptionPane.showMessageDialog(null, "Invalid Product ID", 
							null, 1, new ImageIcon("resources/incorrect.png"));
				}
			}
		}
		
		return false;
	}
	
	

	@Override
	public boolean updateProductID(String productID, String newProductID, String newProductName, String newProductDesc,
			double newPrice, int newNumInStock, String newSupplierID, String newSupplierName, String newSupplierPhone) {

		
		
		try {
			pStmt = conn.prepareStatement(UPDATE_PROD);
			pStmt.setString(1, newProductID);
			pStmt.setString(2, newProductName);
			pStmt.setString(3, newProductDesc);
			pStmt.setDouble(4, newPrice);
			pStmt.setInt(5, newNumInStock);
			pStmt.setString(6, newSupplierID);
			pStmt.setString(7, newSupplierName);
			pStmt.setString(8, newSupplierPhone);
			pStmt.setString(9, productID);
			
			pStmt.executeUpdate();
			
			saveProductrIDs();
			
			return true;
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList displayAll() 
	{
		return null;
	}

	@Override
	public Object[][] searchProduct(String productID) {
		
		int noOfItems = countRows();		
		Object [][] rows = new Object[1][8];
		
		
		try {
			
			String pID = null, pName = null, pDesc = null, 
					   sID = null, sName = null, sPhone = null;
			
			double price = 0.0;
			int numInStock = 0;
			
			pStmt = conn.prepareStatement(ONE_PRODUCT);
			pStmt.setString(1, productID);
			rs = pStmt.executeQuery();
			
			for(int row = 0; row < 1; row++)
			{
				rs.next();
				for(int col = 0; col < 8; col++)
				{	
					if(col == 0){
						rows[row][col] = rs.getString(1);
						pID = (String)rows[row][col];
					}
					
					if(col == 1){
						rows[row][col] = rs.getString(2);
						pName = (String)rows[row][col];
					}
					
					if(col == 2){
						rows[row][col] = rs.getString(3);
						pDesc = (String)rows[row][col];
					}
					if(col == 3){
						rows[row][col] = rs.getDouble(4);
						price = (Double)rows[row][col];
					}
					
					if(col == 4){
						rows[row][col] = rs.getInt(5);
						numInStock = (Integer)rows[row][col];
					}
					
					if(col == 5){
						rows[row][col] = rs.getString(6);
						sID = (String)rows[row][col];
					}
					if(col == 6){
						rows[row][col] = rs.getString(7);
						sName = (String)rows[row][col];
					}	
					if(col == 7){
						rows[row][col] = rs.getString(8);
						sPhone = (String)rows[row][col];
					}
					
				}
				
			}
			return rows;

		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteProduct(String productID) {
		
		
		try {
			
			pStmt = conn.prepareStatement(DELETE_PRODUCT);
			pStmt.setString(1, productID);
			pStmt.executeUpdate();
			
			return true;
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			//return false;
		}
		
		return false;
	}
	
	public Object[][] rowData()
	{
		int noOfItems = countRows();
		
		Object [][] rows = new Object[noOfItems][8];
		
		try {
			
			String pID = null, pName = null, pDesc = null, 
				   sID = null, sName = null, sPhone = null;
			double price = 0.0;
			int numInStock = 0;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ALL_PRODUCTS);
			
			for(int row = 0; row < noOfItems; row++)
			{
				rs.next();
				for(int col = 0; col < 8; col++)
				{	
					if(col == 0){
						rows[row][col] = rs.getString(1);
						pID = (String)rows[row][col];
					}
					
					if(col == 1){
						rows[row][col] = rs.getString(2);
						pName = (String)rows[row][col];
					}
					
					if(col == 2){
						rows[row][col] = rs.getString(3);
						pDesc = (String)rows[row][col];
					}
					if(col == 3){
						rows[row][col] = rs.getDouble(4);
						price = (Double)rows[row][col];
					}
					
					if(col == 4){
						rows[row][col] = rs.getInt(5);
						numInStock = (Integer)rows[row][col];
					}
					
					if(col == 5){
						rows[row][col] = rs.getString(6);
						sID = (String)rows[row][col];
					}
					if(col == 6){
						rows[row][col] = rs.getString(7);
						sName = (String)rows[row][col];
					}	
					if(col == 7){
						rows[row][col] = rs.getString(8);
						sPhone = (String)rows[row][col];
					}
					
					Supplier supplier = new Supplier(sID, sName, sPhone);
					
					Product newProduct = new Product(pID, pName, pDesc,
													price, numInStock, supplier);
				}
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return rows;
	}
	
	public int countRows()
	{
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(COUNT_ALL);
			rs.next();
			count = rs.getInt(1);
			return count;
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}


	public void saveProductrIDs()
	{
		int noOfItems = countRows();
		productIDs = new String[noOfItems];
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ALL_PRODUCTS);

			for(int i = 0; i < noOfItems; i++)
			{
				rs.next();
				for(int col = 0; col == 0; col++)
				{	
					if(col == 0){
						productIDs[i] = rs.getString(1);
					}
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean saveNewProduct(Product newProduct) {
		
		try {
			pStmt = conn.prepareStatement(ADD_PRODUCT);
			pStmt.setString(1, newProduct.getProductID());
			pStmt.setString(2, newProduct.getProductName());
			pStmt.setString(3, newProduct.getProductDescription());
			pStmt.setDouble(4, newProduct.getPrice());
			pStmt.setInt(5, newProduct.getQuantityInStock());
			pStmt.setString(6, newProduct.getSupplier().getSupplierID());
			pStmt.setString(7, newProduct.getSupplier().getSupplierName());
			pStmt.setString(8, newProduct.getSupplier().getSupplierPhoneNo());
			
			pStmt.executeUpdate();
			
			saveProductrIDs();
			return true;
		} 
		catch (SQLException e) {
				
			JOptionPane.showMessageDialog(null, "Product Exists", 
					null, 1, new ImageIcon("resources/incorrect.png"));
			
		}
		
		return false;
	}





}
