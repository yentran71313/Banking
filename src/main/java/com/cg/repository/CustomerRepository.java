package com.cg.repository;

import com.cg.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAllByIdNot(Long id);


    List<Customer> findAllByDeletedFalse();

    List<Customer> findAllByIdNotAndDeletedFalse(Long id);

    List<Customer> findAllByEmail(@NotEmpty String email);

    List<Customer> findAllByPhone(@NotEmpty(message = "Phone is not blank") @Pattern(regexp = "^0[3798][0-9]{8}", message = "Phone is not valid") String phone);
}
