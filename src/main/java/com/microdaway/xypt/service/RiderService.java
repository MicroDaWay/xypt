package com.microdaway.xypt.service;

import com.microdaway.xypt.entity.Rider;

import java.util.List;

public interface RiderService {
    void examine(Rider rider);

    Rider get();

    void update(Rider rider);

    List<Rider> awaitExamine();

    void examinePass(Integer id, Integer userId);
}
