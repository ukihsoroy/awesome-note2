##使用appassembler-maven-plugin 打包自定义目录

1.Pom中添加
```
    <plugin>
        <artifactId>maven-resources-plugin</artifactId>
        <executions>
            <execution>
                <id>copy-resources</id>
                <phase>package</phase>
                <goals>
                    <goal>copy-resources</goal>
                </goals>
                <configuration>
                    <outputDirectory>${project.build.directory}/${project.name}/classes</outputDirectory>
                    <resources>
                        <resource>
                            <directory>${project.build.directory}/classes</directory>
                            <filtering>false</filtering>
                        </resource>
                    </resources>
                </configuration>
            </execution>
        </executions>
    </plugin>
    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>appassembler-maven-plugin</artifactId>
        <version>2.0.0</version>
        <configuration>
            <!-- 生成linux, windows两种平台的执行脚本 -->
            <platforms>
                <platform>windows</platform>
                <platform>unix</platform>
            </platforms>
            <!-- 根目录 -->
            <assembleDirectory>${project.build.directory}/${project.name}</assembleDirectory>
            <!-- 打包的jar，以及maven依赖的jar放到这个目录里面 -->
            <repositoryName>lib</repositoryName>
            <!-- 可执行脚本的目录 -->
            <binFolder>bin</binFolder>
            <!-- lib目录中jar的存放规则，默认是${groupId}/${artifactId}的目录格式，flat表示直接把jar放到lib目录 -->
            <repositoryLayout>flat</repositoryLayout>
            <encoding>UTF-8</encoding>
            <logsDirectory>logs</logsDirectory>
            <tempDirectory>tmp</tempDirectory>
            <programs>
                <program>
                    <!-- 启动类 -->
                    <mainClass>org.ko.web.Application</mainClass>
                    <jvmSettings>
                        <extraArguments>
                            <extraArgument>-server</extraArgument>
                            <extraArgument>-Xmx2G</extraArgument>
                            <extraArgument>-Xms2G</extraArgument>
                        </extraArguments>
                    </jvmSettings>
                </program>
            </programs>
        </configuration>
    </plugin>
```
2.修改启动的主类-对应自己的main入口
```
    <!-- 启动类 -->
    <mainClass>org.ko.web.Application</mainClass>
```

3.使用maven命令打包
```
    mvn clean package appassembler:assemble
```

4.修改
- {project.name}/target/{project.name}/bin/application.bat
- 找到CLASSPATH,  在后面添加："%BASEDIR%"\classes;
```
    set CLASSPATH="%BASEDIR%"\classes;...
```

5.启动：{project.name}/target/{project.name}/bin/application.bat


6.错误
- 命令行过长--set CLASSPATH时REPO有可能过多, 这里使用通配符
```
    set CLASSPATH="%BASEDIR%"\classes;"%REPO%"\*;
```