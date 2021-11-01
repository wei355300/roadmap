package com.mantas.tapd.ext.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class WorkerTrace extends Worker {

    private List<Trace> traces = new ArrayList<>();

    public WorkerTrace(Worker worker) {
        setUser(worker.getUser());
        setName(worker.getName());
        setEmail(worker.getEmail());
        setRoles(worker.getRoles());
    }
}
