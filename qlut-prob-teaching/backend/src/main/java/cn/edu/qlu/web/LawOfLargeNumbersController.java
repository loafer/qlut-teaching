package cn.edu.qlu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <描述信息>
 *
 * @author
 */
@RestController
@RequestMapping("/t1")
public class LawOfLargeNumbersController {
    private static final Logger logger = LoggerFactory.getLogger(LawOfLargeNumbersController.class);

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("t1");
    }

    @RequestMapping("/dataset")
    public List dataSet(int n) {
        List<Foo> list = new ArrayList<>(n);

        LocalDateTime start = LocalDateTime.now();

        for (int i=1; i<=n; i++){
            list.add(new Foo(i, buildY(i)));
        }

        LocalDateTime now = LocalDateTime.now();
        logger.info("{}", Duration.between(start, now).getSeconds());
        return list;
    }

    private Stream<Integer> shuffle(Stream<Integer> stream) {
        return stream.sorted(Comparator.comparing(x -> 0.5 - Math.random()));
    }

    private int buildY(int n) {
        List<Integer> seeds = Arrays.asList(0, 100, 200, 300, 400, 500, 600);
        Supplier<Integer> yi = () -> 3000 - shuffle(seeds.stream()).limit(5).reduce((x, y) -> x + y).get();

        int[] tmp = new int[n];
        IntStream.range(0, n).forEach(i -> tmp[i] = yi.get());
        return Arrays.stream(tmp).sum() / n;
    }

    public static class Foo {
        private final int x;
        private final int y;

        public Foo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
