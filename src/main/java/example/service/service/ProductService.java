package example.service.service;

import example.service.data.ProductRepository;
import example.service.data.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Service for interacting with product data.
 */
@Component
public class ProductService {
  private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

  private final ProductRepository repo;

  @Autowired
  public ProductService(final ProductRepository repo) {
    this.repo = repo;
  }

  /**
   * Gets a product by id.
   *
   * @param id product identifier
   * @return a {@link Product} if found; otherwise empty
   */
  public Mono<Product> getProduct(final String id) {
    return repo.findOne(id);
  }
}
