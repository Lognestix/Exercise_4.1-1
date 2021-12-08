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