package vzap.brandon.pojos;

public class Product
{
	private String productID;
	private String productName;
	private String productDescription;
	private double price;
	private int quantityInStock;
	private Supplier supplier;
	
	public Product(String productID, String productName, String productDescription, double price, int quantityInStock,
				   Supplier supplier) 
	{
		this(supplier);
		this.productID = productID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.price = price;
		this.quantityInStock = quantityInStock;
		
	}

	public Product(String productID)
	{
		this.productID = productID;
	}
	
	public Product(Supplier supplier)
	{
		this.supplier = supplier;
	}

	public Supplier getSupplier()
	{
		return supplier;
	}
	
	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", productDescription="
				+ productDescription + ", price=" + price + ", quantityInStock=" + quantityInStock + ", supplier="
				+ supplier + "]";
	}
}
