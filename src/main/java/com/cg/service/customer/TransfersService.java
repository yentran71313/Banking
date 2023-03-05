package com.cg.service.customer;

import com.cg.model.Transfers;
import com.cg.repository.TransfersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class TransfersService implements ITransfersService{
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransfersRepository transfersRepository;
    @Override
    public List<Transfers> findAll() {
        return transfersRepository.findAll();
    }

    @Override
    public Optional<Transfers> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfers save(Transfers transfers) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Transfers transfers) {

    }

    @Override
    public String transfer(long senderId, long recipientId, BigDecimal money) {
        StoredProcedureQuery query =entityManager.createStoredProcedureQuery("transfers");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);

        query.registerStoredProcedureParameter(4,String.class, ParameterMode.OUT);
        query.setParameter(1,senderId);
        query.setParameter(2,money);
        query.setParameter(3, recipientId);

        query.execute();
        String message = (String) query.getOutputParameterValue(4);
        return message;
    }
}
