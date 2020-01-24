package pl.kamil25k.pcservice.Order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.kamil25k.pcservice.Device.Device;
import pl.kamil25k.pcservice.User.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Device device;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "FINISH_DATE")
    private LocalDateTime finishDate;

    @Column(name = "FINAL_COST")
    private int finalCost;

    @Min(value = 10)
    @Column(name = "EXPECTED_COST")
    private int expectedCost;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order() {
    }

    public Order(Long id, User user, Device device, String description, LocalDateTime startDate, LocalDateTime finishDate, int finalCost, int expectedCost, Status status) {
        this.id = id;
        this.user = user;
        this.device = device;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.finalCost = finalCost;
        this.expectedCost = expectedCost;
        this.status = status;
    }
}
