package esportes.api.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "vw_users")
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
