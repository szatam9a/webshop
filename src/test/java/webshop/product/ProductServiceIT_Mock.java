package webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceIT_Mock {

    @Mock
    ProductDao productDao;

    @InjectMocks
    ProductService productService;

    String testTrueProductName = "TEST_ProductName";
    long testID = 1;
    int testTruePrice = 123_456_789;
    Product testProduct = new Product(testID, testTrueProductName, testTruePrice);

    @Test
    void testLoadProductFromFile() {
        assertThatThrownBy(this::loadCall)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot open file for read!");

        verify(productDao, never()).insertProduct(any());

    }

    private void loadCall() {
        productService.loadProductFromFile(Path.of("/scr"));
    }
}
