package example.service.controller.model;

import example.service.data.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductResponseTest {

  @Test
  void fromCopiesProductFieldsAndFormatsEpochSecondsAsUtcDates() {
    Product product = new Product();
    product.setId("42");
    product.setName("Sample product");
    product.setDescription("Sample description");
    product.setActive(true);
    product.setStartTime(1609459200L);
    product.setEndTime(1612051200L);

    ProductResponse response = ProductResponse.from(product);

    assertAll(
            () -> assertEquals("42", response.getId()),
            () -> assertEquals("Sample product", response.getName()),
            () -> assertEquals("Sample description", response.getDescription()),
            () -> assertTrue(response.isActive()),
            () -> assertEquals("2021-01-01T00:00Z", response.getStartDate()),
            () -> assertEquals("2021-01-31T00:00Z", response.getEndDate())
    );
  }
}
