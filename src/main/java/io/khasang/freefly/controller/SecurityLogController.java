package io.khasang.freefly.controller;

import io.khasang.freefly.entity.SecurityLog;
import io.khasang.freefly.service.SecurityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/securitylog")
public class SecurityLogController {
    private final SecurityLogService securityLogService;

    @Autowired
    public SecurityLogController(SecurityLogService securityLogService) {
        this.securityLogService = securityLogService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public SecurityLog addSecurityLog(@RequestBody SecurityLog securityLog) {
        return securityLogService.addSecurityLog(securityLog);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public SecurityLog getSecurityLogById(@PathVariable(value = "id") String id) {
        return securityLogService.getSecurityLogById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public void deleteSecurityLogById(@PathVariable(value = "id") String id) {
        securityLogService.deleteSecurityLogById(Long.parseLong(id));
    }
}
