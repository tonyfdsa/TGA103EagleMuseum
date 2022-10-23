package index_product.vo;

public class Index_productVo {
	private Long productID;
    private String productName;
    private String productDescription;
    private byte[] productImage;
    
    public Index_productVo() {
        super();
    }

    public Index_productVo(Long productID, String productName, String productDescription, byte[] productImage) {
        super();
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
    }
    
    @Override
    public String toString() {
        return "Index_productVo [productID=" + productID + ", productName=" + productName
                + ", productDescription=" + productDescription + "]";
    }
 
    
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
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
	public void setProductDescription(String productdescription) {
		this.productDescription = productdescription;
	}
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
	
	
	
	
}
