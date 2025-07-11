package sample.cafekiosk.spring.api.service.product.request;

import lombok.Builder;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Builder
public record ProductCreateServiceRequest(
	ProductType type,
	ProductSellingStatus sellingStatus,
	String name,
	int price
) {

	public Product toEntity(String nextProductNumber) {
		return Product.builder()
			.productNumber(nextProductNumber)
			.type(type)
			.sellingStatus(sellingStatus)
			.name(name)
			.price(price)
			.build();
	}
}
