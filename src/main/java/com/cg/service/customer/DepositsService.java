package com.cg.service.customer;

import com.cg.model.Deposits;
import com.cg.repository.DepositsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class DepositsService implements IDepositsService{
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DepositsRepository depositsRepository;
    @Override
    public List<Deposits> findAll() {
        return depositsRepository.findAll();
    }

    @Override
    public Optional<Deposits> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Deposits save(Deposits deposits) {
        return depositsRepository.save(deposits);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Deposits deposits) {

    }

    @Override
    public String deposit(Long id, BigDecimal transactionAmount) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("deposit");
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
