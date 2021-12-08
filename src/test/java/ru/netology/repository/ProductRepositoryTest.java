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