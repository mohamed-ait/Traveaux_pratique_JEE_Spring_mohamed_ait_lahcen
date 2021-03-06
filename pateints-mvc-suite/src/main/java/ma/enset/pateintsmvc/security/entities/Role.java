package ma.enset.pateintsmvc.security.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="ROLES")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ROLE_NAME",unique = true , length = 20)
    private String roleName;
    private String description;
}
