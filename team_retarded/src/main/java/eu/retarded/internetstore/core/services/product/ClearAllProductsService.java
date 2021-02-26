package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.responses.product.ClearAllProductsResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClearAllProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ClearAllProductsResponse execute() {
        productRepository.deleteAll();
        return new ClearAllProductsResponse();
    }
}

