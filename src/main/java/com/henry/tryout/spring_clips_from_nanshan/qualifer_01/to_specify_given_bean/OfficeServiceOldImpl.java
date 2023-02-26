package com.henry.tryout.spring_clips_from_nanshan.qualifer_01.to_specify_given_bean;

import org.springframework.stereotype.Service;

@Service("oldOfficeService")
public class OfficeServiceOldImpl implements OfficeService {

    @Override
    public void log() {
        System.out.println("this is previous/old office service.");
    }
}
