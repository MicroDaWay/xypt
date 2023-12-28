package com.microdaway.xypt.service.impl;

import com.microdaway.xypt.entity.Rider;
import com.microdaway.xypt.mapper.RiderMapper;
import com.microdaway.xypt.service.RiderService;
import com.microdaway.xypt.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RiderServiceImpl implements RiderService {
    @Autowired
    private RiderMapper riderMapper;

    @Override
    public void examine(Rider rider) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        rider.setUserId(userId);
        rider.setExamineState(0);
        riderMapper.examine(rider);
    }

    @Override
    public Rider get() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Rider rider = riderMapper.get(userId);
        return rider;
    }

    @Override
    public void update(Rider rider) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        rider.setUserId(userId);
        riderMapper.update(rider);
    }

    @Override
    public List<Rider> awaitExamine() {
        List<Rider> riderList = riderMapper.awaitExamine();
        return riderList;
    }

    @Override
    public void examinePass(Integer id, Integer userId) {
        riderMapper.examinePass(id);
        riderMapper.updateRole(userId);
    }
}
