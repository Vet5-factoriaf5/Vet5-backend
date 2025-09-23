package org.factoriaf5.happypaws.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public String index() {
        return "Foo";
    }

}
