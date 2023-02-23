package rewardprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rewardprogram.model.CustomerRewardProgram;

@Repository
public interface CustomerRewardProgramRepository extends  JpaRepository<CustomerRewardProgram,Long> {


}
