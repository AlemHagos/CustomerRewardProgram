package rewardprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rewardprogram.model.CustomerRewardProgram;
import rewardprogram.model.ThreeMonthsTotalPoints;
import rewardprogram.repository.CustomerRewardProgramRepository;
import rewardprogram.service.CustomerRewardProgramService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RewardProgramApplication  implements CommandLineRunner {
	@Autowired
	private CustomerRewardProgramRepository programRepository;

	public static void main(String[] args) {
		SpringApplication.run(RewardProgramApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Cleanup database tables.
		programRepository.deleteAll();
		CustomerRewardProgram LuwamJanuaryReward;

		/**
		 * Insert Luwams Data for last 3 months;
		 */
		List<Double> purchases = Arrays.asList(120.0, 100.0, 10.0);
		List<LocalDate> dates = Arrays.asList(LocalDate.of(2022, 01, 01)
				, LocalDate.of(2022, 01, 21), LocalDate.of(2022, 01, 29));
		LuwamJanuaryReward = new CustomerRewardProgram(1l, "Luwam", "Fish",
				purchases, dates);
		LuwamJanuaryReward.print(purchases);
		LuwamJanuaryReward.setMonthlyTotalPoints(LuwamJanuaryReward.getMonthlyTotal());

		programRepository.save(LuwamJanuaryReward);

		CustomerRewardProgram existingLuwamId = programRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("This Id is not found"));

		List<Double> FebruaryPurchase = Arrays.asList(120.0);

		CustomerRewardProgram LuwamFeburaryReward = new CustomerRewardProgram(existingLuwamId.getId(), existingLuwamId.getFirstName(),
				existingLuwamId.getLastName(), FebruaryPurchase, Arrays.asList(LocalDate.of(2020, 02, 01)));
		LuwamFeburaryReward.print(FebruaryPurchase);
		LuwamFeburaryReward.setMonthlyTotalPoints(LuwamFeburaryReward.getMonthlyTotal());

		programRepository.save(LuwamFeburaryReward);

		List<Double> MarchPurchases = Arrays.asList(50.0, 100.0);
		List<LocalDate> MarchPurchaseDates = Arrays.asList(LocalDate.of(2022, 03, 01),
				LocalDate.of(2022, 03, 15), LocalDate.of(2022, 03, 25));

		CustomerRewardProgram LuwamMarchReward = new CustomerRewardProgram(3l, existingLuwamId.getFirstName(),
				existingLuwamId.getLastName(), MarchPurchases, MarchPurchaseDates);
		LuwamMarchReward.print(MarchPurchases);
		LuwamMarchReward.setMonthlyTotalPoints(LuwamMarchReward.getMonthlyTotal());

		programRepository.save(LuwamMarchReward);

//Adding the total three months data
		List<CustomerRewardProgram> Luwam = Arrays.asList(LuwamJanuaryReward, LuwamFeburaryReward, LuwamMarchReward);
		print(Luwam);



	}

	public static void print(List<CustomerRewardProgram> arr) {
		int total = 0;
		for (int i = 0; i < arr.size(); i++) {
			//System.out.println(arr.get(i));
			System.out.println("MonthlyPoints: "+arr.get(i).getMonthlyTotal());
			System.out.println("Total-" + (i + 1) + "-Points: " + (total+= arr.get(i).getMonthlyTotal()));
		}
	}
}