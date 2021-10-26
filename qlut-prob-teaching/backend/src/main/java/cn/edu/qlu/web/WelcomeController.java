package cn.edu.qlu.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <描述信息>
 *
 * @author
 */
@RestController
public class WelcomeController {
    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome Spring fans";
    }
}
