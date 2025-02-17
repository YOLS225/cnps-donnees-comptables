package CNPS.DONNEES_COMPTABLES.jpa.entity
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "associates")
public class Associates {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_associates", nullable = false )
    private UUID id_associates;

    @Column (name ="name_associates")
    private String nameAssociates;

    @Column(name = "first_name_associates")
    private String firstNameAssociates;

    @Column(name = "function_associates")
    private String functionAssociates;

    @Column(name = "percentage_associates")
    private String percentageAssociates;

    @Column(name = "address_associates")
    private String addressAssociates;

    @Column(name = "nationality_associates")
    private String nationalityAssociates;

    @ManyToOne
    private Company company;
}