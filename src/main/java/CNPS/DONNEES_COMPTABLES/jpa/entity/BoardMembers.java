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
@Table(name = "board_members")
public class BoardMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_members", nullable = false )
    private UUID id_members;

    @Column (name ="name_members")
    private String nameMembers;

    @Column(name = "first_name_members")
    private String firstNameMembers;

    @Column(name = "structure_members")
    private String structureMembers;

    @Column(name = "percentage_members")
    private String percentageMembers;

    @Column(name = "address_members")
    private String addressMembers;

    @Column(name = "nationality_members")
    private String nationalityMembers;

    @ManyToOne
    private Company company;
}