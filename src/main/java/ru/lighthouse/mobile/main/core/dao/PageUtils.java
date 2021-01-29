package ru.lighthouse.mobile.main.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;
import java.util.function.Function;

public final class PageUtils {
    public static <T> void forEachAllPages(int pageSize, Function<? super Pageable, ? extends Page<T>> getPage, Consumer<? super T> action) {
        Pageable pageRequest = PageRequest.of(0, pageSize);
        Page<T> page;
        do {
            page = getPage.apply(pageRequest);
            page.get().forEach(action);
            pageRequest = page.nextPageable();
        } while (page.hasNext());
    }
}
