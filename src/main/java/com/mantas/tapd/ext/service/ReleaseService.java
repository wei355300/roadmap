package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Release;

import java.util.List;

public interface ReleaseService {

    List<Release> list(String day);
}
