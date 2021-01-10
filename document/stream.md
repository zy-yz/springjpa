### 1 流的创建

#### 1.1流的创建方法

创建流的方法有三种，分别是，Stream.of(),Stream.iterate()，Stream.generate()

```java
static <T> Stream<T> of(T... values)

static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)

static <T> Stream<T> generate(Supplier<T> s)
```

Stream.of()：参数简单，就是一系列的泛型参数

Stream.iterate()：第一个参数是一个初始值，第二个参数是一个操作

Stream.generate(): 参数就是一个Supplier的供给型参数

```java
/**
 * 流的创建方法
 * */
public static void testCreateStream(){
    //利用Stream.of方法创建流
    Stream<String> stream = Stream.of("hello","world","Java8");
    stream.forEach(System.out::println);
    System.out.println("----------------");
    //利用Stream.iterate方法创建流
    Stream.iterate(10,n->n+1)
            .limit(5)
            .collect(Collectors.toList())
            .forEach(System.out::println);
    System.out.println("---------------");
    //利用Stream.generate方法创建流
    Stream.generate(Math::random)
            .limit(5)
            .forEach(System.out::println);
    System.out.println("--------------");
    //从现有集合创建流
    List<String> strings = Arrays.asList("hello","world","java");
    String collect = strings.stream().collect(Collectors.joining(","));
    System.out.println(collect);
}
```

### 2.流的操作

#### 2.1装箱流

在处理对象流的时候，可以利用 `Collectors` 类的**静态方法**转换为集合，例如，将字符串流转换为 `List<String>` ，这种方式是没有问题的。

但是，如果遇到 double流想要转换为 List 时，这是就会报错。

```java
DoubleStream.of(1.0, 2.0, 3.0)
                .collect(Collectors.toList());//错误的写法
```

这种方式就是错误的，编译是不能通过的。

##### 可以利用 boxed 方法

利用 `boxed` 方法，可以将 `DoubleStream` 转换为 `Stream<Double>` ，例如；

```java
DoubleStream.of(1.0, 2.0, 3.0)
                .boxed()
                .collect(Collectors.toList());
```

这样就解决了上面的问题。

##### 利用 mapToObj 方法

利用 `mapToObj` 方法也可以实现上面的功能，另外，也提供了 `mapToInt、mapToLong、mapToDouble` 等方法将基本类型流转换为相关包装类型。

```java
DoubleStream.of(1.0, 2.0, 3.0)
                .mapToObj(Double::valueOf)
                .collect(Collectors.toList());
```

##### collect 方法

一般情况下，我们利用 `collect` 方法的时候，都是用于将**流的数据收集为基本类型的集合**，例如；

```java
stream.collect(Collectors.toList())
```

然而，` collect` 方法其实还有一种更加一般化的形式，如下；

```java
<R> R collect(Supplier<R> supplier,
                        ObjIntConsumer<R> accumulator,
                        BiCnsumer<R,R> combiner)
```

上面这种方法的**第一个参数是一个供给器，相当于初始化一个容器，第二个参数是累加器，相当于给初始化的容器赋值，第三个参数是组合器，相当于将这些元素全部组合到一个容器**。

```java
List<Double> list = DoubleStream.of(1.0, 2.0, 3.0)
                .collect(ArrayList<Double>::new, ArrayList::add, ArrayList::addAll);
```

上面的例子我们可以看到，第一个参数：使用一个**静态方法**初始化一个 `List` 容器，第二个参数：使用静态方法 `add` ，添加元素，第三个参数：使用静态方法 `addAll` ，用于联合所有的元素。

从最后的返回值为 `List<Double>`，我们也可以看出，全部组合成一个初始化的 `List` 集合中了。

#### 2.2 字符串与流之间的转换

字符串与流之间的转换，将 **String 转为流**有两种方法，分别是 `java.lang.CharSequence` 接口定义的默认方法 `chars` 和 `codePoints` ，而将**流转为字符串**就是我们前面已经讲解到的方法 `collect` 。

```java
private static void testStringToStream(){
    /**转换成流*/
    String s = "hello world java8".codePoints()
            .collect(StringBuffer::new,
                    StringBuffer::appendCodePoint,
                    StringBuffer::append)
            /**将流转换成字符串*/
            .toString();
    System.out.println(s);

    /**转换成流*/
    String s1 = "hello world Java8".chars()
            .collect(StringBuffer::new,
                    StringBuffer::appendCodePoint,
                    StringBuffer::append)
            /**将流转换成字符串*/
            .toString();
    System.out.println(s1);
}
```

先用` chars` 和 `codePoints` 方法转换为流，然后都是利用 `collect` 方法再转回字符串。

#### 2.3 流的映射 map 与 flatMap

流的映射是什么意思呢，我们先将一个在 Java8 之前的例子，**我们常常需要将一个集合的对象的某一个字段取出来，然后再存到另外一个集合中**，这种场景我们在 Java8 之前我们会这样实现。

```java
    @Test
    public void testList() {
        List<Person> list = new ArrayList<>();
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Java5"));
        friends.add(new Friend("Java6"));
        friends.add(new Friend("Java7"));
        Person person = new Person();
        person.setFriends(friends);
        list.add(person);
        List<String> strings = new ArrayList<>();
        for(Person p : list){
            strings.add(p.getName());
        }
    }
```

但是，` Java8` 却改变了这种现实， `map` 和 `flatMap`的使用。

```java
<R> Stream<R> map(Function<? super T,? extends R> mapper)

<R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
  
 
```

使用map方法

```java
public static void testMapAndFlatMap(){
    List<SysRole> list = new ArrayList<>();
    List<SysMenuRole> menuRoles = new ArrayList<>();
    menuRoles.add(new SysMenuRole());
    SysRole sysRole = new SysRole();
    sysRole.setRoleRelation(menuRoles);
    list.add(sysRole);
    //映射名字
    List<String> collect = list.stream().map(SysRole::getNameEn).collect(Collectors.toList());

}
```

使用 `map` 方法，参数给定 `SysRole::getNameEn` 映射出 `name`，然后再用 `collect` 收集到 `List` 中

但是，如果我们用 map 方法想要映射出 `SysMenuRole` 属性，会遇到一个问题；

```java

        List<List<`SysMenuRole`>> collect = list.stream().map(SysRole::getMenu).collect(Collectors.toList());
```

我们发现，上面的返回值是 `List<List<`SysMenuRole`>>`，这种形式集合里面还包着集合，处理有点麻烦，但是，不是还有另外 `flatMap` 没有使用吗，这个方法正好能够解决这个问题。

```java
list.stream().flatMap(m -> m.getRoleRelation().stream().collect(Collectors.toList()));
```

这个方法的返回值是 `List<SysMenuRole>`，正如我们看到的，**` flatMap` 的方法能够“展平”包裹的流**，这就是 `map` 和 `flatMap` 的区别。

#### 2.4 流的连接

流的连接有两种方式，如果是**两个流的连接**，使用 `Stream.concat` 方法，如果是**三个及三个以上的流的连接**，就使用 `Stream.flatMap` 方法。

```java
    public static void testJoinStream(){
        //两个流的连接
        Stream<String> first = Stream.of("1", "2", "3");
        Stream<String> second = Stream.of("4", "5", "6");
        Stream<String> third = Stream.of("7", "8", "9");
//        Stream<String> concat = Stream.concat(first, second);
//        System.out.println(concat.collect(Collectors.joining(",")));

        //多个流的连接
        Stream<String> stringStream = Stream.of(first, second, third).flatMap(Function.identity());
        System.out.println(stringStream.collect(Collectors.joining(",")));
    }
```

### 3 流的规约操作

流的规约操作几种类型，这里都讲一下。

#### 内置的规约操作

基本类型流都有内置的规约操作。包括**average、count、max、min、sum、summaryStatistics**，前面的几个方法相信不用说了，` summaryStatistics` 方法是前面的几个方法的结合，下面我们看看他们如何使用。

```java
public static void testReduce1() {
    String[] strings = {"hello", "sihai", "hello", "Java8"};
    long count = Arrays.stream(strings) .map(String::length) .count();
    System.out.println(count);
    System.out.println("##################");
    int sum = Arrays.stream(strings) .mapToInt(String::length) .sum(); System.out.println(sum);
    System.out.println("##################");
    OptionalDouble average = Arrays.stream(strings) .mapToInt(String::length) .average();
    System.out.println(average); System.out.println("##################");
    OptionalInt max = Arrays.stream(strings) .mapToInt(String::length) .max();
    System.out.println(max); System.out.println("##################");
    OptionalInt min = Arrays.stream(strings) .mapToInt(String::length) .min();
    System.out.println(min);
    DoubleSummaryStatistics statistics = DoubleStream.generate(Math::random) .limit(1000) .summaryStatistics();
    System.out.println(statistics); 
}
```

#### 基本的规约操作

基本的规约操作是利用前面讲过的 `reduce` 方法实现的，` IntStream` 接口定义了三种 `reduce` 方法的重载形式，如下；

```java
OptionalInt reduce(IntBinaryOperator op)

int reduce(int identity, IntBianryOperator op)

<U> U reduce(U identity,
      BiFunction<U,? super T,U> accumulator,
      BianryOperator<U> combiner)
```

上面的 `identity` 参数就是初始化值的意思，` IntBianryOperator` 类型的参数就是操作，例如 `lambda` 表达式；` BianryOperator<U> combiner`是一个组合器，在前面有讲过。

下面我们通过一个例子来讲解一下。

```java
    public void testReduce2() {
        int sum = IntStream.range(1, 20)
                .reduce((x, y) -> x + y)
                .orElse(0);
        System.out.println(sum);

        System.out.println("##################");

        int sum2 = IntStream.range(1, 20)
                .reduce(0, (x, y) -> x + 2 * y);
        System.out.println(sum2);

        System.out.println("##################");

        int sum3 = IntStream.range(1, 20)
                .reduce(0, Integer::sum);
        System.out.println(sum3);

    }
```

例子中的第一个是**1到20累加**的操作，第二个以**0为初始值，然后2倍累加**，第三个是**以0为初始值，累加**。

#### 流的计数

流的数量统计有两种方法，分别是 `Stream.count()` 方法和 `Collectors.counting()` 方法。

```java
    public void testStatistics() {
        //统计数量
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        long count = Arrays.stream(strings)
                .count();
        System.out.println(count);

        System.out.println("##################");

        Long count2 = Arrays.stream(strings)
                .collect(Collectors.counting());
        System.out.println(count2);

    }
```

### 4 流的查找与匹配

#### 流的查找

流的查找 Stream 接口提供了两个方法 `findFirst` 和 `findAny`。

`findFirst` 方法返回流中的**第一个**元素的 `Optional`，而 `findAny` 方法返回流中的**某个**元素的 `Optional`。

我们来看一个例子。

```java
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        Optional<String> first = Arrays.stream(strings)
                .findFirst();
        System.out.println(first.get());

        System.out.println("##################");

        Optional<String> any = Arrays.stream(strings).findAny();
        System.out.println(any.get());

        System.out.println("##################");
```

#### 流的匹配

流的匹配 Stream 接口提供了三个方法，分别是 `anyMatch`（任何一个元素匹配，返回 true）、` allMatch`（所有元素匹配，返回 true）、` noneMatch`（没有一个元素匹配，返回 true）。

```java
        boolean b = Stream.of(1, 2, 3, 4, 5, 10)
                .anyMatch(x -> x > 5);
        System.out.println(b);

        System.out.println("##################");

        boolean b2 = Stream.of(1, 2, 3, 4, 5, 10)
                .allMatch(x -> x > 5);
        System.out.println(b2);

        System.out.println("##################");

        boolean b3 = Stream.of(1, 2, 3, 4, 5, 10)
                .noneMatch(x -> x > 5);
        System.out.println(b3);
```

### 