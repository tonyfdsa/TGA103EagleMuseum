package index_product.dto;

import java.time.LocalDateTime;

public class IndexProductDto {
	private Long productID;
	private String productName;
	private String productDescription;
	private String productImageBase64;
	
	@Override
	public String toString() {
		return "IndexProductDto [productID=" + productID + ", productName=" + productName
				+ ", productDescription=" + productDescription +  ", productImageBase64=" + productImageBase64 + "]";
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
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImageBase64() {
		return productImageBase64;
	}
	public void setProductImageBase64(String productImageBase64) {
		this.productImageBase64 = productImageBase64;
	}
	
}

	
	
	

