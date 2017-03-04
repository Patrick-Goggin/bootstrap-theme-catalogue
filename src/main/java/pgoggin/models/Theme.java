package pgoggin.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by patrickgoggin on 3/2/17.
 */

@Entity
@Data
public class Theme {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @Column
    String description;

    @Column
    String cdn;

    @Column
    String originalUri;

    @Column
    String provider;

    @Column
    String website;

    @Column
    String localUri;

    @Column
    String price;

    @Column
    Date dateAdded;

    @Column
    String addedBy;

    @Column
    String css;
}
