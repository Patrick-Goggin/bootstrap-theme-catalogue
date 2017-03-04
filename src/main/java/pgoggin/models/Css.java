package pgoggin.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by patrickgoggin on 3/2/17.
 */
@Entity
@Data
public class Css {
    @Id
    @GeneratedValue
    Long id;

    @Column
    Long themeId;

    @Column
    String name;

    @Column
    String css;
}
