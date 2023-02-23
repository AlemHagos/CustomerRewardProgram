package rewardprogram.service;

import rewardprogram.model.CustomerRewardProgram;

import java.util.List;

public interface CustomerRewardProgramService {

    CustomerRewardProgram addNewCustomerReward(CustomerRewardProgram rewardProgram);
    List<CustomerRewardProgram> getAllCustomerRewards();

    CustomerRewardProgram getCustomerRewardProgramBy_Id(Long customerRewardId);

    CustomerRewardProgram updateRegistrationEvent(Long customerRewardId, CustomerRewardProgram rewardProgram);
    void deleteById(Long customerRewardId);
}
