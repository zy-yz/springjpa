package com.peppers.exam.utils.lambda;


import com.peppers.exam.entity.baseeneity.BaseEntity;
import com.peppers.exam.entity.role.SysRole;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static net.bytebuddy.implementation.FixedValue.nullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author peppers
 * @description Java8功能
 * 有关 Optional 可空类型
 * @since 2021/1/11
 **/
public class OptionalTest {

    public static void main(String[] args) {
        //optionalTest();
       // getSysRoleANdCacheCool(1L);
        patternTest();

    }


    /**
     * optional通用操作
     * */
    public static void optionalTest() {
        //通过get方法获取Optional中的实际值
        assertThat(Optional.of(1).get(), is(1));
        //通过ofNullable来初始化一个null，通过orElse方法实现Optional中无数据的时候返回一个默认值
        assertThat(Optional.ofNullable(null).orElse("A"), is("A"));
        //OptionalDouble是基本类型double的Optional对象，isPresent判断有无数据
        assertFalse(OptionalDouble.empty().isPresent());
        //通过map方法可以对Optional对象进行级联转换，不会出现空指针，转换后还是一个Optional
        assertThat(Optional.of(1).map(Math::incrementExact).get(), is(2));
        //通过filter实现Optional中数据的过滤，得到一个Optional，然后级联使用orElse提供默认值
        assertThat(Optional.of(1).filter(integer -> integer % 2 == 0).orElse(null), is(nullValue()));
        //通过orElseThrow实现无数据时抛出异常
        Optional.empty().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * 并行流操作
     * 通过 parallel 方法，一键把 Stream 转换为并行操作提交到线程池处理。
     * */
    private static void patternTest(){
        IntStream.rangeClosed(1,100).parallel().forEach(i->{
            System.out.println(LocalDateTime.now()+":"+i);
            System.out.println("-----");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 通过HashMap实现一个缓存操作，先判断缓存中是否有值；如果没有值，就从数据库搜索值，然后将数据加入缓存
     *
     * */
    private static Map<Long, SysRole> cache = new ConcurrentHashMap<>();
    private static SysRole getSysRoleAndCache(Long id){
        SysRole sysRole = null;
        List<SysRole> sysRoles = new ArrayList<>();
        //key存在，就返回value
        if (cache.containsKey(id)){
            sysRole = cache.get(id);
        }else {
            //不存在，则获取value
            //遍历数据源查询获得sysRole
            for (SysRole p: sysRoles){
                if (p.getId().equals(id)){
                    sysRole = p;
                    break;
                }
            }
            //加入ConcurrentHashMap
            if (sysRole != null){
                cache.put(id, sysRole);
            }
        }
        return sysRole;
    }

    public void notcoolCache() {
        getSysRoleAndCache(1L);
        getSysRoleAndCache(100L);
        System.out.println(cache);
        assertThat(cache.size(), is(1));
        assertTrue(cache.containsKey(1L));
    }

    /**
     * java8中利用 ConcurrentHashMap 的 computeIfAbsent 方法
     * */
    private static SysRole getSysRoleANdCacheCool(Long id){
        List<SysRole> sysRoles = new ArrayList<>();

        //这个使用computeIfAbsent来将数据加入缓存中
       // SysRole sysRole = sysRoles.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);/

        cache.computeIfAbsent(id, null);
        return cache.computeIfAbsent(id,i->
                sysRoles.stream()
                        .filter(p->p.getId().equals(i)) //过滤
                .findFirst() // 找到第一个，得到OptionAl<SysRole>
                .orElse(null)  //如果没有找到就使用null
        );
    }


    @Test
    public void filesExample() throws IOException {
        //无限深度，递归遍历文件夹
        try (Stream<Path> pathStream = Files.walk(Paths.get("."))) {
            pathStream.filter(Files::isRegularFile) //只查普通文件
                    .filter(FileSystems.getDefault().getPathMatcher("glob:**/*.java")::matches) //搜索java源码文件
                    .flatMap(ThrowingFunction.unchecked(path ->
                            Files.readAllLines(path).stream() //读取文件内容，转换为Stream<List>
                                    .filter(line -> Pattern.compile("public class").matcher(line).find()) //使用正则过滤带有public class的行
                                    .map(line -> path.getFileName() + " >> " + line))) //把这行文件内容转换为文件名+行
                    .forEach(System.out::println); //打印所有的行
        }
    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Throwable> {
        static <T, R, E extends Throwable> Function<T, R> unchecked(ThrowingFunction<T, R, E> f) {
            return t -> {
                try {
                    return f.apply(t);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            };
        }

        R apply(T t) throws E;
    }

}
