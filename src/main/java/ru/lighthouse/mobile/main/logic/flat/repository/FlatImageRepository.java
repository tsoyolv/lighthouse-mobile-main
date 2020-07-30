package ru.lighthouse.mobile.main.logic.flat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lighthouse.mobile.main.logic.flat.entity.FlatImage;

import java.util.List;

public interface FlatImageRepository extends JpaRepository<FlatImage, Long> {
    Page<FlatImage> findAllByFilePathIsNull(Pageable pageable);
    List<FlatImage> getAllByFlatId(Long flatId);
}
