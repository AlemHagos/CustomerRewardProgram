package rewardprogram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThreeMonthsTotalPoints {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToOne
    private CustomerRewardProgram program;


    public  void addThreeMonthsPoint(List<CustomerRewardProgram> arr) {
        int total = 0;
        for (int i = 0; i < arr.size(); i++) {
            //System.out.println(arr.get(i));
           // System.out.println("MonthlyPoints: " + arr.get(i).getMonthlyTotal());
            System.out.println("Total-" + (i + 1) + "-Points: " + (total += arr.get(i).getMonthlyTotal()));
        }
    }
}
