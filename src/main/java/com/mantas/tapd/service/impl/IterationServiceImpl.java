package com.mantas.tapd.service.impl;

import com.mantas.tapd.dto.Iteration;
import com.mantas.tapd.service.IterationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class IterationServiceImpl implements IterationService {

    @Override
    public List<Iteration> list(Date startDate) {
        return null;
    }
}
