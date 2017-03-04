package pgoggin.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by patrickgoggin on 3/2/17.
 */
@Repository
public interface ThemeRepository  extends JpaRepository<Theme, Long> {

}
