package kz.batyrbek.palaumen.palaumen.repository;

import kz.batyrbek.palaumen.palaumen.model.ExtraDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface ExtraDetailRepository extends JpaRepository<ExtraDetail, Long> {
}
