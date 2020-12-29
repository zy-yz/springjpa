package com.peppers.exam.service.label;


import com.peppers.exam.entity.label.Label;
import com.peppers.exam.repository.label.LabelRepository;


public class LabelApplicationService<T extends Label> {
    public LabelApplicationService(LabelRepository<T> repository){
    }
}
