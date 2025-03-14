package repository;

import com.deepsolv.linkedininsights.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    Page findByLinkedinId(String linkedinId);
}