# JSONDoc 快速生成Restful API

**1.pom**

- 工程中引入下面依赖 
```
    <!--JSONDoc 快速启动-->
    <dependency>
      <groupId>org.jsondoc</groupId>
      <artifactId>spring-boot-starter-jsondoc</artifactId>
      <version>1.2.19</version>
    </dependency>

    <!--JSONDoc-UI前端页面-->
    <dependency>
      <groupId>org.jsondoc</groupId>
      <artifactId>jsondoc-ui-webjar</artifactId>
      <version>1.2.19</version>
    </dependency>
```

**2.Controller使用JSONDoc注解, 配置文档**

```
    @RestController
    @RequestMapping("/user")
    //配置JSONDoc接口组注释
    @Api(name = "用户集成接口", description = "获取维护用户具体数据")
    public class UserController {
    
        @Autowired private UserService userService;
    
        @GetMapping
        //单个方法注释
        @ApiMethod(summary = "获取全部用户", description = "获取全部用户")
        public Collection<User> getAll() {
            return userService.getAll();
        }
    }
```

**4.JSONDoc注解解释**

- @Api: 用在类上, 说明该类的作用
```
    @Api(name = "用户集成接口", description = "获取维护用户具体数据")
```

- @ApiOperation: 用在方法上, 说明方法的作用
```
    @ApiMethod(summary = "获取全部用户", description = "获取全部用户")
```

- @ApiPathParam: 写在方法参数上, 表示路径参数			
- @ApiQueryParam: 写在方法参数上, 表示查询参数			
- @ApiBodyObject: 写在方法参数上, 表示请求正文中参数
```
    @ApiPathParam(name = "id", description = "待修改用户ID") @PathVariable("id") Long id
    @ApiBodyObject(clazz = User.class) @RequestBody User user
```

- @ApiObject: 用在实体类上, 用来表示一个实体
```
    @ApiObject(name = "User", description = "用户实体类")
    public class User {}
```

- @ApiObjectField: 用在实体类的属性上, 翻译属性
```
    //类属性注释
    @ApiObjectField(name = "id", description = "用户主键ID")
    private Long id;

    @ApiObjectField(name = "name", description = "用户姓名")
    private String name;

    @ApiObjectField(name = "age", description = "用户年龄")
    private Short age;
```

**5.访问**

`JSONDoc UI`: [http://localhost:8080/jsondoc-ui.html](http://localhost:8080/jsondoc-ui.html) 

`JSONDoc URL`: [http://localhost:8080/jsondoc](http://localhost:8080/jsondoc)
