package com.mantas.tapd.service;

import com.mantas.tapd.dto.Release;

import java.util.List;

public interface ReleaseService {

    List<Release> list(String day);
}
