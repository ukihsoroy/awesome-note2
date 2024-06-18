package org.ko.mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ko.mvc.annotation.Autowired;
import org.ko.mvc.annotation.RequestMapping;
import org.ko.mvc.annotation.RestController;
import org.ko.mvc.annotation.Service;

@WebServlet("/DispatcherServlet")  
public class DispatcherServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    // 所有文件的路径
    List<String> packageNames = new ArrayList<String>();  
    // 所有类的实例，key是注解的value,value是所有类的实例  
    Map<String, Object> instanceMap = new HashMap<String, Object>();  
    // requestMapping的映射关系
    Map<String, Object> handerMap = new HashMap<String, Object>();  
    
    public DispatcherServlet() {  
        super();  
    }  
  
    public void init(ServletConfig config) throws ServletException {  
        //包扫描,获取包中的文件到 packageNames中
        scanPackage("org.ko");  
        try {  
        	//创建对应类的实例到instanceMap中
            filterAndInstance();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        //读取requestMapping的映射关系  
        handerMap();  
        //实现注入  
        ioc();  
    }  
  
    private void filterAndInstance() throws Exception {  
        if (packageNames.size() <= 0) {  
            return;  
        }  
        for (String className : packageNames) {  
        	//去掉读取文件的.class类型，获取Class类的对象
            Class<?> cName = Class.forName(className.replace(".class", "").trim());  
            //查看当前的类是否使用RestController注解
            if (cName.isAnnotationPresent(RestController.class)) {  
            	//获取当前类的实例
                Object instance = cName.newInstance();  
//                //获取注解对象
//                RestController controller = (RestController) cName.getAnnotation(RestController.class);
//                //读取注解中的参数
//                String key = controller.value();
                RequestMapping mapping = (RequestMapping)cName.getAnnotation(RequestMapping.class);
                String key = mapping.value();
                System.out.println(key);
                if (key != null && !"".equals(key)) {
                	//当value不为空时
                	instanceMap.put(key, instance);  
                } else {
                	//为空时
                	instanceMap.put(cName.getName(), instance);  
                }
            } else if (cName.isAnnotationPresent(Service.class)) {  
                Object instance = cName.newInstance();  
                Service service = (Service) cName.getAnnotation(Service.class);  
                String key = service.value(); 
                System.out.println(key);
                if (key != null && !"".equals(key)) {
                	//当value不为空时
                	instanceMap.put(key, instance);  
                } else {
                	//为空时
                	instanceMap.put(cName.getName(), instance);  
                }
            } else {  
                continue;  
            }  
        }  
    }  
  
    private void ioc() {  
    	//如果为空返回
        if (instanceMap.isEmpty())  
            return;  
        //遍历
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {  
            // 拿到当前类对象-所有属性  
            Field fields[] = entry.getValue().getClass().getDeclaredFields();  
            // 遍历所有属性
            for (Field field : fields) {  
            	// 可访问私有属性  
                field.setAccessible(true);
                
                //如果全局属性中有Autowired注解
                if (field.isAnnotationPresent(Autowired.class)) {
                	//获取Autowired注解对象
                	field.getName();
                	Autowired autowired = field.getAnnotation(Autowired.class);  
                	//获取value
                    String value = autowired.value();  
//                    field.setAccessible(true);  
                    try {  
                    	//将instanceMap中的实例注入
                        field.set(entry.getValue(), instanceMap.get(value));  
                    } catch (IllegalArgumentException e) {  
                        e.printStackTrace();  
                    } catch (IllegalAccessException e) {  
                        e.printStackTrace();  
                    }  
                }
            }  
        }  
    }  
  
    /** 
     * 扫描包下的所有文件 
     * @param packagePath 
     */  
    private void scanPackage(String packagePath) {  
    	//通过反射获取当前目录的绝对路径
        URL url = this.getClass().getClassLoader().getResource("/" + replaceTo(packagePath));// 将所有的.转义获取对应的路径  
        //获取当前的全部路径
        String pathFile = url.getFile();  
        //全部文件
        File file = new File(pathFile);  
        //获取全部的名字
        String fileList[] = file.list();  
        for (String path : fileList) { 
        	//分别读取每一个
            File eachFile = new File(pathFile + path);  
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
  
    /** 
     * 建立mapping映射关系 
     */  
    private void handerMap() {  
        if (instanceMap.size() <= 0) return;  
        //便利实例map
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {  
            if (entry.getValue().getClass().isAnnotationPresent(RestController.class)) {  
            	//如果当前的实例有controller注解
//                RestController controller = (RestController) entry.getValue().getClass().getAnnotation(RestController.class);
                //获取controller中的value参数
//                String ctvalue = controller.value();
                //获取当前类的全部方法对象
                Method[] methods = entry.getValue().getClass().getMethods();  
                //遍历方法对象
                for (Method method : methods) {  
                	//如果方法对象中有RequestMapping注解
                    if (method.isAnnotationPresent(RequestMapping.class)) {  
                    	//获取注解RequestMapping对象
                        RequestMapping rm = (RequestMapping) method.getAnnotation(RequestMapping.class);  
                        //获取其中的value
                        String rmvalue = rm.value();  
                        //拼接成对应的url"/" + ctvalue + 
                        handerMap.put("/" + rmvalue, method);  
                    } else {  
                        continue;  
                    }  
                }  
            } else {  
                continue;  
            }  
  
        }  
    }  
  
    private String replaceTo(String path) {  
        return path.replaceAll("\\.", "/");  
    }  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        this.doPost(req, resp);  
    }  
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        String url = req.getRequestURI();  
        String context = req.getContextPath();  
        String path = url.replace(context, "");  
        Method method = (Method) handerMap.get("/" + path.split("/")[2]);  
        Object controller = (Object) instanceMap.get(path.split("/")[1]);  
        try {  
            method.invoke(controller, new Object[] {req, resp});  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            e.printStackTrace();  
        }  
    }  
  
} 