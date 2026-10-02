package example.service.controller.model;

import example.service.data.model.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductResponseTest {

  @Test
  void fromMapsProductFieldsAndFormatsEpochSecondsAsUtcOffsets() {
    final Product product = new Product();
    product.setId("product-1");
    product.setName("Test Product");
    product.setDescription("A product used in tests");
    product.setActive(true);
    product.setStartTime(1609459200L);
    product.setEndTime(1609545600L);

    final ProductResponse response = ProductResponse.from(product);

    assertThat(response.getId()).isEqualTo("product-1");
    assertThat(response.getName()).isEqualTo("Test Product");
    assertThat(response.getDescription()).isEqualTo("A product used in tests");
    assertThat(response.isActive()).isTrue();
    assertThat(response.getStartDate()).isEqualTo("2021-01-01T00:00Z");
    assertThat(response.getEndDate()).isEqualTo("2021-01-02T00:00Z");
  }
}
