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