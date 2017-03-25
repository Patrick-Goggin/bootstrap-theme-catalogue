package pgoggin.models;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by patrickgoggin on 3/24/17.
 */
@Entity
@Data
public class CurrentTheme {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column
    String description;

    @Column
    String localUri;

    @Column
    Long currentId;

    @Column
    String cdn;

    @Column
    String color;
}
