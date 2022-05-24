package com.mantas.tapd.ext.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
@EqualsAndHashCode(of = {"user"})
public class Worker {

     private String user;
     private String name;
     private String email;
     private List<String> roles;
     private Set<Trace> traces = new HashSet<>();
}
