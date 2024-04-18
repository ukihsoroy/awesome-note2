package org.ko.ibatis.handler;

import com.google.gson.Gson;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.File;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(value = String.class)
public class JsonTypeHandler<E> extends BaseTypeHandler {


    private static final Integer ZERO = 0;

    private List<String> packageNames = new ArrayList<String>();

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String result = resultSet.getString(s);
        if (!result.isEmpty()) {
            boolean flag = ZERO.equals(result.indexOf("{")) && new Integer(result.length() - 1).equals(result.lastIndexOf("}"));
            if (flag) {
                for (String className : packageNames) {
                    try {
                        Class<?> clazz = Class.forName(className.replace(".class", "").trim());
                        return new Gson().fromJson(result, clazz);
                    } catch (ClassNotFoundException e ) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return resultSet.getString(s);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getString(i);
    }

    private void scanPackage(String packagePath) {
        //通过反射获取当前目录的绝对路径
        URL url = this.getClass().getClassLoader().getResource(replaceTo(packagePath));// 将所有的.转义获取对应的路径
        System.out.println(url);
        //获取当前的全部路径
        String pathFile = url.getFile();
        //全部文件
        File file = new File(pathFile);
        //获取全部的名字
        String fileList[] = file.list();
        for (String path : fileList) {
            //分别读取每一个
            File eachFile = new File(pathFile + "/" + path);
            //如果是一个目录
            if (eachFile.isDirectory()) {
                //递归向下进行读取目录
                scanPackage(packagePath + "." + eachFile.getName());
            } else {
                //将获取到的文件名保存到集合中
                packageNames.add(packagePath + "." + eachFile.getName());
            }
        }
    }

    private String replaceTo(String path) {
        return path.replaceAll("\\.", "/");
    }

}
