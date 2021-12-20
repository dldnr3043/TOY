package com.myproject.toy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * TOY WEB ROOT 컨트롤러
 * 
 * @author 이동욱
 *
 */
@Slf4j
@Controller
@Api(value = "ToyLduWebRootController",
     description = "TOY WEB ROOT 컨트롤러")
public class ToyLduWebRootController {
	/**
     * @return
     */
    @ApiOperation(value = "루트 시작 페이지",
                  notes = "루트 시작 페이지로 이동한다")
    @RequestMapping(value = "/",
                    method = {RequestMethod.GET, RequestMethod.POST})
    public String moveToyWebRootIndex()
    {
        log.debug("moveToyWebRootIndex");
        return "index";
    }
}
