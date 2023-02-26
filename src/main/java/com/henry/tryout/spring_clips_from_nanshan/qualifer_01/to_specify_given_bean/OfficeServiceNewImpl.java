package com.henry.tryout.spring_clips_from_nanshan.qualifer_01.to_specify_given_bean;

import org.springframework.stereotype.Service;

@Service("newOfficeService")
public class OfficeServiceNewImpl implements OfficeService{
    @Override
    public void log() {
        System.out.println("this is new office service.");
    }
}
