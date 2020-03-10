package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.RecordDto;

public interface RecordService extends IService<RecordDto> {

    void saveRecordData(RecordDto recordDto);
}
