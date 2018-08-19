package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Human;
import io.khasang.freefly.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/human")
public class HumanController {

    @Autowired
    private HumanService humanService;

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Human createHuman(@RequestBody Human human) {
        return humanService.addHuman(human);
    }

    @ResponseBody
    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Human getHumanById(@PathVariable(value = "id") long id) {
        return humanService.getById(id);
    }

    @ResponseBody
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public Human deleteHumanById(@PathVariable(value = "id") long id) {
        return humanService.deleteById(id);
    }
}
