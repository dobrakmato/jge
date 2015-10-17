package eu.matejkormuth.jge.filesystem;

public interface ResourceLoader<T extends Resource> {

    T load(String path);
}
