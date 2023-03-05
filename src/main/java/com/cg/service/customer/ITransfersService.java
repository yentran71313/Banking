package com.cg.service.customer;

import com.cg.model.Transfers;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;

public interface ITransfersService extends IGeneralService<Transfers> {
    String transfer(long senderId, long recipientId, BigDecimal money);
}
