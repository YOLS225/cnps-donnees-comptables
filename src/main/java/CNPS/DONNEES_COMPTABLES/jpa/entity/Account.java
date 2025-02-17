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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "label")
    private String label;

    @Column(name = "type")
    private String type;

    @Column(name = "type")
    private String parentAccount;

    @Column(name = "type")
    private String status;

    @ManyToOne
    private AccountingPlan accountingPlan;

    @ManyToOne
    private AccountClass accountingClass;
}
