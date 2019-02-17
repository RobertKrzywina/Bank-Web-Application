package pl.robert.project.transactions.query;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class TransactionQuery {

    private static long idTemp = 0;

    private long id;
    private String title;
    private String description;
    private String senderAccountNumber;
    private String receiverAccountNumber;
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
