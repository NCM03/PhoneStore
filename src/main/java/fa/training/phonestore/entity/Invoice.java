package fa.training.phonestore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceID")
    private Integer invoiceId;
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;
    @Column(name = "InvoiceType", nullable = false)
    private Integer invoiceType;

    @Column(name = "InvoiceDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @Column(name = "incurred", nullable = false, precision = 10, scale = 2)
    private double incurred;

    @Column(name = "incurredDescription", length = 2000)
    private String incurredDescription;
}

