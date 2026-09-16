package example.service.controller.model;

import example.service.data.model.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductResponseTest {

  @ParameterizedTest(name = "copies product fields and formats UTC timestamps when active={0}")
  @ValueSource(booleans = {true, false})
  void fromCopiesProductFieldsAndFormatsUtcTimestamps(boolean active) {
    Product product = new Product();
    product.setId("product-123");
    product.setName("Test product");
    product.setDescription("A product used to verify response mapping");
    product.setActive(active);
    product.setStartTime(1609459201L);
    product.setEndTime(1609549262L);

    ProductResponse response = ProductResponse.from(product);

    assertAll(
        () -> assertEquals("product-123", response.getId()),
        () -> assertEquals("Test product", response.getName()),
        () -> assertEquals("A product used to verify response mapping", response.getDescription()),
        () -> assertEquals(active, response.isActive()),
        () -> assertEquals("2021-01-01T00:00:01Z", response.getStartDate()),
        () -> assertEquals("2021-01-02T01:01:02Z", response.getEndDate())
    );
  }
}
