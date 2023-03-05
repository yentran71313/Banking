package com.cg.service.customer;

import com.cg.model.Withdraws;
import com.cg.repository.WithdrawsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class WithdrawsService implements IWithdrawsService{
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private WithdrawsRepository withdrawsRepository;
    @Override
    public List<Withdraws> findAll() {
        return null;
    }

    @Override
    public Optional<Withdraws> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Withdraws save(Withdraws withdraws) {
        return withdrawsRepository.save(withdraws);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Withdraws withdraws) {

    }

    @Override
    public String withdraw(long id, BigDecimal transactionAmount) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("withdraws");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, BigDecimal.class,ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.setParameter(1,id);
        query.setParameter(2,transactionAmount);
        query.execute();
        String message = (String) query.getOutputParameterValue(3);
        return message;
    }
}
