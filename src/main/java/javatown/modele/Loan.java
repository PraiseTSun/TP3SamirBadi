package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_client")
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "fk_document")
    private AbstractDocument document;

    @Basic
    private LocalDate dateOfLoan;
    @Basic
    private LocalDate dateOfReturn;

    public Loan(Client client, AbstractDocument document, String dateOfLoan) {
        this.client = client;
        this.document = document;
        this.dateOfLoan = LocalDate.parse(dateOfLoan);
        this.dateOfReturn = LocalDate.parse(returnDate(dateOfLoan, document.loanTimeInDay()));
    }

    private String returnDate(String date, int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myDate = addDays(myDate, days);
        return format.format(myDate);
    }

    private Date addDays(Date myDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public boolean isLate(String dateOfReturn) {
        return nbDaysLate(dateOfReturn) > 0;
    }

    public int nbDaysLate(String dateOfReturn){
        LocalDate from = this.dateOfReturn;
        LocalDate to = LocalDate.parse(dateOfReturn);
        Period period = Period.between(from, to);
        int late = period.getYears() * 365 + period.getMonths() * 30 + period.getDays();
        return late;
    }
}
