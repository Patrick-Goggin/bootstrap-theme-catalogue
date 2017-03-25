package pgoggin.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by patrickgoggin on 3/24/17.
 */
@Repository
public interface CurrentThemeRepository extends JpaRepository<CurrentTheme, Long> {

}
