package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "CustomerID")
    private int customerID;
    @Column(name = "RequestType")
    private int requestType;
    @Column(name = "Description")
    private String Description;
@Column(name = "EmployeeID")
    private Integer employeeID;
    @Column(name = "Answer")
    private String answer;
@Column(name = "Date_created")
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime requestDate;
    @Column(name = "Date_Answer")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime answerDate;
    @Column(name = "Title")
    private String title;
    @JoinColumn(name = "InvoiceID")
    private Integer invoiceID;
    @Column(name = "Status")
    private int  status;

}
