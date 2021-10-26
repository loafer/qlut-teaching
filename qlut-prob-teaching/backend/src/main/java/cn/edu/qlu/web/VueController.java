package cn.edu.qlu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <描述信息>
 *
 * @author
 */
@Controller
public class VueController {
    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public String forward() {
        // Forward to home page so that route is preserved.
        return "forward:/";
    }
}
