package indi.uhyils.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月16日 17时04分
 */
public class ShowDocUtil {

    private static final List<String> NUMBERS = new ArrayList<>();

    static {
        NUMBERS.add("Integer");
        NUMBERS.add("Double");
        NUMBERS.add("Float");
        NUMBERS.add("Long");
    }

    public static void main(String[] args) throws Exception {
        initDoc();
    }

    public static void initDoc() throws Exception {
        File directory = new File("");
        String courseFile = directory.getCanonicalPath() + "/my-api";
        File apiDir = new File(courseFile);
        File[] files = apiDir.listFiles();
        for (File projectDir : files) {
            LogUtil.info(ShowDocUtil.class, "最外层: " + projectDir.getPath());
            if (projectDir.isDirectory()) {
                String s = projectDir.getPath() + "/src/main/java/indi/uhyils/service";
                File[] services = new File(s).listFiles();
                for (File service : services) {
                    LogUtil.info(ShowDocUtil.class, "中层的service: " + service.getPath());
                    if (service.exists() && service.isFile()) {
                        String path = service.getPath();
                        String substring = path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
                        Class<?> aClass = Class.forName("indi.uhyils.service." + substring);
                        Method[] declaredMethods = aClass.getDeclaredMethods();
                        for (Method declaredMethod : declaredMethods) {
                            LogUtil.info(ShowDocUtil.class, "内层的method: " + declaredMethod.getName());
                            Class parameterType = declaredMethod.getParameterTypes()[0];
                            // 返回值的属性
                            Class returnClass = declaredMethod.getReturnType();
                            Type returnType = declaredMethod.getGenericReturnType();
                            Type genericParameterType = declaredMethod.getGenericParameterTypes()[0];
                            Type actualTypeArgument = null;
                            if (returnType instanceof ParameterizedType) {
                                Type[] actualTypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
                                //因为list泛型只有一个值 所以直接取0下标
                                actualTypeArgument = actualTypeArguments[0];
                                String typeName = actualTypeArgument.getTypeName();
                                //真实返回值类型 Class对象
                                if (typeName.contains("<")) {
                                    typeName = typeName.substring(0, typeName.indexOf("<"));
                                }
                                returnClass = Class.forName(typeName);
                            }
                            // 参数json
                            String paramJson = getJson(parameterType, new ArrayList<>(), false, genericParameterType);
                            String resultJson = getJson(returnClass, new ArrayList<>(), false, actualTypeArgument);

                            String paramList = getList(paramJson);
                            String resultList = getList(resultJson);


                            String html = new StringBuilder()
                                    .append("\n")
                                    .append("\n")
                                    .append("    \n")
                                    .append("##### 简要描述\n")
                                    .append("\n")
                                    .append("- \n")
                                    .append("\n")
                                    .append("##### 接口默认信息\n")
                                    .append("- 见默认接口说明\n")
                                    .append("\n")
                                    .append("| 参数名称 |   参数类型|参数值|\n")
                                    .append("| :------------: | :------------: |:------------: |\n")
                                    .append("| interfaceName |  string|")
                                    .append(aClass.getSimpleName())
                                    .append("|\n")
                                    .append("|  methodName |  string |")
                                    .append(declaredMethod.getName())
                                    .append("|\n")
                                    .append("\n")
                                    .append("\n")
                                    .append("##### 请求参数 \n")
                                    .append("```\n")
                                    .append(paramJson)
                                    .append("```\n")
                                    .append(paramList)
                                    .append("\n")
                                    .append("\n")
                                    .append("##### 返回参数\n")
                                    .append("```\n")
                                    .append(resultJson)
                                    .append("```\n")
                                    .append(resultList).toString();
                            ApiContent apiContent = new ApiContent();
                            apiContent.setApi_key("bc973c7dea684a306ad7f4c3df5feb581341237566");
                            apiContent.setApi_token("1f48fe4aea0dc96599f6f7ca5ebe0eaa1211797159");
                            apiContent.setCat_name("接口api/" + projectDir.getName().substring(projectDir.getName().lastIndexOf("-") + 1) + "/" + aClass.getSimpleName());
                            apiContent.setPage_title(declaredMethod.getName());
                            apiContent.setPage_content(html);
                            String resultApi = JSON.toJSONString(apiContent);
                            sendHttpToShowDoc(JSON.parseObject(resultApi));
                        }
                    }
                }
            }
        }
    }

    private static void sendHttpToShowDoc(JSONObject data) throws Exception {

        String url = "http://39.98.164.91:4999/server/index.php?s=/api/item/updateByApi";
        HashMap<String, Object> head = new HashMap<>(1);
        head.put("ContentType", "application/json");
        Object o = HttpUtil.sendHttpPost(url, head, data);
        System.out.println(o.toString());
    }

    /**
     * 获取json的列表->showDoc中的参数表格
     *
     * @param paramJson 参数的json
     * @return 可以展示为list的字符串
     */
    private static String getList(String paramJson) {
        String list = "##### 参数列表\n";
        list += "|参数|类型|描述|\n" +
                "|:-------|:-------|:-------|\n";
        if ("{}".equalsIgnoreCase(paramJson)) {
            return list + String.format("| %s | %s | 无 |\n", "不确定参数", "object");
        } else if ("2".equalsIgnoreCase(paramJson)) {
            return list + String.format("| %s | %s | 无 |\n", "唯一返回参数", "number");
        } else if ("\"String\"".equalsIgnoreCase(paramJson)) {
            return list + String.format("| %s | %s | 无 |\n", "唯一返回参数", "string");
        } else if ("[]".equalsIgnoreCase(paramJson)) {
            return list + String.format("| %s | %s | 无 |\n", "唯一返回数组参数", "array");
        } else if ("true".equalsIgnoreCase(paramJson)) {
            return list + String.format("| %s | %s | 无 |\n", "唯一返回参数", "bool");
        } else if (paramJson.startsWith("[")) {
            paramJson = paramJson.substring(1, paramJson.length() - 1);
            return getList(paramJson);
        }


        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(paramJson);
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String type = entry.getValue().getClass().getSimpleName();
                list += String.format("| %s | %s | 无 |\n", entry.getKey(), type);
            }
        } catch (Exception e) {
            System.out.println(paramJson);
            LogUtil.error(ShowDocUtil.class, e);
        }


        return list;
    }

    /**
     * 获取class对应的json
     *
     * @param clazz               要变成的类
     * @param lastClass           已经用过的class
     * @param recursionSuperClass 是否递归父类
     * @return
     * @throws Exception
     */
    private static String getJson(Class clazz, List<Class> lastClass, Boolean recursionSuperClass, Type lastClassType) throws Exception {
        String simpleName = clazz.getSimpleName();
        if (lastClass.contains(clazz) || "Object".equalsIgnoreCase(simpleName) || "Serializable".equalsIgnoreCase(simpleName)) {
            return "{}";
        } else if ("String".equalsIgnoreCase(simpleName)) {
            return "\"" + simpleName + "\"";
        } else if ("Boolean".equalsIgnoreCase(simpleName)) {
            return "true";
        } else if ("List".equalsIgnoreCase(simpleName) || "ArrayList".equalsIgnoreCase(simpleName) || "LinkedList".equalsIgnoreCase(simpleName)) {
            if (lastClassType != null) {
                String typeName = lastClassType.getTypeName();
                typeName = typeName.substring(typeName.indexOf("<") + 1, typeName.lastIndexOf(">"));
                if (typeName.indexOf("<") != -1) {
                    typeName = typeName.substring(0, typeName.lastIndexOf("<"));
                }
                String json = getJson(Class.forName(typeName), lastClass, recursionSuperClass, null);
                return "[" + json + "]";
            }
            return "[]";

        } else if (simpleName.endsWith("[]")) {
            // 进来的是一个数组类型
            return "[]";
        } else if (NUMBERS.contains(simpleName)) {
            return "2";
        } else if (simpleName.contains("Map")) {
            return "{}";
        } else {
            List<Class> classes = getSuperClass(clazz);
            List<Field> declaredFields = new ArrayList<>();
            // 如果要递归父类
            if (recursionSuperClass) {
                for (Class aClass : classes) {
                    declaredFields.addAll(Arrays.asList(aClass.getDeclaredFields()));
                }
            } else {
                declaredFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            }

            lastClass.add(clazz);
            StringBuilder sb = new StringBuilder();
            sb
                    .append("{");
            for (Field declaredField : declaredFields) {
                Class<?> type = declaredField.getType();
                sb
                        .append("\"")
                        .append(declaredField.getName())
                        .append("\":");
                sb
                        .append(getJson(type, lastClass, true, null));
                sb
                        .append(",");
            }
            if (declaredFields.size() != 0) {
                sb.delete(sb.length() - 1, sb.length());
            }
            sb
                    .append("}");
            return jsonFormat(sb.toString());
        }
    }

    /**
     * 获取一个class包含自己的所有父类
     *
     * @param clazz clazz
     * @return
     */
    public static List<Class> getSuperClass(Class clazz) {
        List<Class> listSuperClass = new ArrayList<>();
        listSuperClass.add(clazz);
        Class superclass = clazz.getSuperclass();
        while (superclass != null) {
            if ("java.lang.Object".equals(superclass.getName())) {
                break;
            }
            listSuperClass.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return listSuperClass;
    }

    /**
     * 格式化json
     *
     * @param jsonString json
     * @return 格式化后的json
     */
    public static String jsonFormat(String jsonString) {
        JSONObject object = JSONObject.parseObject(jsonString);
        jsonString = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        return jsonString;
    }

    static class ApiContent {
        private String api_key;
        private String api_token;
        private String cat_name;
        private String page_title;
        private String page_content;

        public String getApi_key() {
            return api_key;
        }

        public void setApi_key(String api_key) {
            this.api_key = api_key;
        }

        public String getApi_token() {
            return api_token;
        }

        public void setApi_token(String api_token) {
            this.api_token = api_token;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getPage_title() {
            return page_title;
        }

        public void setPage_title(String page_title) {
            this.page_title = page_title;
        }

        public String getPage_content() {
            return page_content;
        }

        public void setPage_content(String page_content) {
            this.page_content = page_content;
        }
    }
}
