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
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_bank", nullable = false)
    private UUID idBank;

    @Column(name = "name")
    private String name;

    @Column(name = "windows_code")
    private String windowsCode;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "key_rib")
    private String keyRib;

    @Column(name = "iban")
    private String iban;

    @ManyToOne
    private Company company;
}
