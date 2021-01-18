### 正确高效处理Jpa中实体之间的关联关系

#### 实体之间关系

实体与实体之间的关联关系一共分为四种，分别为 **OneToOne、OneToMany、ManyToOne 和 ManyToMany**

而实体之间的关联关系又分为双向的和单向的

##### 一、@OneToOne 关联关系

@OneToOne 一般表示对象之间**一对一**的关联关系，它**可以放在 field** 上面，**也可以放在 get/set** 方法上面

如果是配置**双向关联**，**维护关联关系的是拥有外键的一方**，而**另一方必须配置 mappedBy**；如果是**单项关联**，直接配置在拥有外键的一方即可

例子:user 表是用户的主信息，user_info 是用户的扩展信息，两者之间是一对一的关系。user_info 表里面有一个 user_id 作为关联关系的外键，如果是单项关联，则如下

```java
@Entity
@Data
@Builder
public class User {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String name;
   private String email;
   private String sex;
   private String address;
}
```

User 实体里面什么都没变化，不需要添加 @OneToOne 注解。我们只需要在拥有外键的一方配置就可以，所以 UserInfo 的代码如下：

```java
@Entity
@Data
@Builder
@ToString(exclude = "user")
public class UserInfo {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private Integer ages;
   private String telephone;
  
   //cascade:表的级联操作
   //@JoinColumn(name="user_id",referencedColumnName="id",nullable=false)
   //private User user;
   @OneToOne //维护user的外键关联关系，配置一对一
   private User user;
}
```

如果需要配置双向关联，则在User实体中添加@OneToOne(mappedBy = "XXX");

```java
@Entity
@Data
@Builder
public class User {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String name;
   private String email;
   @OneToOne(mappedBy = "user")
   private UserInfo userInfo;//变化之处
   private String sex;
   private String address;
}
```

#### @interface OneToOne 源码解读

```java
public @interface OneToOne {
   //表示关系目标实体，默认该注解标识的返回值的类型的类。
   Class targetEntity() default void.class;
   //cascade 级联操作策略，就是我们常说的级联操作
   CascadeType[] cascade() default {};
   //数据获取方式EAGER(立即加载)/LAZY(延迟加载)
   FetchType fetch() default EAGER;
   //是否允许为空，默认是可选的，也就表示可以为空；
   boolean optional() default true;
   //关联关系被谁维护的一方对象里面的属性名字。 双向关联的时候必填
   String mappedBy() default "";
   //当被标识的字段发生删除或者置空操作之后，是否同步到关联关系的一方，即进行通过删除操作，默认flase，注意与CascadeType.REMOVE 级联删除的区别
   boolean orphanRemoval() default false;
}
```

#### mappedBy 注意事项

只有**关联关系**的**维护方**才能操作两个实体之间外键的关系。被维护方即使设置了维护方属性进行存储也不会更新外键关联。

**mappedBy 不能与 @JoinColumn 或者 @JoinTable 同时使用**，因为没有意义，关联关系不在这里面维护。

mappedBy 的值是指**另一方的实体里面属性的字段**，而不是数据库字段，也不是实体的对象的名字。

#### 注解@JoinColumn的接口定义

```java
public interface JoinColumn extends Annotation {

   //外键列的名称，默认情况下是：引用实体的字段名称 +“_”+ 被引用的主键列的名称。一般也可以自定义，一般见名知意，就可以采用默认值。
   public abstract String name();
   //参考列，默认值是关联表的主键
   public abstract String referencedColumnName();

   public abstract boolean unique();
   //是否可以为空
   public abstract boolean nullable();
   //是否可以插入
   public abstract boolean insertable();
   //是否可以更新
   public abstract boolean updatable();
   //列定义
   public abstract String columnDefinition();

   public abstract String table();
   //外键
   public abstract ForeignKey foreignKey();
}
```

#### 注解@CascadeType():用于关联操作的

```java
public enum CascadeType {
    /** Cascade all operations */
  	//全选
    ALL,
    /** Cascade persist operation */
  	//级联新建
    PERSIST,
    /** Cascade merge operation */
  	//级联更新
    MERGE,
    /** Cascade remove operation */
  	//级联删除
    REMOVE,

    /** Cascade refresh operation */
  	//级联刷新
    REFRESH,
    /**
     * Cascade detach operation
     *
     * @since Java Persistence 2.0
     *
     */
  	//级联脱管/游离操作:如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
    DETACH
}
```

其中，**默认是没有级联操作的**，关系表不会产生任何影响

#### 主键和外键都是同一个字段

假设 user 表是主表，user_info 的主键是 user_id，并且 user_id=user 是表里面的 id

就应该设置@MapsId这个注解

```java
public class UserInfo implements Serializable {
   @Id
   private Long userId;
   private Integer ages;
   private String telephone;
   @MapsId
   @OneToOne(cascade = {CascadeType.PERSIST},orphanRemoval = true)
   private User user;
}
```

@MapsId 注解的作用是把关联关系实体里面的 ID（默认）值 copy 到 @MapsId 标注的字段上面（这里指的是 user_id 字段）

#### @OneToOne 延迟加载，我们只需要 ID 值

一、使用saveAndFlush()方法，并且@OneToOne 上面我们添加 @MapsId 注解，@OneToOne 里面的 fetch = FetchType.LAZY 设置延迟加载

```java
 @MapsId
 @OneToOne(cascade = {CascadeType.PERSIST},orphanRemoval = true,fetch = FetchType.LAZY)
 private User user;
```
二、去掉 @OneToOne 关联关系，新增字段

```java
@Column(name = "user_id")
private Long userId;
```

### @JoinCloumns & JoinColumn

这两个注解是集合关系，他们可以同时使用，@JoinColumn 表示单字段，@JoinCloumns 表示多个 @JoinColumn

@JoinCloumns指定中间表中关联自己ID的字段，@InverseJoinColumns表示中间表中关联对方ID的字段

```java
public @interface JoinColumn {
   //关键的字段名,默认注解上的字段名，在@OneToOne代表本表的外键字段名字；
   String name() default "";
   //与name相反关联对象的字段，默认主键字段
   String referencedColumnName() default "";
   //外键字段是否唯一
   boolean unique() default false;
   //外键字段是否允许为空
   boolean nullable() default true;
   //是否跟随一起新增
   boolean insertable() default true;
   //是否跟随一起更新
   boolean updatable() default true;
   //JPA2.1新增，外键策略
   ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

}
```

 @ForeignKey(PROVIDER_DEFAULT) 里面枚举值

```java
public enum ConstraintMode {
   //创建外键约束
   CONSTRAINT,
   //不创建外键约束
   NO_CONSTRAINT,
   //采用默认行为
   PROVIDER_DEFAULT
}
```

### @ManyToOne& @OneToMany

@ManyToOne 代表多对一的关联关系，而 @OneToMany 代表一对多，一般两个成对使用表示双向关联关系

 JPA 协议中也是明确规定：维护关联关系的是拥有外键的一方，而另一方必须配置 mappedBy

```java
public @interface OneToMany {
   Class targetEntity() default void.class;
   //cascade 级联操作策略：(CascadeType.PERSIST、CascadeType.REMOVE、CascadeType.REFRESH、CascadeType.MERGE、CascadeType.ALL)
   如果不填，默认关系表不会产生任何影响。
   CascadeType[] cascade() default {};
	//数据获取方式EAGER(立即加载)/LAZY(延迟加载)
   FetchType fetch() default LAZY;
   //关系被谁维护，单项的。注意：只有关系维护方才能操作两者的关系。
   String mappedBy() default "";
	//是否级联删除。和CascadeType.REMOVE的效果一样。两种配置了一个就会自动级联删除
   boolean orphanRemoval() default false;

}
```

1. @ManyToOne 一定是维护外键关系的一方，所以没有 mappedBy 字段；
2. @ManyToOne 删除的时候一定不能把 One 的一方删除了，所以也没有 orphanRemoval 的选项；
3. @ManyToOne 的 Lazy 效果和 @OneToOne 的一样，所以和上面的用法基本一致；
4. @OneToMany 的 Lazy 是有效果的。

```java
@Entity
@Data
@Builder
public class User implements Serializable {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String name;
   private String email;
   private String sex;
   @OneToMany(mappedBy = "user",orphanRemoval = true,fetch = FetchType.LAZY)
   private List<UserAddress> address;
}

@Entity
@Data
@Builder
@ToString(exclude = "user")
public class UserAddress {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String address;
   @ManyToOne(cascade = CascadeType.ALL)
   private User user;

}

```

### @ManyToMany

@ManyToMany 代表多对多的关联关系，这种关联关系任何一方都可以维护关联关系。

```java

@Entity
@Data
@Builder
public class User{

   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String name;
   @ManyToMany(mappedBy = "users")
   private List<Room> rooms;
}



@Entity
@Data
@Builder
@ToString(exclude = "users")
public class Room {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String title;
   @ManyToMany
   private List<User> users;
}

```

可以使用@JoinTable修改表名

```java
@Entity
@Data
@Builder
@ToString(exclude = "users")
public class Room {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String title;
   @ManyToMany
   @JoinTable(name = "user_room_ref",
         joinColumns = @JoinColumn(name = "room_id_x"),
         inverseJoinColumns = @JoinColumn(name = "user_id_x")
   )
   private List<User> users;
}
```

#### @JoinTable源码解释

```java
public @interface JoinTable {
   //中间关联关系表明
   String name() default "";
   //表的catalog
   String catalog() default "";
   //表的schema
   String schema() default "";
   //维护关联关系一方的外键字段的名字
   JoinColumn[] joinColumns() default {};
   //另一方的表外键字段
   JoinColumn[] inverseJoinColumns() default {};
   //指定维护关联关系一方的外键创建规则
   ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
   //指定另一方的外键创建规则
   ForeignKey inverseForeignKey() default @Forei gnKey(PROVIDER_DEFAULT);
}
```

同时如果中间表中需要有其他字段，可以使用@ManyToOne和@OneToMany来代替@ManyToMany

```java
@Entity
@Data
@Builder
public class UserRoomRelation {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private Date createTime,udpateTime;
   @ManyToOne
   private Room room;
   @ManyToOne
   private User user;
}

public class User implements Serializable {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   @OneToMany(mappedBy = "user")
   private List<UserRoomRelation> userRoomRelations;
....}


Room 变化如下：

public class Room {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @OneToMany(mappedBy = "room")
   private List<UserRoomRelation> userRoomRelations;
...}

```

#### @ManyToMany 的最佳实践

1. 实际生产一般是在中间表对象里面做单向关联，这样会让实体之间的关联关系简单很多。
2. 与 @OneToMany 一样的道理，不要用级联删除和 orphanRemoval=true。
3. FetchType 采用默认方式：fetch = FetchType.LAZY 的方式。

