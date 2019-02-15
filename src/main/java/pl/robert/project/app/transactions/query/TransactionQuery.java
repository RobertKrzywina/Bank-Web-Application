package pl.robert.project.app.transactions.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class TransactionQuery {

    private static long idTemp = 0;

    private long id;
    private String title;
    private String description;
    private String number;
    private LocalDateTime date;
    private double amount;

    public TransactionQuery(String title, String description, String number, LocalDateTime date, double amount) {
        this(++idTemp, title, description, number, date, amount);
    }

    private TransactionQuery(long id, String title, String description, String number, LocalDateTime date, double amount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.number = number;
        this.date = date;
        this.amount = amount;
    }

    public static void setIdTemp(long idTemp) {
        TransactionQuery.idTemp = idTemp;
    }
}
