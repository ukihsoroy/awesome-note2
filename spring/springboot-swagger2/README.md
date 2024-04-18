# Swagger2 快速集成

**1.pom**

- 工程中引入下面依赖 
```
    <!--引入Swagger组件 -->
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.8.0</version>
    </dependency>

    <!--SwaggerUI界面-->
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.8.0</version>
    </dependency>
```

**2.配置Conf类**

```
    //开启Swagger2
    @EnableSwagger2
    //boot 配置类
    @Configuration
    public class Swagger2Config {
    
        /**
         * 通过 createRestApi函数来构建一个DocketBean
         */
        @Bean
        public Docket createRestApi() {
    
            return new Docket(DocumentationType.SWAGGER_2)
                    //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                    .apiInfo(apiInfo())
                    .select()
                    /**
                     * 控制暴露出去的路径下的实例
                     * 如果某个接口不想暴露,可以使用以下注解
                     * @ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                     */
                    .apis(RequestHandlerSelectors.basePackage("org.ko.web.rest"))
                    .paths(PathSelectors.any())
                    .build();
        }
    
        /**
         * 构建API文档详细参数
         * @return
         */
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Swagger2 API")
                    .contact(new Contact("K.O", "", ""))    //作者
                    .version("1.0")
                    .description("API 描述")
                    .build();
        }
    }
```

**3.Controller使用Swagger注解, 配置文档**

```
    @RestController
    @RequestMapping("/user")
    //配置Swagger 接口组注释
    @Api(value = "UserController", description = "用户集成接口")
    public class UserController {
    
        @Autowired private UserService userService;
    
        @PostMapping
        //单个方法注释
        @ApiOperation("新增用户")
        public Long save(
                //参数注释
                @ApiParam(name = "user", value = "用户数据", required = true) @RequestBody User user) {
            return userService.save(user);
        }
    }
```

**4.Swagger注解解释**

- @Api: 用在类上, 说明该类的作用
```
    @Api(value = "UserController", description = "用户集成接口")
```

- @ApiOperation: 用在方法上, 说明方法的作用
```
    @ApiOperation(value = "新增用户", notes = "新增用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
```

- @ApiImplicitParams: 用在方法上, 包含一组参数说明
- @ApiImplicitParams: 用在@ApiImplicitParams注解中, 指定一个请求参数的各个方面
```
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Long", paramType = "path")
    })
```

- @ApiParam: 用在方法参数上, 表示当前参数作用
```
    @ApiParam(name = "user", value = "用户数据", required = true)
```

- @ApiResponses: 用在方法上, 表示当前方法的一组响应
- @ApiResponse: 用在@ApiResponses注解中, 用来表示一个错误响应信息
```
    @ApiResponses({
            @ApiResponse(code = 400, message = "User doesn't exist")
    })
```

- @ApiModel: 用在实体类上, 用来表示一个实体
```
    @ApiModel
    public class User {}
```

- @ApiModelProperty: 用在实体类的属性上, 翻译属性
```
    //类属性注释
    @ApiModelProperty(value = "id", name = "主键ID")
    private Long id;

    @ApiModelProperty(value = "name", name = "姓名")
    private String name;

    @ApiModelProperty(value = "age", name = "年龄")
    private Short age;
```

**5.访问**

`Swagger UI`: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 