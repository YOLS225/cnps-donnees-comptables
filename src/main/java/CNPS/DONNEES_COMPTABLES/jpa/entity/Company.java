package CNPS.DONNEES_COMPTABLES.jpa.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "activity")
    private String activity;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @Column(name = "capital")
    private String capital;

    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "country")
    private String country;

    @Column(name = "geographic_address")
    private String geographicAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "control_entity")
    private String controlEntity;

    @Column(name = "address_signatory")
    private String addressSignatory;

    @Column(name = "website")
    private String webSite;

    @Column(name = "function_signatory")
    private String functionSignatory;

    @Column(name = "address_contact_person")
    private String addressContactPerson;

    @Column(name = "ncc")
    private String ncc;

    @Column(name = "function_contact_person")
    private String functionContactPerson;

    @Column(name = "name_signatory")
    private String nameSignatory;

    @Column(name = "importator_code")
    private String importatorCode;

    @Column(name = "nrc")
    private String nrc;

    @Column(name = "name_contact_person")
    private String nameContactPerson;

    @Column(name = "code_main_activity")
    private String codeMainActivity;

    @Column(name = "teledeclarant_number")
    private String teleDeclarantNumber;

    @Column(name = "country_head_office")
    private String countryHeadOffice;

    @Column(name = "registry")
    private String registry;

    @Column(name = "juridic_form")
    private String juridicForm;

    @Column(name = "number_establishement_in")
    private String numberEstablishementIn;

    @Column(name = "start_exercice")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startExercice;

    @Column(name = "number_establishement_out")
    private String numberEstablishementOut;

    @Column(name = "fiscal_row")
    private String fiscalRow;

    @Column(name = "logo")
    private String logo;
}
