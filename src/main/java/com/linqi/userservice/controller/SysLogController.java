package com.linqi.userservice.controller;

import com.linqi.userservice.common.beans.PageQuery;
import com.linqi.userservice.common.response.ResultVO;
import com.linqi.userservice.common.response.ResultVOUtil;
import com.linqi.userservice.param.SearchLogParam;
import com.linqi.userservice.service.SysLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    @RequestMapping("/log.page")
    public ModelAndView page() {
        return new ModelAndView("log");
    }

    @RequestMapping("/recover.json")
    @ResponseBody
    public ResultVO recover(@RequestParam("id") int id) {
        sysLogService.recover(id);
        return ResultVOUtil.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public ResultVO searchPage(SearchLogParam param, PageQuery page) {
        return ResultVOUtil.success(sysLogService.searchPageList(param, page));
    }
}
