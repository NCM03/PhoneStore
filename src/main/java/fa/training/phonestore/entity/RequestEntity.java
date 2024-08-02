package fa.training.phonestore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table(name = "Request")
@Entity
@Data
@NoArgsConstructor
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequestID")
    private int requestID;
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;
    @Column(name = "RequestType")
    private int requestType;
    @Column(name = "Description")
    private String Description;
    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;
    @Column(name = "Answer")
    private String answer;
@Column(name = "Date_created")
    private LocalDate requestDate;
    @Column(name = "Date_Answer")
    private LocalDate answerDate;
    @Column(name = "Title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "InvoiceID")

    private Invoice invoiceID;
    @Column(name = "Status")
    private int status;
    @OneToMany(mappedBy = "request") // "request" phải khớp với tên thuộc tính trong ImageRequest
    private List<ImageRequest> imageRequests;
}
