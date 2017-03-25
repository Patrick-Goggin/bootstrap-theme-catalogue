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
    String localUri;

    @Column
    String css;
    public Theme (){
        this.name = name;
        this.description = description;
        this.cdn = cdn;
        this.localUri = localUri;
    }
    public Theme(String name, String description, String cdn){
        this.name = name;
        this.description = description;
        this.cdn = cdn;
        this.localUri = localUri;
    }
}
