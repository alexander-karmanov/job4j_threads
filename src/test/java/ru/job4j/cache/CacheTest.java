package ru.job4j.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CacheTest {

    private Cache cache;

    @BeforeEach
    void setUp() {
        cache = new Cache();
    }

    @Test
    public void whenAddFind() throws OptimisticException {
        var base = new Base(1,  "Base", 1);
        cache.add(base);
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base");
    }

    @Test
    public void whenAddUpdateFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        cache.add(base);
        cache.update(new Base(1, "Base updated", 1));
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base updated");
    }

    @Test
    public void whenAddDeleteFind() throws OptimisticException {
        var base = new Base(1,   "Base", 1);
        cache.add(base);
        cache.delete(1);
        var find = cache.findById(base.id());
        assertThat(find.isEmpty()).isTrue();
    }

    @Test
    public void whenMultiUpdateThrowException() throws OptimisticException {
        var base = new Base(1,  "Base", 1);
        cache.add(base);
        cache.update(base);
        assertThatThrownBy(() -> cache.update(base))
                .isInstanceOf(OptimisticException.class);
    }

    @Test
    public void whenFindByIdExistingModelThenReturnModel() throws OptimisticException {
        var base = new Base(1, "Base", 0);
        cache.add(base);
        assertThat(cache.findById(1).isPresent()).isTrue();
    }

    @Test
    public void whenFindByIdNonExistingModelThenReturnEmpty() {
        assertThat(cache.findById(1).isEmpty());
    }
}
