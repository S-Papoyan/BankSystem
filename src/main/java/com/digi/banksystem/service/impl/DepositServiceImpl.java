package com.digi.banksystem.service.impl;

import com.digi.banksystem.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositService depositService;
}
