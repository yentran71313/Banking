package com.cg.service.customer;


import com.cg.model.Customer;
import com.cg.service.IGeneralService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    List<Customer> findAllByIdNot(Long id);

    List<Customer> findAllByIdNotAndDeletedFalse(Long id);

    List<Customer> findAllByDeletedFalse();

    List<Customer> findAllByEmail(@NotEmpty String email);

    List<Customer> findAllByPhone(@NotEmpty(message = "Phone is not blank") @Pattern(regexp = "^0[3798][0-9]{8}", message = "Phone is not valid") String phone);
}
