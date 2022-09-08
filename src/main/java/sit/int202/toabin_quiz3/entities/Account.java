package sit.int202.toabin_quiz3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;

//63130500055 Tarathep Siripis

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @NonNull
    @Column(name = "mobile_no", nullable = false, length = 10)
    private String mobileNo;

    @NonNull
    @Column(name = "pin_code", nullable = false, length = 100)
    private String pinCode;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_buy")
    private Date lastBuy;

    @NonNull
    @Column(name = "total_amount", nullable = false)
    private int totalAmount;

    @OneToMany(mappedBy = "accountsMobileNo")
    private List<Transaction> transactions = new ArrayList<>();
}