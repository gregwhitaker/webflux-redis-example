package example.service.service;

import example.service.data.ProductRepository;
import example.service.data.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @Mock
  private ProductRepository repo;

  private ProductService service;

  @BeforeEach
  void setUp() {
    service = new ProductService(repo);
  }

  @Test
  void getProductReturnsFoundProduct() {
    String id = "product-123";
    Product product = new Product();
    product.setId(id);
    when(repo.findOne(id)).thenReturn(Mono.just(product));

    StepVerifier.create(service.getProduct(id))
        .expectNext(product)
        .verifyComplete();

    verify(repo).findOne(id);
  }

  @Test
  void getProductCompletesEmptyWhenProductIsMissing() {
    String id = "missing-product";
    when(repo.findOne(id)).thenReturn(Mono.empty());

    StepVerifier.create(service.getProduct(id))
        .verifyComplete();

    verify(repo).findOne(id);
  }

  @Test
  void getProductPropagatesRepositoryError() {
    String id = "product-456";
    RuntimeException error = new RuntimeException("Repository unavailable");
    when(repo.findOne(id)).thenReturn(Mono.error(error));

    StepVerifier.create(service.getProduct(id))
        .expectErrorMatches(actual -> actual == error)
        .verify();

    verify(repo).findOne(id);
  }
}
