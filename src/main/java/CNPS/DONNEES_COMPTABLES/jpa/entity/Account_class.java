package CNPS.DONNEES_COMPTABLES.jpa.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "account_class")
public class AccountClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_account_class", nullable = false)
    private UUID id_account_class;

    @Column(name = "reference_account_class")
    private String referenceAccountClass;

    @Column(name = "lib_account_class")
    private String libAccountClass;

    @Column(name = "parent_account_class")
    private String parentAccountClass;
}