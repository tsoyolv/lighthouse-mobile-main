package ru.lighthouse.mobile.main.repository.flat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.lighthouse.mobile.main.repository.flat.entity.FlatImage;

import java.util.List;

public interface FlatImageRepository extends JpaRepository<FlatImage, Long>, JpaSpecificationExecutor<FlatImage> {
    List<FlatImage> getAllByFlat_Id(Long flatId);
}
