package esportes.api.domain.user;

import javax.persistence.*;
import lombok.*;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "vw_users")
//@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long empresaId;
    private String name;
    private String email;
    private String password;
}
