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
public class BoardMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_members", nullable = false )
    private UUID id;

    @Column (name ="lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "function")
    private String function;

    @Column(name = "structure")
    private String structure;

    @Column(name = "percentage")
    private Float percentage;

    @Column(name = "address")
    private String address;

    @Column(name = "nationality")
    private String nationality;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Company company;
}