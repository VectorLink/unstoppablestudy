package com.unstoppable.unstoppablestudy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class NormalTest {
    public static void main(String[] args) {
        Function<String,Integer> function= Integer::valueOf;
        Function<String, BigDecimal> stringBigDecimalFunction = function.andThen(BigDecimal::new);
        BigDecimal apply = stringBigDecimalFunction.apply("99");
        Assert.isTrue(apply.compareTo(BigDecimal.valueOf(99L))==0,"不相同");

        List<String> li = Arrays.asList("1","2","3","3");

        double sum = li.stream().mapToDouble(Double::valueOf).sum();
        log.info("总数：{}",sum);




    }


    public <E> E getValueTest(E e){
        return e;
    }
}
