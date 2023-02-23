package rewardprogram.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rewardprogram.model.CustomerRewardProgram;
import rewardprogram.repository.CustomerRewardProgramRepository;
import rewardprogram.service.CustomerRewardProgramService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerRewardProgramServiceImpl implements CustomerRewardProgramService {

    @Autowired
    private CustomerRewardProgramRepository programRepository;

    @Override
    public CustomerRewardProgram addNewCustomerReward(CustomerRewardProgram rewardProgram) {
        List<Double> purchase = rewardProgram.getPurchases();
        double total = 0;
        for ( Double result : purchase  ) {
            if (result <= 50) {
                total += 0;
            } else if (result <= 100) {
                total += (result - 50);
            } else {
                total += (result - 100) * 2 + 50;
            }
        }
        rewardProgram.setMonthlyTotalPoints(total);
        return programRepository.save(rewardProgram);
    }

    @Override
    public List<CustomerRewardProgram> getAllCustomerRewards() {
        return programRepository.findAll();
        //return programRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    @Override
    public CustomerRewardProgram getCustomerRewardProgramBy_Id(Long customerRewardId) {
        return programRepository.findById(customerRewardId).orElseThrow(()-> new IllegalArgumentException("The id is not found"));
    }

    @Override
    public CustomerRewardProgram updateRegistrationEvent(Long customerRewardId, CustomerRewardProgram rewardProgram) {
        Optional<CustomerRewardProgram> exsitingId = programRepository.findById(customerRewardId);//.orElseThrow(()->new IllegalArgumentException("The id is not found"));
        CustomerRewardProgram newCustomer = null;
        if (exsitingId.isPresent()) {
            rewardProgram.setId(rewardProgram.getId());
            rewardProgram.setFirstName(rewardProgram.getFirstName());
            rewardProgram.setLastName(rewardProgram.getLastName());
            rewardProgram.setMonthlyTotalPoints(rewardProgram.getMonthlyTotal());
            programRepository.save(rewardProgram);
            return rewardProgram;
        } else {
            throw new IllegalArgumentException("Id is not found");
        }
    }
    @Override
    public void deleteById(Long customerRewardId) {
  programRepository.deleteById(customerRewardId);
    }
}
