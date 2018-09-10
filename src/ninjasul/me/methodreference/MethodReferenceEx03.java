package ninjasul.me.methodreference;

import com.sun.javaws.exceptions.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferenceEx03 {
    public static void main(String[] args) {
        final Section section1 = new Section(1);

        final Function<Integer, Section> sectionFactoryWithLambdaExpression = number -> new Section(number);
        final Section section1WithLambdaExpression = sectionFactoryWithLambdaExpression.apply(1);

        final Function<Integer, Section> sectionFactoryWithMethodReference = Section::new;
        final Section section1WithMethodReference = sectionFactoryWithMethodReference.apply(1);

        System.out.println(section1);
        System.out.println(section1WithLambdaExpression);
        System.out.println(section1WithMethodReference);

        System.out.println("================================================================");

        final OldProduct oldProduct = new OldProduct(1L, "A", new BigDecimal("100"));
        System.out.println(oldProduct);

        final OldProductFactory oldProductFactory = OldProduct::new;
        System.out.println(oldProductFactory.create( 1L, "A", new BigDecimal("100")));

        System.out.println("================================================================");

        final ProductA productA = createProduct( 1L, "A", new BigDecimal("123"), ProductA::new);
        final ProductB productB = createProduct( 2L, "B", new BigDecimal("111"), ProductB::new);
        final ProductC productC = createProduct( 3L, "C", new BigDecimal("10"), ProductC::new);

        System.out.println(productA);
        System.out.println(productB);
        System.out.println(productC);
    }

    private static <T extends Product> T createProduct( Long id, String name, BigDecimal price, ProductFactory<T> productFactory ) {
        if (id == null || id <= 0 ) {
            throw new IllegalArgumentException("The id must be a positive Long.");
        }
        if( name == null || name.isEmpty() ) {
            throw new IllegalArgumentException("The name is not give.");
        }
        if( price == null || price.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("The price must be greater than 0.");
        }

        return productFactory.create( id, name, price );
    }

}

@FunctionalInterface
interface OldProductFactory {
    OldProduct create( Long id, String name, BigDecimal price);
}

@FunctionalInterface
interface ProductFactory <T extends Product> {
    T create( Long id, String name, BigDecimal price);
}

@Data
@AllArgsConstructor
class Section {
    private int number;
}

@Data
@AllArgsConstructor
class OldProduct {
    private Long id;
    private String name;
    private BigDecimal price;
}

@Data
@AllArgsConstructor
abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

class ProductA extends Product {
    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductA{");
        sb.append('}');
        return sb.toString();
    }
}

class ProductB extends Product {
    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductB{");
        sb.append('}');
        return sb.toString();
    }
}

class ProductC extends Product {
    public ProductC(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }


}