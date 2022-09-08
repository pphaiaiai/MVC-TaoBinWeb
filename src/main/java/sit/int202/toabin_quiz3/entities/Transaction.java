package sit.int202.toabin_quiz3.entities;

import jakarta.persistence.*;
import lombok.*;

//63130500055 Tarathep Siripis


import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tran_date", nullable = false)
    private Date tranDate;

    @NonNull
    @Column(name = "qty", nullable = false)
    private Integer qty;

    @NonNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NonNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accounts_mobile_no", nullable = false)
    private Account accountsMobileNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    public Integer getId() {
        return id;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public Integer getQty() {
        return qty;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public Account getAccountsMobileNo() {
        return accountsMobileNo;
    }

    public Drink getDrink() {
        return drink;
    }
}