package sample.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ProductTypeTest {

	@Test
	@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
	void containsStockType() {
	    // given
	    ProductType givenType = ProductType.HANDMADE;

	    // when
	    boolean result = ProductType.containsStockType(givenType);

	    // then
		assertFalse(result);
	}

	@Test
	@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
	void containsStockType2() {
		// given
		ProductType givenType = ProductType.BAKERY;

		// when
		boolean result = ProductType.containsStockType(givenType);

		// then
		assertTrue(result);
	}

	@Test
	@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
	void containsStockType3() {
		// given
		ProductType givenType1 = ProductType.HANDMADE;
		ProductType givenType2 = ProductType.BOTTLE;
		ProductType givenType3 = ProductType.BAKERY;

		// when
		boolean result1 = ProductType.containsStockType(givenType1);
		boolean result2 = ProductType.containsStockType(givenType2);
		boolean result3 = ProductType.containsStockType(givenType3);

		// then
		assertFalse(result1);
		assertTrue(result2);
		assertTrue(result3);
	}

	@CsvSource({"HANDMADE,false","BOTTLE,true","BAKERY,true"})
	@ParameterizedTest
	@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
	void containsStockType4(ProductType productType, boolean expected) {
		// when
		boolean result = ProductType.containsStockType(productType);

		// then
		assertThat(result).isEqualTo(expected);
	}

	private static Stream<Arguments> provideProductTypesForCheckingStockType() {
		return Stream.of(
			Arguments.of(ProductType.HANDMADE, false),
			Arguments.of(ProductType.BOTTLE, true),
			Arguments.of(ProductType.BAKERY, true)
		);
	}

	@MethodSource("provideProductTypesForCheckingStockType")
	@ParameterizedTest
	@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
	void containsStockType5(ProductType productType, boolean expected) {
		// when
		boolean result = ProductType.containsStockType(productType);

		// then
		assertThat(result).isEqualTo(expected);
	}
}