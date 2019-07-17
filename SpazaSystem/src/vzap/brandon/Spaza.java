package vzap.brandon;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import javafx.scene.control.DialogPane;

import java.awt.*;
import java.awt.event.*;
import vzap.brandon.spazaDAO.*;
import vzap.brandon.pojos.*;

public class Spaza extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane;
	private JTextField tfProductID;
	private JTextField tfProductName;
	private JTextArea tfProductDesc;
	private JTextField tfPricePerUnit;
	private JTextField tfSearch, tfNumInStock, tfSupplierID, tfSupplierName, tfSupplierPhoneNo;
	
	private JButton bDisplay, bSearch, bAdd, bDelete, bExit, bNext, bUpdate, bChangePid;
	
	private JLabel userPic;
	private JPanel topPane, bottomPane, leftPane, rightPane;
	private JTable table;
	DefaultTableModel dbTable;
	
	String [] columns, productIDs;
	Object [][] rows;
	
	private SpazaDaoInterface spazaDAO;
	
	public Spaza()
	{
		
		spazaDAO = new SpazaDAO();
		
		
		// =============== JTable ===================
		
		columns = new String[] {"Product ID", 
								"Product Name",
								"Product Description",
								"Price Per Unit", 
								"Quantity Avail", 
								"Supplier ID", 
								"Supplier Name", 
								"Supplier Tel"};	
		
		rows = spazaDAO.rowData();
		
		dbTable = new DefaultTableModel(rows, columns);
		
		table = new JTable();
		table.setSelectionBackground(new Color(245,205,122));
		table.setSelectionForeground(Color.BLACK);
		
		table.setModel(dbTable);
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 


		// ========= RIGHT PANEL ===========
		
		rightPane = new JPanel(new BorderLayout());
		rightPane.setBounds(223, 98, 977, 541);
		this.getContentPane().add(rightPane);
	
		rightPane.add(scrollPane, BorderLayout.CENTER);
		rightPane.add(table.getTableHeader(), BorderLayout.NORTH);
		
		
		// ========= TOP PANEL ==============
		
		topPane = new JPanel();
		topPane.setBackground(new Color(245,205,122));
		topPane.setBounds(0, 0, 1200, 98);
		this.getContentPane().add(topPane);
		topPane.setLayout(null);
		
		userPic = new JLabel();
		userPic.setIcon(new ImageIcon("resources/profile.png"));
		userPic.setBounds(1104, 6, 90, 92);
		topPane.add(userPic);
		
		JLabel jlUserName = new JLabel("Brandon K. Klaas");
		jlUserName.setFont(new Font("Arial", Font.BOLD, 16));
		jlUserName.setBounds(942, 32, 150, 19);
		topPane.add(jlUserName);
		
		JLabel jlUserPosition = new JLabel("MANAGER");
		jlUserPosition.setBackground(SystemColor.windowBorder);
		jlUserPosition.setBounds(942, 51, 113, 16);
		topPane.add(jlUserPosition);
		
		JLabel jlTitle = new JLabel("Database");
		jlTitle.setFont(new Font("Arial", Font.BOLD, 30));
		jlTitle.setBounds(592, 30, 140,50);
		topPane.add(jlTitle);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(220, 59, 117, 29);
		topPane.add(tfSearch);
		
		bSearch = new JButton();
		bSearch.setIcon(new ImageIcon("resources/search.png"));
		bSearch.setBounds(340, 58, 31, 31);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		bSearch.setBorder(emptyBorder);
		
		topPane.add(bSearch);
		bSearch.addActionListener(this);
		
		JLabel spazaLogo = new JLabel("");
		spazaLogo.setIcon(new ImageIcon("resources/SPAZA-LOGO.png"));
		spazaLogo.setBounds(44, 6, 150, 86);
		topPane.add(spazaLogo);
		

		// ========= BOTTOM PANEL =============
		
		bottomPane = new JPanel();
		bottomPane.setBackground(new Color(245,205,122));
		bottomPane.setBounds(0, 639, 1200, 39);
		this.getContentPane().add(bottomPane);
		bottomPane.setLayout(null);
		
		bAdd = new JButton("ADD");
		bAdd.setBounds(6, 6, 88, 29);
		
		bAdd.addActionListener(this);
		bottomPane.add(bAdd);
		
		
		bUpdate = new JButton("UPDATE");
		bUpdate.setBounds(93, 6, 88, 29);
		bottomPane.add(bUpdate);
		
		bDelete = new JButton("DELETE");
		bDelete.setBounds(186, 6, 88, 29);
		bottomPane.add(bDelete);
		
		bExit = new JButton("EXIT");
		bExit.setBounds(1050, 6, 117, 29);
		bottomPane.add(bExit);
//		
//		bNext = new JButton("Next");
//		bNext.setBounds(700, 6,117,29);
//		bottomPane.add(bNext);
		
		bDisplay = new JButton("Display All");
		bDisplay.setBounds(450, 6, 117, 29);
		bottomPane.add(bDisplay);
		
		bChangePid = new JButton("Change ID");
		bChangePid.setBounds(300, 6, 117, 29);
		bottomPane.add(bChangePid);
		
		// ========= LEFT PANEL =============
		
		leftPane = new JPanel();
		leftPane.setBackground(new Color(240,215,164));
		leftPane.setBounds(0, 98, 223, 541);
		this.getContentPane().add(leftPane);
		leftPane.setLayout(null);
		
		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setBounds(6, 9, 113, 16);
		leftPane.add(lblProductId);
		
		tfProductID = new JTextField();
		tfProductID.setBounds(6, 24, 197, 26);
		leftPane.add(tfProductID);
		tfProductID.setColumns(10);
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(6, 62, 113, 16);
		leftPane.add(lblProductName);
		
		tfProductName = new JTextField();
		tfProductName.setBounds(6, 79, 197, 26);
		leftPane.add(tfProductName);
		tfProductName.setColumns(10);
		
		JLabel lProductDesc = new JLabel("Product Description:");
		lProductDesc.setBounds(6, 117, 197, 16);
		leftPane.add(lProductDesc);
		
		tfProductDesc = new JTextArea();
		tfProductDesc.setBounds(6, 136, 197, 75);
		leftPane.add(tfProductDesc);
		tfProductDesc.setColumns(10);
		
		JLabel lPricePerUnit = new JLabel("Price Per Unit");
		lPricePerUnit.setBounds(6, 229, 113, 16);
		leftPane.add(lPricePerUnit);
		
		tfPricePerUnit = new JTextField();
		tfPricePerUnit.setBounds(6, 247, 197, 26);
		leftPane.add(tfPricePerUnit);
		tfPricePerUnit.setColumns(10);
		
		JLabel lNumInStock = new JLabel("Number Of Units In Stock:");
		lNumInStock.setBounds(6, 295, 197, 16);
		leftPane.add(lNumInStock);
		
		tfNumInStock = new JTextField();
		tfNumInStock.setBounds(6, 313, 197, 26);
		leftPane.add(tfNumInStock);
		tfNumInStock.setColumns(10);
		
		JLabel lSupplierID = new JLabel("Supplier ID:");
		lSupplierID.setBounds(6, 351, 197, 16);
		leftPane.add(lSupplierID);
		
		tfSupplierID = new JTextField();
		tfSupplierID.setBounds(6, 371, 197, 27);
		leftPane.add(tfSupplierID);
		
		JLabel lblNewLabel = new JLabel("Supplier Name:");
		lblNewLabel.setBounds(6, 410, 187, 16);
		leftPane.add(lblNewLabel);
		
		tfSupplierName = new JTextField();
		tfSupplierName.setBounds(6, 429, 197, 27);
		leftPane.add(tfSupplierName);
		
		JLabel lSupplierPhoneNo = new JLabel("Supplier Phone No:");
		lSupplierPhoneNo.setBounds(6, 475, 197, 16);
		leftPane.add(lSupplierPhoneNo);
		
		tfSupplierPhoneNo = new JTextField();
		tfSupplierPhoneNo.setBounds(6, 503, 197, 27);
		leftPane.add(tfSupplierPhoneNo);
		
		bDelete.addActionListener(this);
		bDisplay.addActionListener(this);
		bExit.addActionListener(this);
		bChangePid.addActionListener(this);
		bUpdate.addActionListener(this);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 1) {
					JTable sampleTable = (JTable)e.getSource();
					
					int row = sampleTable.getSelectedRow();
					
					tfProductID.setText((String)rows[row][0]);
					tfProductName.setText((String)rows[row][1]);
					tfProductDesc.setText((String)rows[row][2]);
					tfPricePerUnit.setText(rows[row][3].toString());
					tfNumInStock.setText((String)rows[row][4].toString());
					tfSupplierID.setText((String)rows[row][5].toString());
					tfSupplierName.setText((String)rows[row][6].toString());
					tfSupplierPhoneNo.setText((String)rows[row][7].toString());
				}
			}
		});

		this.setResizable(false);
		this.setBounds(50, 10, 1200, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
	}
	
	public static void main(String [] args)
	{
		new Spaza();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source == bDelete)
		{
			if(tfProductID.getText().equals("") != true &&
			   tfProductID.getText() != null)
			{
				boolean tf = spazaDAO.deleteProduct(tfProductID.getText());
				
				if(tf == true)
				{
					JOptionPane.showMessageDialog(null, "Product Deleted", 
							null, 1, new ImageIcon("resources/correct.png"));
					
					rows = spazaDAO.rowData();
					dbTable = new DefaultTableModel(rows, columns);
					table.setModel(dbTable);
					dbTable.fireTableDataChanged();
				}
				
				if(tf == false)
				{
					JOptionPane.showMessageDialog(null, "Product does not Exist", 
							null, 1, new ImageIcon("resources/incorrect.png"));
					
				}

			}
			else
			{
				JOptionPane.showMessageDialog(null, "Enter Product ID", 
						null, 1, new ImageIcon("resources/incorrect.png"));
			}
				
		}
		
		if(source == bDisplay)
		{
			rows = spazaDAO.rowData();
			dbTable = new DefaultTableModel(rows, columns);
			table.setModel(dbTable);
			dbTable.fireTableDataChanged();
		}
		
		if(source == bSearch)
		{
			if(tfSearch.getText().equals("") != true &&
			   tfSearch.getText() != null)
			{
				rows = spazaDAO.searchProduct(tfSearch.getText());
				dbTable = new DefaultTableModel(rows, columns);
				table.setModel(dbTable);
				dbTable.fireTableDataChanged();	
			}
		}
		
		if(source == bExit)
		{
			System.exit(0);
		}
		
		if(source == bChangePid)
		{
			String newProdID= JOptionPane.showInputDialog("Please enter new Product ID");
			
			
			if (tfProductID.getText().equals("") != true &&
					tfProductID.getText() != null)
			{
				spazaDAO.updateProductID(tfProductID.getText(),
						newProdID,
						tfProductName.getText(),
						tfProductDesc.getText(),
						Double.parseDouble(tfPricePerUnit.getText()),
						Integer.parseInt(tfNumInStock.getText()),
						tfSupplierID.getText(),
						tfSupplierName.getText(), 
						tfSupplierPhoneNo.getText());
				
				tfProductID.setText("");
				tfProductName.setText("");
				tfProductDesc.setText("");
				tfPricePerUnit.setText("");
				tfNumInStock.setText("");
				
				tfSupplierID.setText("");
				tfSupplierName.setText("");
				tfSupplierPhoneNo.setText("");
				
				rows = spazaDAO.rowData();
				dbTable = new DefaultTableModel(rows, columns);
				table.setModel(dbTable);
				dbTable.fireTableDataChanged();
				
				JOptionPane.showMessageDialog(this, "Product ID Changed", 
						null, 1, new ImageIcon("resources/correct.png"));
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Select Product Info", 
						null, 1, new ImageIcon("resources/incorrect.png"));
			
			}
			
		}
		
		if(source == bUpdate)
		{
			if (tfProductID.getText().equals("") != true &&
					tfProductID.getText() != null)
			{
				boolean tf =spazaDAO.updateProduct(tfProductID.getText(), 
						tfProductName.getText(),
						tfProductDesc.getText(),
						Double.parseDouble(tfPricePerUnit.getText()),
						Integer.parseInt(tfNumInStock.getText()),
						tfSupplierID.getText(),
						tfSupplierName.getText(), 
						tfSupplierPhoneNo.getText());
				
				tfProductID.setText("");
				tfProductName.setText("");
				tfProductDesc.setText("");
				tfPricePerUnit.setText("");
				tfNumInStock.setText("");
				
				tfSupplierID.setText("");
				tfSupplierName.setText("");
				tfSupplierPhoneNo.setText("");
				
				rows = spazaDAO.rowData();
				dbTable = new DefaultTableModel(rows, columns);
				table.setModel(dbTable);
				dbTable.fireTableDataChanged();

				if(tf == true)
				{
					JOptionPane.showMessageDialog(this, "Product Updated", 
							null, 1, new ImageIcon("resources/correct.png"));
				}
//				
//				if(tf == false)
//				{
//					JOptionPane.showMessageDialog(this, "There's no such Product ID", 
//							null, 1, new ImageIcon("resources/incorrect.png"));
//				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Enter Product Info", 
						null, 1, new ImageIcon("resources/incorrect.png"));
			
			}
			
		}
		
		if(source == bAdd)
		{
			
			if (tfProductID.getText().equals("") != true &&
				tfProductID.getText() != null)
			{
				Product newProduct;
				
				newProduct = new Product(tfProductID.getText(), 
							tfProductName.getText(), tfProductDesc.getText(),
							Double.parseDouble(tfPricePerUnit.getText()), 
							Integer.parseInt(tfNumInStock.getText()),
							new Supplier(tfSupplierID.getText(),
										 tfSupplierName.getText(), 
										 tfSupplierPhoneNo.getText()));
				
				boolean tf = spazaDAO.saveNewProduct(newProduct);
				
				tfProductID.setText("");
				tfProductName.setText("");
				tfProductDesc.setText("");
				tfPricePerUnit.setText("");
				tfNumInStock.setText("");
				
				tfSupplierID.setText("");
				tfSupplierName.setText("");
				tfSupplierPhoneNo.setText("");
				
				rows = spazaDAO.rowData();
				dbTable = new DefaultTableModel(rows, columns);
				table.setModel(dbTable);
				dbTable.fireTableDataChanged();
				
				if(tf == true)
				{
					JOptionPane.showMessageDialog(this, "Product Added", 
							null, 1, new ImageIcon("resources/correct.png"));
				}
			}
			else {
			
				JOptionPane.showMessageDialog(this, "Enter Product Info", 
						null, 1, new ImageIcon("resources/incorrect.png"));
			}
			
		}
		
	}
}
