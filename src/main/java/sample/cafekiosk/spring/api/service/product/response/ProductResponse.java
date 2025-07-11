package sample.cafekiosk.spring.api.service.product.response;

import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;

import java.util.List;

import lombok.Builder;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Builder
public record ProductResponse(
	Long id,
	String productNumber,
	ProductType type,
	ProductSellingStatus sellingStatus,
	String name,
	int price
) {

	public static List<ProductSellingStatus> forDisplay() {
		return List.of(SELLING, HOLD);
	}

	public static ProductResponse of(Product product) {
		return ProductResponse.builder()
			.id(product.getId())
			.productNumber(product.getProductNumber())
			.type(product.getType())
			.sellingStatus(product.getSellingStatus())
			.name(product.getName())
			.price(product.getPrice())
			.build();
	}
}
