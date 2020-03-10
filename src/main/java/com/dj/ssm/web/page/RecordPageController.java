package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.pojo.RecordDto;
import com.dj.ssm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/record/")
public class RecordPageController {

    @Autowired
    private RecordService recordService;

    /**
     * 展示校园卡消费记录
     */
    @GetMapping("getRecordList")
    public String getRecordList(Model model, Integer id){
        QueryWrapper<RecordDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        List<RecordDto> list = recordService.list(queryWrapper);
        model.addAttribute("list", list);
        return "";
    }

}
