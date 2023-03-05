package com.cg.service.customer;

import com.cg.model.Withdraws;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;

public interface IWithdrawsService extends IGeneralService<Withdraws> {

    String withdraw(long id, BigDecimal transactionAmount);
}
