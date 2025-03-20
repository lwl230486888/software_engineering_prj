package main;
public interface AbstractFactory<T> {
    T create(String type, Object... params);
}
