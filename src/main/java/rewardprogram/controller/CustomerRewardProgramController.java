package rewardprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rewardprogram.model.CustomerRewardProgram;
import rewardprogram.service.CustomerRewardProgramService;

import java.util.List;

@RestController
@RequestMapping(value = {"/customers"})
public class CustomerRewardProgramController {

@Autowired
private CustomerRewardProgramService programService;

    //public CustomerRewardProgramController(CustomerRewardProgramService programService) {
     //   this.programService = programService;
   // }

        @GetMapping("/")
        public ResponseEntity<List<CustomerRewardProgram>> getAllCustomerRewardsProgram() {

            return  new ResponseEntity<>( programService.getAllCustomerRewards(), HttpStatus.OK);
        }
        @GetMapping(value = {"/{customerId}"})
        public ResponseEntity<?> getCustomerRewardsById(@PathVariable("customerId") Long customerId) {

            return new ResponseEntity<>(programService.getCustomerRewardProgramBy_Id(customerId), HttpStatus.OK);
        }

    @PostMapping("/create")
    // @ResponseStatus()
    public ResponseEntity<?> createCustomerRewardProgram(@RequestBody CustomerRewardProgram customerRewardProgram){
        return  new ResponseEntity<>( programService.addNewCustomerReward(customerRewardProgram), HttpStatus.CREATED);
    }
    @PutMapping("/update/{customerId}")
    // @ResponseStatus()
    public ResponseEntity<?> updateCustomerRewardProgram(@PathVariable("customerId") Long customerId,@RequestBody CustomerRewardProgram customerRewardProgram){
        return  new ResponseEntity<>( programService.updateRegistrationEvent(customerId,customerRewardProgram), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{customerId}"})
    public ResponseEntity<?> deleteCustomerRewardsById(@PathVariable("customerId") Long customerId) {

        return new ResponseEntity<>( HttpStatus.NO_CONTENT);

    }
}
