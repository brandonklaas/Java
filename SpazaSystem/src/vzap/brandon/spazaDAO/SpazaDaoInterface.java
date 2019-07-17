package vzap.brandon.spazaDAO;

import java.util.ArrayList;
import vzap.brandon.pojos.Product;
import vzap.brandon.pojos.Supplier;

public interface SpazaDaoInterface
{
	public abstract ArrayList displayAll();
	public abstract Object[][] searchProduct(String productID);
	public abstract boolean deleteProduct(String productID);
	public abstract boolean saveNewProduct(Product newProduct);
	public abstract boolean updateProductID(String productID,
											String newProductID,
			  								String newProductName,
			  								String newProductDesc,
			  								double newPrice,
			  								int newNumInStock,
			  								String newSupplierID,
			  								String newSupplierName,
			  								String newSupplierPhone
			  								);
	
	public abstract boolean updateProduct(String productID,
										  String newProductName,
										  String newProductDesc,
										  double newPrice,
										  int newNumInStock,
										  String newSupplierID,
										  String newSupplierName,
										  String newSupplierPhone
										  );
	public abstract int countRows();
	public abstract Object[][] rowData();
	
}
