package pgoggin.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by patrickgoggin on 3/4/17.
 */

@Repository
public interface ThingRepository extends JpaRepository<Thing, Long> {

}
