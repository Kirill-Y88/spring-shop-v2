package ru.gb.market.happy.product.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
//import ru.gb.market.happy.product.dto.ProductDto;
import ru.gb.market.happy.product.model.Product;
import ru.gb.market.happy.product.repositories.ProductRepository;
import ru.gb.market.happy.router.dto.ProductDto;

import java.text.ParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(this::toDto);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(this::toDto);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product toEntity(ProductDto productDto) throws ParseException {
        return modelMapper.map(productDto, Product.class);
    }

}
