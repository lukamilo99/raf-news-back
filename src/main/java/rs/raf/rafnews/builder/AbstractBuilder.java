package rs.raf.rafnews.builder;

public abstract class AbstractBuilder<T>{

    protected T object;

    public AbstractBuilder() {
        object = createObject();
    }

    protected abstract T createObject();

    public T build() {
        return object;
    }
}
