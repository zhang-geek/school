package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.RecordMapper;
import com.dj.ssm.pojo.RecordDto;
import com.dj.ssm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, RecordDto> implements RecordService {
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public void saveRecordData(RecordDto recordDto) {
        recordDto.setCreateTime(new Date());
        recordDto.setRecordTime(new Date());
        recordMapper.insert(recordDto);
    }
}
