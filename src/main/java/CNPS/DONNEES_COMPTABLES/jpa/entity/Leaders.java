package CNPS.DONNEES_COMPTABLES.jpa.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "leaders")
public class Leaders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_leaders", nullable = false )
    private UUID id_leaders;

    @Column(name = "name_leaders")
    private String nameLeaders;

    @Column(name = "first_name_leaders")
    private String firstNameLeaders;

    @Column(name = "fiscal_id_leaders")
    private String fiscalIdLeaders;

    @Column(name = "function_leaders")
    private String functionLeaders;

    @Column(name = "address_leaders")
    private String addressLeaders;

    @Column(name = "nationality_leaders")
    private String nationalityLeaders;

    @ManyToOne
    private Company company;
}