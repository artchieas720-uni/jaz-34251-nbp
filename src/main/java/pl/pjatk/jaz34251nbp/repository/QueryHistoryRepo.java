package pl.pjatk.jaz34251nbp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.jaz34251nbp.model.QueryHistory;

@Repository
public interface QueryHistoryRepo extends JpaRepository<QueryHistory, Long> {
}