# Настройки добавленные в pom.xml для данного проекта
```xml
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <failIfNoTests>true</failIfNoTests>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.5</version>
                <executions>
                    <execution>
                        <id>agent-Smith</id>
                        <phase>initialize</phase>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report-agent-Smith</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>3.6.28</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```
# Код Java находящийся в этом репозитории
```Java
package ru.netology.domain;

import java.util.Objects;

public class Product {
  private int id;
  private String name;
  private int price;

  public Product() {
  }

  public Product(int id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id == product.id &&
        price == product.price &&
        Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price);
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        '}';
  }
}
```
```Java
package ru.netology.domain;

import java.util.Objects;

public class Book extends Product {
  private String author;

  public Book() {
    super();
  }

  public Book(int id, String name, int price, String author) {
    super(id, name, price);
    this.author = author;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Book book = (Book) o;
    return Objects.equals(author, book.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), author);
  }

  @Override
  public String toString() {
    return "Book{" +
        "author='" + author +
        '}';
  }
}
```
```Java
package ru.netology.domain;

import java.util.Objects;

public class TShirt extends Product {
  private String color;

  public TShirt() {
    super();
  }

  public TShirt(int id, String name, int price, String color) {
    super(id, name, price);
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    TShirt shirt = (TShirt) o;
    return Objects.equals(color, shirt.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), color);
  }

  @Override
  public String toString() {
    return "TShirt{" +
            "color='" + color +
            '}';
  }
}
```
```Java
package ru.netology.manager;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.repository.ProductRepository;

public class ProductManager {
  //Добавление необходимыех полей, конструкторов и методов
  private ProductRepository repository;

  public ProductManager(ProductRepository repository) { this.repository = repository; }

  //Добавление продукта в репозиторий
  public void addProduct(Product item) { repository.save(item); }

  public Product[] searchBy(String text) {
    Product[] products = repository.findAll();
    Product[] result = new Product[0];
    for (Product product : products) {
      if (matches(product, text)) {
        int length = result.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(result, 0, tmp, 0, result.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;
        result = tmp;
      }
    }
    return result;
  }

  public boolean matches(Product product, String search) {
    if (product.getName().contains(search)) { //Проверка на наличие поискового слова в данных о названии
      return true;
    }
    if (product instanceof Book) { //Если в параметре product лежит объект класса Book,
      Book book = (Book) product; //то он ложится в переменную типа Book чтобы пользоваться методами класса Book.
      if (book.getAuthor().contains(search)) { //Проверка на наличие поискового слова в данных об авторе
        return true;
      }
    }
    if (product instanceof TShirt) { //Если в параметре product лежит объект класса Smartphone,
      TShirt tShirt = (TShirt) product; //то он ложится в переменную типа Smartphone чтобы пользоваться методами класса Smartphone.
      if (tShirt.getColor().contains(search)) { //Проверка на наличие поискового слова в данных о цвете
        return true;
      }
    }
    return false;
  }
}
```
```Java
package ru.netology.domain;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
```
```Java
package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

public class ProductRepository {
  private Product[] items = new Product[0];

  public void save(Product item) {
    int length = items.length + 1;
    Product[] tmp = new Product[length];
    System.arraycopy(items, 0, tmp, 0, items.length);
    int lastIndex = tmp.length - 1;
    tmp[lastIndex] = item;
    items = tmp;
  }

  public Product[] findAll() {
    return items;
  }

  public Product findById(int id) {
    for (Product item : items) {
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }

  public void removeById(int id) {
    if (findById(id) == null) {
      throw new NotFoundException(
              "Element with id: " + id + " not found"
      );
    }
    int length = items.length - 1;
    Product[] tmp = new Product[length];
    int index = 0;
    for (Product item : items) {
      if (item.getId() != id) {
        tmp[index] = item;
        index++;
      }
    }
    items = tmp;
  }
}
```
```Java
package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    //Общие данные:
    private final ProductRepository repository = new ProductRepository();

    private final Book zero = new Book(0, "Одиссея капитана Блада", 3_700, "Рафаэль Сабатини");
    private final Book first = new Book(1, "Час Быка", 3_200, "Иван Ефремов");
    private final Book second = new Book(2, "Изгой", 3_500, "Руслан Михайлов");
    private final Book third = new Book(3, "Лабиринт отражений", 3_400, "Сергей Лукьяненко");

    private final TShirt fourth = new TShirt(4, "Поло", 1_200, "Желтый");
    private final TShirt fifth = new TShirt(5, "Классическая", 1_500, "Синий");
    private final TShirt sixth = new TShirt(6, "Хенли", 1_300, "Салатовый");
    private final TShirt seventh = new TShirt(7, "Классическая", 1_100, "Белый");

    @BeforeEach
    public void setUp() {
        repository.save(zero);
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        repository.save(seventh);
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(6);
        Product[] expected = {zero, first, second, third, fourth, fifth, seventh};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdException() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(8);
        });
    }
}
```