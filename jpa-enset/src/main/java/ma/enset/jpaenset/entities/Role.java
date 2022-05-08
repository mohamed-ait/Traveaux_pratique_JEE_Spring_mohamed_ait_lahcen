package ma.enset.jpaenset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="roles")
@Data @AllArgsConstructor @NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ROLE_NAME",unique = true , length = 20)
    private String roleName;
    @Column(name="description")
    private String desc;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="USERS_ROLES")
    @ToString.Exclude
    private List<User> users=new ArrayList<>();
}
