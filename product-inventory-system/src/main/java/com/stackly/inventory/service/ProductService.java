package com.stackly.inventory.service;

import com.stackly.inventory.dto.ProductRequest;
import com.stackly.inventory.dto.StockUpdateRequest;
import com.stackly.inventory.exception.FieldValidationException;
import com.stackly.inventory.exception.ProductNotFoundException;
import com.stackly.inventory.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private static final int MAX_STOCK = 100_000;

    private final List<Product> store = new ArrayList<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public synchronized Product create(ProductRequest request) {
        validateStock(request.getStock());
        long id = idSequence.incrementAndGet();
        Product product = new Product(id, request.getProductName(), request.getCategory(),
                request.getStock(), request.getPrice());
        store.add(product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(store);
    }

    public Product findById(Long id) {
        return store.stream()
                .filter(p -> p.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public synchronized Product update(Long id, ProductRequest request) {
        Product product = findById(id);
        validateStock(request.getStock());
        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        return product;
    }

    public synchronized Product updateStock(Long id, StockUpdateRequest request) {
        Product product = findById(id);
        validateStock(request.getStock());
        product.setStock(request.getStock());
        return product;
    }

    public synchronized void delete(Long id) {
        Product product = findById(id);
        store.remove(product);
    }

    private void validateStock(Integer stock) {
        if (stock != null && stock > MAX_STOCK) {
            throw new FieldValidationException("Stock cannot exceed " + MAX_STOCK + " units");
        }
    }
}
