package org.ko.multiapi.rest;

import org.ko.multiapi.annot.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/{version}")
public class UserContoller {

    @GetMapping
    public String userDetailV1() {
        return "v1";
    }

    @GetMapping
    @ApiVersion(2)
    public String userDetailV2() {
        return "v2";
    }

}
