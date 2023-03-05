package com.cg.service.customer;

import com.cg.model.Deposits;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;

public interface IDepositsService extends IGeneralService<Deposits> {
    String deposit(Long id, BigDecimal transactionAmount);
}
