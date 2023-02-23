package rewardprogram.model;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Entity
public class CustomerRewardProgram {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="customer_purchases",joinColumns = @JoinColumn(name = "customer_id"))
    private List<Double> purchases;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="customer_dates",joinColumns = @JoinColumn(name = "customer_id"))
    private List<LocalDate> dates;
    private  Double monthlyTotalPoints;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name="customer_total3months",joinColumns = @JoinColumn(name = "customer_id"))
//    @OneToOne
//    public ThreeMonthsTotalPoints total3MonthsPoints;


    public CustomerRewardProgram(Long id,String firstName, String lastName, List<Double> purchases,
                                 List<LocalDate> dates) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.purchases = purchases;
        this.dates = dates;
      this. monthlyTotalPoints = 0.0;

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Double> getPurchases() {
        return purchases;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public double calculatePoints(double purchase) {

        if (purchase <= 50) {
            return 0;
        } else if (purchase <= 100) {
            return (purchase - 50);
        } else {
            return (purchase - 100) * 2 + 50;
        }
    }

    public String print(List<Double> purchases) {
        String add = "";
        double points = 0;
        for (int i = 0; i < purchases.size(); i++) {
            //we get every transaction
            points = calculatePoints(purchases.get(i));

            add += "\nPoints for $" + (purchases.get(i)) + " is: " + points;// +"sum monthlu"+ ;
            //I sum all per transaction
            monthlyTotalPoints += points;
        }
        return add;
    }

    public Double getMonthlyTotal() {
        return monthlyTotalPoints;
    }

    //    @Override
//    public String toString() {
//        return "[CustomerName: " + firstName + " "+lastName+
//                "\nPurchase: " + purchases +
//                "\nDate: " + dates +
//                "\n[Points: " + print(purchases) + "]" + "\nMonthlyTotal: " + monthlyTotalPoints + "]";
//
//    }
}