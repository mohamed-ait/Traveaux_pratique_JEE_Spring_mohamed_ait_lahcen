package ma.enset.pateintsmvc.security.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="USERS")
public class User {
    @Id
    private String userId;
    @Column(name="USER_NAME",unique = true, length =20 )
    private String userName;
    private String password;
    private boolean active;
    @ManyToMany( fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();
}