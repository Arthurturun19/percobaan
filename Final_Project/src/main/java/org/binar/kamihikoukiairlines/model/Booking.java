package org.binar.kamihikoukiairlines.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Users users;
    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;
    private String bookingCode;
    private String paymentMethod;
    private LocalDateTime dueValid;
    private Boolean isSuccess;
    private Boolean isValid;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    @Column(name = "passenger")
    private List<Passenger> passengersList = new ArrayList<>();*/
}
