package indi.uhyils.util.kpro;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.enum_.KeyTypeEnum;
import indi.uhyils.pojo.request.DbInformation;
import indi.uhyils.pojo.tool.ColumnInfo;
import indi.uhyils.pojo.tool.TableInfo;
import indi.uhyils.util.LogUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * 项目生成模块功能实现
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 07时19分
 */
public class KproUtil {
    private static List<String> fatherFiled = new ArrayList<>();

    static {
        fatherFiled.add("id");
        fatherFiled.add("create_date");
        fatherFiled.add("create_user");
        fatherFiled.add("delete_flag");
        fatherFiled.add("remark");
        fatherFiled.add("update_date");
        fatherFiled.add("update_user");

    }

    /**
     * 生成mysql项目
     *
     * @param dbInformation 数据库信息
     * @param dateFormat    现在的日期 字符串形式 yyyy年MM月dd日 hh时mm分
     * @return mysql生成的文件
     */
    public static HashMap<String, String> getMySqlKpro(DbInformation dbInformation, String dateFormat) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = dbInformation.getUrl();
            String userName = dbInformation.getUserName();
            String password = dbInformation.getPassword();
            conn = DriverManager.getConnection(url, userName, password);
            HashMap<String, TableInfo> tableInfos = getStringTableInfoHashMap(dbInformation, conn);

            return getFileHashMapByTableInfos(tableInfos, dbInformation, dateFormat, DbTypeEnum.MYSQL);
        } catch (Exception e) {
            LogUtil.error(KproUtil.class, e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LogUtil.error(e.getMessage());
                }
            }
        }
        return new HashMap<>(0);
    }

    /**
     * 获取最终hashMap
     *
     * @param tableInfos    <table名称,table数据>
     * @param dbInformation
     * @param dateFormat
     * @return map<相对路径 + 名称, 文件内容>
     */
    private static HashMap<String, String> getFileHashMapByTableInfos(HashMap<String, TableInfo> tableInfos, DbInformation dbInformation, String dateFormat, DbTypeEnum typeEnum) {
        HashMap<String, String> result = new HashMap<>(16);
        String apiPath = "my-api/my-api-provider-" + dbInformation.getSmallProjectName();
        String servicePath = "my-service/my-service-" + dbInformation.getSmallProjectName();
        for (Map.Entry<String, TableInfo> stringTableInfoEntry : tableInfos.entrySet()) {
            result.putAll(Objects.requireNonNull(createPojo(apiPath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat, typeEnum)));
            result.putAll(Objects.requireNonNull(createDao(servicePath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat, typeEnum)));
            result.putAll(Objects.requireNonNull(createMapper(servicePath + "/src/main/resources", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat)));
            result.putAll(Objects.requireNonNull(createService(apiPath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat)));
            result.putAll(Objects.requireNonNull(createServiceImpl(servicePath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat)));
            result.putAll(Objects.requireNonNull(createHtml(servicePath + "/src/main/resources/static/page", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat)));

        }
        result.putAll(Objects.requireNonNull(createOther(apiPath, servicePath, dbInformation, dateFormat, typeEnum)));
        return result;
    }

    private static HashMap<String, String> createOther(String apiPath, String servicePath, DbInformation dbInformation, String dateFormat, DbTypeEnum typeEnum) {
        HashMap<String, String> result = new HashMap<>(10);
        /*api pom.xml*/
        result.putAll(createApiPom(apiPath, dbInformation));
        /*service pom.xml*/
        result.putAll(createServicePom(servicePath, dbInformation, typeEnum));
        /*service Dockerfile*/
        result.putAll(createDockerfile(servicePath, dbInformation));
        /*service application.yml*/
        result.putAll(createApplicationYml(servicePath, dbInformation));
        /*service banner.txt*/
        result.putAll(createBanner(servicePath));
        /*service logback-spring.xml*/
        result.putAll(createLogBack(servicePath));
        /*service Application.java*/
        result.putAll(createApplication(servicePath, dbInformation, dateFormat));
        /*jvm启动参数*/
        result.putAll(createStartJvmParam(servicePath, dbInformation));
        return result;
    }

    private static Map<? extends String, ? extends String> createHtml(String filepath, String className, TableInfo tableInfo, String dateFormat) {
        String tableHtml = filepath + "/" + className + ".html";
        String detailHtml = filepath + "/table/" + className + "Add.html";
        HashMap<String, String> result = new HashMap<>(2);
        StringBuilder tableHtmlSb = new StringBuilder();
        StringBuilder detailHtmlSb = new StringBuilder();

        StringBuilder cols = new StringBuilder();
        tableInfo.getColums().entrySet().forEach(t -> {
            ColumnInfo value = t.getValue();
            String smallName = value.getSmallName();
            String remark = value.getRemark();
            if (StringUtils.isEmpty(remark)) {
                return;
            }
            cols.append("                            {field: '" + smallName + "', title: '" + remark + "'},\n");
        });
        StringBuilder detailCols = new StringBuilder();
        tableInfo.getColums().entrySet().forEach(t -> {
            ColumnInfo value = t.getValue();
            String smallName = value.getSmallName();
            String remark = value.getRemark();
            if (StringUtils.isEmpty(remark)) {
                return;
            }
            detailCols.append("    <div class=\"layui-form-item\">\n        <label class=\"layui-form-label required\">").append(remark).append("</label>\n        <div class=\"layui-input-block\">\n            <input type=\"text\" name=\"").append(smallName).append("\" lay-verify=\"required\" placeholder=\"请输入").append(remark).append("\" value=\"\"\n                   class=\"layui-input\">\n        </div>\n    </div>\n").toString();
        });

        StringBuilder detailEditCols = new StringBuilder();
        tableInfo.getColums().entrySet().forEach(t -> {
            ColumnInfo value = t.getValue();
            String smallName = value.getSmallName();
            String remark = value.getRemark();
            if (StringUtils.isEmpty(remark)) {
                return;
            }
            detailEditCols.append("                $('[name=\"" + smallName + "\"]').attr('value', data." + smallName + ");\n");
        });

        tableHtmlSb.append("<!DOCTYPE html>\n<html>\n<head>\n    <meta charset=\"utf-8\">\n    <title>layui</title>\n    <meta name=\"renderer\" content=\"webkit\">\n    <!--设置1个小时的缓存-->\n    <meta http-equiv=\"Cache-Control\" content=\"max-age=3200\"/>\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n    <link rel=\"stylesheet\" href=\"../lib/layui-v2.5.5/css/layui.css\" media=\"all\">\n    <link rel=\"stylesheet\" href=\"../css/public.css\" media=\"all\">\n</head>\n<body><div class=\"layuimini-container\">\n    <div class=\"layuimini-main\">\n\n        <!--<fieldset class=\"table-search-fieldset\">\n            <legend>定时任务管理</legend>\n            <div style=\"margin: 10px 10px 10px 10px\">\n                <form class=\"layui-form layui-form-pane\" action=\"\">\n                    <div class=\"layui-form-item\">\n                        <div class=\"layui-inline\">\n                            <label class=\"layui-form-label\">接口名称</label>\n                            <div class=\"layui-input-inline\">\n                                <select id=\"interfaceName\" name=\"interfaceName\" lay-verify=\"\"\n                                        lay-filter=\"interfaceName\">\n                                    <option value=\"\">请选择一个接口</option>\n                                </select>\n                            </div>\n                        </div>\n                        <div class=\"layui-inline\">\n                            <button type=\"submit\" class=\"layui-btn layui-btn-primary\" lay-submit\n                                    lay-filter=\"data-search-btn\"><i class=\"layui-icon\">\uE615</i> 搜 索\n                            </button>\n                        </div>\n                    </div>\n                </form>\n            </div>\n        </fieldset>-->\n\n\n        <table class=\"layui-hide\" id=\"currentTableId\" lay-filter=\"currentTableId\"></table>\n\n        <div id=\"page-change\"></div>\n    </div>\n</div>\n<script type=\"text/html\" id=\"toolbarDemo\">\n    <div class=\"layui-btn-container\">\n        <button class=\"layui-btn layui-btn-sm\" lay-event=\"getCheckData\">获取选中行数据</button>\n        <button class=\"layui-btn layui-btn-sm\" lay-event=\"getCheckLength\">获取选中数目</button>\n        <button class=\"layui-btn layui-btn-sm\" lay-event=\"isAll\">验证是否全选</button>\n        <button class=\"layui-btn layui-btn-warm\" lay-event=\"addNewEntity\" style=\"border-radius: 15px\">添加新").append(tableInfo.getTableComment()).append("</button>\n    </div>\n</script>\n\n<script type=\"text/html\" id=\"barDemo\">\n    <a class=\"layui-btn layui-btn-xs\" lay-event=\"edit\">编辑</a>\n    <a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"del\">删除</a>\n</script>\n<script src=\"../lib/layui-v2.5.5/layui.js\" charset=\"utf-8\"></script>\n<script src=\"/js/jquery.min.js\" charset=\"utf-8\"></script>\n<script src=\"/js/my.js\" charset=\"utf-8\"></script>\n<script>\n    layui.use(['form', 'table', 'layer', 'laypage'], function () {\n        var $ = layui.jquery,\n            form = layui.form,\n            laypage = layui.laypage,\n            layer = layui.layer,\n            table = layui.table;\n\n        $.reloadTable = function () {\n            var reload = function (page, size, args) {\n                pushRequest(\"").append(className).append("Service\", \"getByArgs\", {\n                    page: page,\n                    size: size,\n                    paging: true,\n                    args: args\n                }, function (data) {\n                    table.render({\n                        elem: '#currentTableId',\n                        // url: '/action',\n                        data: data.list,\n                        toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板\n                        page: false,\n                        cols: [[\n                            {type: \"numbers\", title: '序号'},\n").append(cols.toString()).append("                            {fixed: 'right', title: '操作', minWidth: 255, toolbar: '#barDemo'}\n                        ]],\n                        skin: 'line'\n                    });\n\n                    laypage.render({\n                        elem: 'page-change'                 //这是元素的id，不能写成\"#pageTest\"\n                        , count: data.count             //数据总数\n                        , limit: data.size                      //每页显示条数\n                        , limits: [10, 20, 30, 40, 50]\n                        , curr: page //获取起始页\n                        , hash: 'fenye' //自定义hash值\n                        , groups: 5                      //连续页码个数\n                        , prev: '上一页'                 //上一页文本\n                        , next: '下一页'                 //下一页文本\n                        , first: '首页'                      //首页文本\n                        , last: '尾页'                     //尾页文本\n                        , layout: ['prev', 'page', 'next', 'limit', 'refresh', 'skip']\n                        , jump: function (obj, first) {\n                            //首次不执行\n                            if (!first) {\n                                let serializeJson = $(\".layui-form\").serializeJson();\n                                var args = []\n                                $.each(serializeJson, function (key, value) {\n                                    if (value != \"\") {\n                                        var arg = {};\n                                        arg.name = key;\n                                        arg.symbol = '=';\n                                        arg.data = value;\n                                        args.push(arg);\n                                    }\n                                });\n                                reload(obj.curr, obj.limit, args);\n                            }\n\n                        }\n                    });\n\n\n                    //头工具栏事件\n                    table.on('toolbar(currentTableId)', function (obj) {\n                        var checkStatus = table.checkStatus(obj.config.id);\n                        switch (obj.event) {\n                            case 'getCheckData':\n                                var data = checkStatus.data;\n                                layer.alert(JSON.stringify(data));\n                                break;\n                            case 'getCheckLength':\n                                var data = checkStatus.data;\n                                layer.msg('选中了：' + data.length + ' 个');\n                                break;\n                            case 'isAll':\n                                layer.msg(checkStatus.isAll ? '全选' : '未全选');\n                                break;\n                            case 'addNewEntity': // 添加新" + tableInfo.getTableComment() + "\n                                var index = layer.open({\n                                    title: '添加" + tableInfo.getTableComment() + "',\n                                    type: 2,\n                                    shade: 0.2,\n                                    maxmin: true,\n                                    shadeClose: true,\n                                    area: ['30%', '40%'],\n                                    content: 'table/").append(className).append("Add.html?func=add',\n                                });\n                                $(window).on(\"resize\", function () {\n                                    layer.full(index);\n                                });\n                                break;\n\n                            //自定义头工具栏右侧图标 - 提示\n                            case 'LAYTABLE_TIPS':\n                                layer.alert('这是工具栏右侧自定义的一个图标按钮');\n                                break;\n                        }\n                    });\n\n                    //监听行工具事件\n                    table.on('tool(currentTableId)', function (obj) {\n                        var data = obj.data;\n                        if (obj.event === 'del') { // 删除\n                            layer.confirm('真的删除行么', function (index) {\n                                pushRequest(\"").append(className).append("Service\", \"delete\", {id: data.id}, function (data) {\n                                    layer.close(index);\n                                    $.reloadTable();\n                                });\n                            });\n                        } else if (obj.event === 'edit') {\n                            var index = layer.open({\n                                title: '添加" + tableInfo.getTableComment() + "',\n                                type: 2,\n                                shade: 0.2,\n                                maxmin: true,\n                                shadeClose: true,\n                                area: ['30%', '40%'],\n                                content: 'table/").append(className).append("Add.html?func=edit&id=' + data.id,\n                            });\n                            $(window).on(\"resize\", function () {\n                                layer.full(index);\n                            });\n                        }\n                    });\n\n                })\n                // 监听搜索操作\n                form.on('submit(data-search-btn)', function (data) {\n                    var result = JSON.stringify(data.field);\n                    layer.alert(result, {\n                        title: '最终的搜索信息'\n                    });\n                    let serializeJson = $(\".layui-form\").serializeJson();\n                    var args = []\n                    $.each(serializeJson, function (key, value) {\n                        if (value != \"\") {\n                            var arg = {};\n                            arg.name = key;\n                            arg.symbol = '=';\n                            arg.data = value;\n                            args.push(arg);\n                        }\n                    });\n                    reload(1, 10, args);\n\n                    return false;\n                });\n            };\n\n\n            reload(1, 10, [\n                {\n                    name: \"1\",\n                    symbol: \"=\",\n                    data: \"1\"\n                }\n            ]);\n        };\n\n        $.reloadTable();\n\n\n        //监听表格复选框选择\n        table.on('checkbox(currentTableFilter)', function (obj) {\n            console.log(obj)\n        });\n\n    });\n</script>\n\n</body>\n</html>\n");
        detailHtmlSb.append("<!DOCTYPE html>\n<html>\n<head>\n    <meta charset=\"utf-8\">\n    <title>layui</title>\n    <meta name=\"renderer\" content=\"webkit\">\n    <!--设置1个小时的缓存-->\n    <meta http-equiv=\"Cache-Control\" content=\"max-age=3200\"/>\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n    <link rel=\"stylesheet\" href=\"../../lib/layui-v2.5.5/css/layui.css\" media=\"all\">\n    <link rel=\"stylesheet\" href=\"../../css/public.css\" media=\"all\">\n    <style>\n        body {\n            background-color: #ffffff;\n        }\n\n        .layui-transfer-box {\n            overflow: auto;\n        }\n    </style>\n</head>\n<body>\n<div class=\"layui-form layuimini-form\">\n    <!--  父id    -->\n    <input type=\"text\" name=\"id\" value=\"\" style=\"display:none;\">\n\n").append(detailCols.toString()).append("\n\n    <div class=\"layui-form-item\">\n        <div class=\"layui-input-block\">\n            <button class=\"layui-btn layui-btn-normal\" lay-submit lay-filter=\"saveBtn\">确认保存</button>\n        </div>\n    </div>\n</div>\n<script src=\"/js/jquery.min.js\"></script>\n<script src=\"/js/my.js\"></script>\n<script src=\"../../lib/layui-v2.5.5/layui.js\" charset=\"utf-8\"></script>\n<script>\n    layui.use(['form'], function () {\n        var form = layui.form,\n            layer = layui.layer,\n            $ = layui.$;\n\n        $.getUrlParam = function (name) {\n            var reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\");\n            var r = window.location.search.substr(1).match(reg);\n            if (r != null) return unescape(r[2]);\n            return null;\n        };\n        let id = $.getUrlParam('id');\n        let func = $.getUrlParam('func');\n\n        if (func == 'edit') { // 这是修改\n            pushRequest(\"").append(className).append("Service\", \"getById\", {\n                id: id\n            }, function (data) {\n                $('[name=\"id\"]').attr('value', data.id);\n").append(detailEditCols.toString()).append("                form.render();\n            })\n        }\n\n        var closeAndReload = function () {\n            var iframeIndex = parent.layer.getFrameIndex(window.name);\n            parent.$.reloadTable();\n            parent.layer.close(iframeIndex);\n        };\n        //监听提交\n        form.on('submit(saveBtn)', function (data) {\n            var index = layer.alert(JSON.stringify(data.field), {\n                title: '最终的提交信息'\n            }, function () {\n                // 关闭弹出层\n                layer.close(index);\n                if (func === 'add') {\n                    pushRequest(\"").append(className).append("Service\", \"insert\", {data: data.field}, function () {\n                    });\n                    closeAndReload();\n                } else if (func === 'edit') {\n                    pushRequest(\"").append(className).append("Service\", \"update\", {data: data.field}, function () {\n                    });\n                    closeAndReload();\n                }\n            });\n            return false;\n        });\n\n    });\n</script>\n</body>\n</html>\n");
        result.put(tableHtml, tableHtmlSb.toString());
        result.put(detailHtml, detailHtmlSb.toString());
        return result;
    }

    private static Map<String, String> createStartJvmParam(String servicePath, DbInformation dbInformation) {
        String fileName = servicePath + "/jvmStartParam.txt";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("-XX:+UseG1GC -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/my/logs/").append(dbInformation.getSmallProjectName()).append("/JVM_DOWN.dump -Xms512m -Xmx512m -XX:NewRatio=1 -Xloggc:d:/my/logs/").append(dbInformation.getSmallProjectName()).append("/JVM.log ");
        sb.append("--spring.profiles.active=dev");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createApplication(String servicePath, DbInformation dbInformation, String dateFormat) {
        String fileName = servicePath + "/src/main/java/indi/uhyils/" + dbInformation.getBigProjectName() + "Application.java";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("package indi.uhyils;\n\nimport indi.uhyils.util.SpringUtil;\nimport org.apache.dubbo.config.spring.context.annotation.EnableDubbo;\nimport org.springframework.boot.SpringApplication;\nimport org.springframework.boot.autoconfigure.SpringBootApplication;\nimport org.springframework.context.ApplicationContext;\nimport org.springframework.transaction.annotation.EnableTransactionManagement;\n\n/**\n * @author uhyils <247452312@qq.com>\n * @date 文件创建日期 " + dateFormat + "\n */\n\n\n@SpringBootApplication\n@EnableDubbo\n@EnableTransactionManagement\npublic class ").append(dbInformation.getBigProjectName()).append("Application {\n    public static void main(String[] args) {\n        SpringApplication.run(").append(dbInformation.getBigProjectName()).append("Application.class, args);\n    }\n}\n");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createLogBack(String servicePath) {
        String fileName = servicePath + "/src/main/resources/logback-spring.xml";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!-- 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出 -->\n<!-- scan:当此属性设置为true时，配置文档如果发生改变，将会被重新加载，默认值为true -->\n<!-- scanPeriod:设置监测配置文档是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。\n                 当scan为true时，此属性生效。默认的时间间隔为1分钟。 -->\n<!-- debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->\n<configuration scan=\"true\" scanPeriod=\"10 seconds\">\n    <contextName>logback</contextName>\n\n    <!-- 获取yml中的log地址 -->\n    <springProperty scope=\"context\" name=\"log.path\" source=\"log-out.path\" defaultValue=\"/usr/my/logs\"/>\n    <springProperty scope=\"context\" name=\"log.dir.name\" source=\"log-out.dir-name\" defaultValue=\"defaultDir\"/>\n\n    <!--0. 日志格式和颜色渲染 -->\n    <!-- 彩色日志依赖的渲染类 -->\n    <conversionRule conversionWord=\"clr\" converterClass=\"org.springframework.boot.logging.logback.ColorConverter\"/>\n    <conversionRule conversionWord=\"wex\"\n                    converterClass=\"org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter\"/>\n    <conversionRule conversionWord=\"wEx\"\n                    converterClass=\"org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter\"/>\n    <!-- 彩色日志格式 -->\n    <property name=\"CONSOLE_LOG_PATTERN\"\n              value=\"${CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}\"/>\n\n    <!--1. 输出到控制台-->\n    <appender name=\"CONSOLE\" class=\"ch.qos.logback.core.ConsoleAppender\">\n        <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->\n        <filter class=\"ch.qos.logback.classic.filter.ThresholdFilter\">\n            <level>debug</level>\n        </filter>\n        <encoder>\n            <Pattern>${CONSOLE_LOG_PATTERN}</Pattern>\n            <!-- 设置字符集 -->\n            <charset>UTF-8</charset>\n        </encoder>\n    </appender>\n\n    <!--2. 输出到文档-->\n    <!-- 2.1 level为 DEBUG 日志，时间滚动输出  -->\n    <appender name=\"DEBUG_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n        <!-- 正在记录的日志文档的路径及文档名 -->\n        <file>${log.path}/${log.dir.name}/debug.log</file>\n        <!--日志文档输出格式-->\n        <encoder>\n            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n            <charset>UTF-8</charset> <!-- 设置字符集 -->\n        </encoder>\n        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n            <!-- 日志归档 -->\n            <fileNamePattern>${log.path}/${log.dir.name}/debug-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n                <maxFileSize>100MB</maxFileSize>\n            </timeBasedFileNamingAndTriggeringPolicy>\n            <!--日志文档保留天数-->\n            <maxHistory>15</maxHistory>\n        </rollingPolicy>\n        <!-- 此日志文档只记录debug级别的 -->\n        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n            <level>debug</level>\n            <onMatch>ACCEPT</onMatch>\n            <onMismatch>DENY</onMismatch>\n        </filter>\n    </appender>\n\n    <!-- 2.2 level为 INFO 日志，时间滚动输出  -->\n    <appender name=\"INFO_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n        <!-- 正在记录的日志文档的路径及文档名 -->\n        <file>${log.path}/${log.dir.name}/info.log</file>\n        <!--日志文档输出格式-->\n        <encoder>\n            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n            <charset>UTF-8</charset>\n        </encoder>\n        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n            <!-- 每天日志归档路径以及格式 -->\n            <fileNamePattern>${log.path}/${log.dir.name}/info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n                <maxFileSize>100MB</maxFileSize>\n            </timeBasedFileNamingAndTriggeringPolicy>\n            <!--日志文档保留天数-->\n            <maxHistory>15</maxHistory>\n        </rollingPolicy>\n        <!-- 此日志文档只记录info级别的 -->\n        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n            <level>info</level>\n            <onMatch>ACCEPT</onMatch>\n            <onMismatch>DENY</onMismatch>\n        </filter>\n    </appender>\n\n    <!-- 2.3 level为 WARN 日志，时间滚动输出  -->\n    <appender name=\"WARN_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n        <!-- 正在记录的日志文档的路径及文档名 -->\n        <file>${log.path}/${log.dir.name}/warn.log</file>\n        <!--日志文档输出格式-->\n        <encoder>\n            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n            <charset>UTF-8</charset> <!-- 此处设置字符集 -->\n        </encoder>\n        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n            <fileNamePattern>${log.path}/${log.dir.name}/warn-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n                <maxFileSize>100MB</maxFileSize>\n            </timeBasedFileNamingAndTriggeringPolicy>\n            <!--日志文档保留天数-->\n            <maxHistory>15</maxHistory>\n        </rollingPolicy>\n        <!-- 此日志文档只记录warn级别的 -->\n        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n            <level>warn</level>\n            <onMatch>ACCEPT</onMatch>\n            <onMismatch>DENY</onMismatch>\n        </filter>\n    </appender>\n\n    <!-- 2.4 level为 ERROR 日志，时间滚动输出  -->\n    <appender name=\"ERROR_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n        <!-- 正在记录的日志文档的路径及文档名 -->\n        <file>${log.path}/${log.dir.name}/error.log</file>\n        <!--日志文档输出格式-->\n        <encoder>\n            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n            <charset>UTF-8</charset> <!-- 此处设置字符集 -->\n        </encoder>\n        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n            <fileNamePattern>${log.path}/${log.dir.name}/error-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n                <maxFileSize>100MB</maxFileSize>\n            </timeBasedFileNamingAndTriggeringPolicy>\n            <!--日志文档保留天数-->\n            <maxHistory>15</maxHistory>\n        </rollingPolicy>\n        <!-- 此日志文档只记录ERROR级别的 -->\n        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n            <level>ERROR</level>\n            <onMatch>ACCEPT</onMatch>\n            <onMismatch>DENY</onMismatch>\n        </filter>\n    </appender>\n\n    <!--\n        <logger>用来设置某一个包或者具体的某一个类的日志打印级别、\n        以及指定<appender>。<logger>仅有一个name属性，\n        一个可选的level和一个可选的addtivity属性。\n        name:用来指定受此logger约束的某一个包或者具体的某一个类。\n        level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，\n              还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。\n              如果未设置此属性，那么当前logger将会继承上级的级别。\n        addtivity:是否向上级logger传递打印信息。默认是true。\n        <logger name=\"org.springframework.web\" level=\"info\"/>\n        <logger name=\"org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor\" level=\"INFO\"/>\n    -->\n\n    <!--\n        使用mybatis的时候，sql语句是debug下才会打印，而这里我们只配置了info，所以想要查看sql语句的话，有以下两种操作：\n        第一种把<root level=\"info\">改成<root level=\"DEBUG\">这样就会打印sql，不过这样日志那边会出现很多其他消息\n        第二种就是单独给dao下目录配置debug模式，代码如下，这样配置sql语句会打印，其他还是正常info级别：\n        【logging.level.org.mybatis=debug logging.level.dao=debug】\n     -->\n\n    <!--\n        root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性\n        level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，\n        不能设置为INHERITED或者同义词NULL。默认是DEBUG\n        可以包含零个或多个元素，标识这个appender将会添加到这个logger。\n    -->\n\n    <!-- 4. 最终的策略 -->\n    <!-- 4.1 开发环境:打印控制台-->\n    <springProfile name=\"dev\">\n        <logger name=\"com.sdcm.pmp\" level=\"debug\"/>\n    </springProfile>\n\n    <root level=\"info\">\n        <appender-ref ref=\"CONSOLE\"/>\n        <appender-ref ref=\"DEBUG_FILE\"/>\n        <appender-ref ref=\"INFO_FILE\"/>\n        <appender-ref ref=\"WARN_FILE\"/>\n        <appender-ref ref=\"ERROR_FILE\"/>\n    </root>\n\n    <!-- 4.2 生产环境:输出到文档\n    <springProfile name=\"pro\">\n        <root level=\"info\">\n            <appender-ref ref=\"CONSOLE\" />\n            <appender-ref ref=\"DEBUG_FILE\" />\n            <appender-ref ref=\"INFO_FILE\" />\n            <appender-ref ref=\"ERROR_FILE\" />\n            <appender-ref ref=\"WARN_FILE\" />\n        </root>\n    </springProfile> -->\n\n</configuration>\n");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createBanner(String servicePath) {
        String fileName = servicePath + "/src/main/resources/banner.txt";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("                        _           _ _\n                        | |         (_) |\n _ __ ___  _   _   _   _| |__  _   _ _| |___\n| '_ ` _ \\| | | | | | | | '_ \\| | | | | / __|\n| | | | | | |_| | | |_| | | | | |_| | | \\__ \\\n|_| |_| |_|\\__, |  \\__,_|_| |_|\\__, |_|_|___/\n            __/ |               __/ |\n           |___/               |___/\n");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createApplicationYml(String servicePath, DbInformation dbInformation) {
        String filePathDev = servicePath + "/src/main/resources/application-dev.yml";
        String filePathTest = servicePath + "/src/main/resources/application-test.yml";
        String filePathRelease = servicePath + "/src/main/resources/application-release.yml";
        String filePathProd = servicePath + "/src/main/resources/application-prod.yml";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder dev = new StringBuilder();
        StringBuilder test = new StringBuilder();
        StringBuilder release = new StringBuilder();
        StringBuilder prod = new StringBuilder();
        dev.append("server:\n  port: " + dbInformation.getPort() + "\ndubbo:\n  metadata-report:\n    address: nacos://192.168.1.101:8848\n  registry:\n    client: curator\n    address: nacos://192.168.1.101:8848\n  protocol:\n    name: dubbo\n    port: " + (dbInformation.getPort() + 12800) + "\n    threadpool: cached\n  application:\n    name: provider-").append(dbInformation.getSmallProjectName()).append("\n  consumer:\n    timeout: 5000\n    group: ${spring.profiles.active}\n  provider:\n    timeout: 5000\n    group: ${spring.profiles.active}\n\nlog-out:\n  path: D:/my/logs\n  dir-name: ").append(dbInformation.getSmallProjectName()).append("\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: indi.uhyils.dao\nlogging:\n  level:\n    indi.uhyils.dao: DEBUG\ntoken:\n  salt: my\n  encodeRules: my\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: ").append(dbInformation.getUrl()).append("\n    username: ").append(dbInformation.getUserName()).append("\n    password: ").append(dbInformation.getPassword()).append("\n    type: com.alibaba.druid.pool.DruidDataSource\n    # 初始化物理连接个数\n    initial-size: 1\n    # 最大连接池数量\n    max-active: 20\n    # 最小连接池数量\n    min-idle: 5\n    # 获取连接时最大等待时间(ms)\n    max-wait: 60000\n    # 开启缓存preparedStatement(PSCache)\n    pool-prepared-statements: true\n    # 启用PSCache后，指定每个连接上PSCache的大小\n    max-pool-prepared-statement-per-connection-size: 20\n    # 用来检测连接是否有效的sql\n    validation-query: select 'x'\n    # 申请连接时不检测连接是否有效\n    test-on-borrow: false\n    # 归还连接时不检测连接是否有效\n    test-on-return: false\n    # 申请连接时检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效（不影响性能）\n    test-while-idle: true\n    # 检测连接的间隔时间，若连接空闲时间 >= minEvictableIdleTimeMillis，则关闭物理连接\n    time-between-eviction-runs-millis: 60000\n    # 连接保持空闲而不被驱逐的最小时间(ms)\n    min-evictable-idle-time-millis: 300000\n    # 配置监控统计拦截的filters（不配置则监控界面sql无法统计），监控统计filter:stat，日志filter:log4j，防御sql注入filter:wall\n    filters: stat,log4j,wall,tableSqlFilter\n    # 支持合并多个DruidDataSource的监控数据\n    use-global-data-source-stat: true\n    # 通过connectProperties属性来打开mergeSql(Sql合并)功能；慢SQL记录(配置超过5秒就是慢，默认是3秒)\n    connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\n  output:\n    ansi:\n      enabled: ALWAYS\n  devtools:\n    restart:\n      enabled: true  #设置开启热部署\n      additional-paths: src/main/java #重启目录\n      exclude: WEB-INF/**\n    freemarker:\n      cache: false    #页面不加载缓存，修改即时生效\n\nredis:\n  normal:\n    ip: 192.168.1.101\n    port: 6379\n    password: uhyils-single\n  hotspot:\n    hosts: 192.168.1.101:26380;192.168.1.101:26381;192.168.1.101:26382\n    password: uhyils\n    sentinels: mymaster\nrabbit:\n  host: 192.168.1.101\n  port: 5672\n  username: uhyils\n  password: 123456");
        test.append("server:\n  port: " + (dbInformation.getPort() + 50) + "\ndubbo:\n  metadata-report:\n    address: nacos://192.168.1.101:8848\n  registry:\n    client: curator\n    address: nacos://192.168.1.101:8848\n  protocol:\n    name: dubbo\n    port: " + (dbInformation.getPort() + 12850) + "\n    threadpool: cached\n  application:\n    name: provider-").append(dbInformation.getSmallProjectName()).append("\n  consumer:\n    timeout: 3000\n    group: ${spring.profiles.active}\n  provider:\n    timeout: 3000\n    group: ${spring.profiles.active}\n\nlog-out:\n  path: D:/my/logs\n  dir-name: ").append(dbInformation.getSmallProjectName()).append("\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: indi.uhyils.dao\nlogging:\n  level:\n    indi.uhyils.dao: DEBUG\ntoken:\n  salt: my\n  encodeRules: my\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: ").append(dbInformation.getUrl()).append("\n    username: ").append(dbInformation.getUserName()).append("\n    password: ").append(dbInformation.getPassword()).append("\n    type: com.alibaba.druid.pool.DruidDataSource\n    # 初始化物理连接个数\n    initial-size: 1\n    # 最大连接池数量\n    max-active: 20\n    # 最小连接池数量\n    min-idle: 5\n    # 获取连接时最大等待时间(ms)\n    max-wait: 60000\n    # 开启缓存preparedStatement(PSCache)\n    pool-prepared-statements: true\n    # 启用PSCache后，指定每个连接上PSCache的大小\n    max-pool-prepared-statement-per-connection-size: 20\n    # 用来检测连接是否有效的sql\n    validation-query: select 'x'\n    # 申请连接时不检测连接是否有效\n    test-on-borrow: false\n    # 归还连接时不检测连接是否有效\n    test-on-return: false\n    # 申请连接时检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效（不影响性能）\n    test-while-idle: true\n    # 检测连接的间隔时间，若连接空闲时间 >= minEvictableIdleTimeMillis，则关闭物理连接\n    time-between-eviction-runs-millis: 60000\n    # 连接保持空闲而不被驱逐的最小时间(ms)\n    min-evictable-idle-time-millis: 300000\n    # 配置监控统计拦截的filters（不配置则监控界面sql无法统计），监控统计filter:stat，日志filter:log4j，防御sql注入filter:wall\n    filters: stat,log4j,wall,tableSqlFilter\n    # 支持合并多个DruidDataSource的监控数据\n    use-global-data-source-stat: true\n    # 通过connectProperties属性来打开mergeSql(Sql合并)功能；慢SQL记录(配置超过5秒就是慢，默认是3秒)\n    connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\n  output:\n    ansi:\n      enabled: ALWAYS\n  devtools:\n    restart:\n      enabled: true  #设置开启热部署\n      additional-paths: src/main/java #重启目录\n      exclude: WEB-INF/**\n    freemarker:\n      cache: false    #页面不加载缓存，修改即时生效\n\nredis:\n  normal:\n    ip: 192.168.1.101\n    port: 6379\n    password: uhyils-single\n  hotspot:\n    hosts: 192.168.1.101:26380;192.168.1.101:26381;192.168.1.101:26382\n    password: uhyils\n    sentinels: mymaster\nrabbit:\n  host: 192.168.1.101\n  port: 5672\n  username: uhyils\n  password: 123456");
        release.append("server:\n  port: " + dbInformation.getPort() + "\ndubbo:\n  metadata-report:\n    address: nacos://172.26.59.129:8848\n  registry:\n    client: curator\n    address: nacos://172.26.59.129:8848\n  protocol:\n    name: dubbo\n    port: " + (dbInformation.getPort() + 12800) + "\n    threadpool: cached\n  application:\n    name: provider-").append(dbInformation.getSmallProjectName()).append("\n  consumer:\n    timeout: 3000\n    group: ${spring.profiles.active}\n  provider:\n    timeout: 3000\n    group: ${spring.profiles.active}\n\nlog-out:\n  path: D:/my/logs\n  dir-name: ").append(dbInformation.getSmallProjectName()).append("\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: indi.uhyils.dao\nlogging:\n  level:\n    indi.uhyils.dao: DEBUG\ntoken:\n  salt: my\n  encodeRules: my\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: ").append(dbInformation.getUrl()).append("\n    username: ").append(dbInformation.getUserName()).append("\n    password: ").append(dbInformation.getPassword()).append("\n    type: com.alibaba.druid.pool.DruidDataSource\n    # 初始化物理连接个数\n    initial-size: 1\n    # 最大连接池数量\n    max-active: 20\n    # 最小连接池数量\n    min-idle: 5\n    # 获取连接时最大等待时间(ms)\n    max-wait: 60000\n    # 开启缓存preparedStatement(PSCache)\n    pool-prepared-statements: true\n    # 启用PSCache后，指定每个连接上PSCache的大小\n    max-pool-prepared-statement-per-connection-size: 20\n    # 用来检测连接是否有效的sql\n    validation-query: select 'x'\n    # 申请连接时不检测连接是否有效\n    test-on-borrow: false\n    # 归还连接时不检测连接是否有效\n    test-on-return: false\n    # 申请连接时检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效（不影响性能）\n    test-while-idle: true\n    # 检测连接的间隔时间，若连接空闲时间 >= minEvictableIdleTimeMillis，则关闭物理连接\n    time-between-eviction-runs-millis: 60000\n    # 连接保持空闲而不被驱逐的最小时间(ms)\n    min-evictable-idle-time-millis: 300000\n    # 配置监控统计拦截的filters（不配置则监控界面sql无法统计），监控统计filter:stat，日志filter:log4j，防御sql注入filter:wall\n    filters: stat,log4j,wall,tableSqlFilter\n    # 支持合并多个DruidDataSource的监控数据\n    use-global-data-source-stat: true\n    # 通过connectProperties属性来打开mergeSql(Sql合并)功能；慢SQL记录(配置超过5秒就是慢，默认是3秒)\n    connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\n  output:\n    ansi:\n      enabled: ALWAYS\n  devtools:\n    restart:\n      enabled: true  #设置开启热部署\n      additional-paths: src/main/java #重启目录\n      exclude: WEB-INF/**\n    freemarker:\n      cache: false    #页面不加载缓存，修改即时生效\n\nredis:\n  ip: 172.26.59.129\n  port: 6379\n  password: uhyils\n\nrabbit:\n  host: 172.26.59.129\n  port: 5672\n  username: uhyils\n  password: 123456\n");
        prod.append("server:\n  port: " + (dbInformation.getPort() + 50) + "\ndubbo:\n  metadata-report:\n    address: nacos://172.26.59.129:8848\n  registry:\n    client: curator\n    address: nacos://172.26.59.129:8848\n  protocol:\n    name: dubbo\n    port: " + (dbInformation.getPort() + 12850) + "\n    threadpool: cached\n  application:\n    name: provider-").append(dbInformation.getSmallProjectName()).append("\n  consumer:\n    timeout: 3000\n    group: ${spring.profiles.active}\n  provider:\n    timeout: 3000\n    group: ${spring.profiles.active}\n\nlog-out:\n  path: D:/my/logs\n  dir-name: ").append(dbInformation.getSmallProjectName()).append("\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: indi.uhyils.dao\nlogging:\n  level:\n    indi.uhyils.dao: DEBUG\ntoken:\n  salt: my\n  encodeRules: my\nspring:\n  datasource:\n    driver-class-name: com.mysql.jdbc.Driver\n    url: ").append(dbInformation.getUrl()).append("\n    username: ").append(dbInformation.getUserName()).append("\n    password: ").append(dbInformation.getPassword()).append("\n    type: com.alibaba.druid.pool.DruidDataSource\n    # 初始化物理连接个数\n    initial-size: 1\n    # 最大连接池数量\n    max-active: 20\n    # 最小连接池数量\n    min-idle: 5\n    # 获取连接时最大等待时间(ms)\n    max-wait: 60000\n    # 开启缓存preparedStatement(PSCache)\n    pool-prepared-statements: true\n    # 启用PSCache后，指定每个连接上PSCache的大小\n    max-pool-prepared-statement-per-connection-size: 20\n    # 用来检测连接是否有效的sql\n    validation-query: select 'x'\n    # 申请连接时不检测连接是否有效\n    test-on-borrow: false\n    # 归还连接时不检测连接是否有效\n    test-on-return: false\n    # 申请连接时检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效（不影响性能）\n    test-while-idle: true\n    # 检测连接的间隔时间，若连接空闲时间 >= minEvictableIdleTimeMillis，则关闭物理连接\n    time-between-eviction-runs-millis: 60000\n    # 连接保持空闲而不被驱逐的最小时间(ms)\n    min-evictable-idle-time-millis: 300000\n    # 配置监控统计拦截的filters（不配置则监控界面sql无法统计），监控统计filter:stat，日志filter:log4j，防御sql注入filter:wall\n    filters: stat,log4j,wall,tableSqlFilter\n    # 支持合并多个DruidDataSource的监控数据\n    use-global-data-source-stat: true\n    # 通过connectProperties属性来打开mergeSql(Sql合并)功能；慢SQL记录(配置超过5秒就是慢，默认是3秒)\n    connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\n  output:\n    ansi:\n      enabled: ALWAYS\n  devtools:\n    restart:\n      enabled: true  #设置开启热部署\n      additional-paths: src/main/java #重启目录\n      exclude: WEB-INF/**\n    freemarker:\n      cache: false    #页面不加载缓存，修改即时生效\n\nredis:\n  ip: 172.26.59.129\n  port: 6379\n  password: uhyils\n\nrabbit:\n  host: 172.26.59.129\n  port: 5672\n  username: uhyils\n  password: 123456\n");

        result.put(filePathDev, dev.toString());
        result.put(filePathTest, test.toString());
        result.put(filePathRelease, release.toString());
        result.put(filePathProd, prod.toString());
        return result;
    }

    private static Map<String, String> createDockerfile(String servicePath, DbInformation dbInformation) {
        String filePath = servicePath + "/Dockerfile";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("# Docker image for {tomcat? springBoot?}\n# VERSION {版本号}\n# Author: uhyils\n# docker stop prod-my-service-user\n# docker rm prod-my-service-user\n# docker rmi my-service/prod-my-service-user:latest\n# docker build --build-arg version=0.0.7-my-SNAPSHOT --build-arg runEnv=prod -t my-service/prod-my-service-user .\n# docker run -it -d  -p ").append(dbInformation.getPort() + 50).append(":").append(dbInformation.getPort() + 50).append(" -p 20852").append(dbInformation.getPort() + 12850).append(":").append(dbInformation.getPort() + 12850).append(" -v /my/logs:/my/logs --name prod-my-service-user my-service/prod-my-service-user:latest\n\nFROM java:8\n\n#作者\nMAINTAINER uhyils <uhyils@qq.com>\n\n\n#挂载\nVOLUME /my/logs\n\n#暴露端口\nEXPOSE ").append(dbInformation.getPort() + 50).append(" ").append(dbInformation.getPort() + 12850).append("\n\n#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar\nADD target/my-*.jar /app.jar\n\n#启动容器时的进程\nENTRYPOINT [\"java\",\"-Xms512m\",\"-Xmx512m\",\"-XX:+PrintGCTimeStamps\",\"-XX:+UseG1GC\",\"-XX:+HeapDumpOnOutOfMemoryError\",\"-XX:HeapDumpPath=/my/logs/").append(dbInformation.getSmallProjectName()).append("/JVM_DOWN.dump\",\"-XX:NewRatio=1\",\"-Xloggc:/my/logs/").append(dbInformation.getSmallProjectName()).append("/JVM.log\",\"-jar\",\"/app.jar\",\"--spring.profiles.active=prod\"]\n");
        result.put(filePath, sb.toString());
        return result;
    }

    private static Map<String, String> createServicePom(String servicePath, DbInformation dbInformation, DbTypeEnum typeEnum) {
        String filePath = servicePath + "/pom.xml";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n    <parent>\n        <artifactId>my-service</artifactId>\n        <groupId>indi.uhyils</groupId>\n        <version>0.0.7-my-SNAPSHOT</version>\n    </parent>\n    <modelVersion>4.0.0</modelVersion>\n    <version>0.0.7-my-SNAPSHOT</version>\n    <artifactId>my-service-").append(dbInformation.getSmallProjectName()).append("</artifactId>\n    <packaging>jar</packaging>\n\n    <dependencyManagement>\n        <dependencies>\n            <dependency>\n                <!-- Import dependency management from Spring Boot -->\n                <groupId>org.springframework.boot</groupId>\n                <artifactId>spring-boot-dependencies</artifactId>\n                <version>${spring-boot.version}</version>\n                <type>pom</type>\n                <scope>import</scope>\n            </dependency>\n        </dependencies>\n    </dependencyManagement>\n\n    <dependencies>\n\n        <dependency>\n            <groupId>indi.uhyils</groupId>\n            <artifactId>my-common-base</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>indi.uhyils</groupId>\n            <artifactId>my-common-service</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>indi.uhyils</groupId>\n            <artifactId>my-api-provider-").append(dbInformation.getSmallProjectName()).append("</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>indi.uhyils</groupId>\n            <artifactId>my-api-provider-user</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n\n");
        switch (typeEnum) {
            case MYSQL:
                sb.append("        <dependency>\n            <groupId>mysql</groupId>\n            <artifactId>mysql-connector-java</artifactId>\n        </dependency>\n");
                break;
            case ORACLE:
                sb.append("<dependency>\n    <groupId>com.oracle</groupId>\n    <artifactId>ojdbc6</artifactId>\n</dependency>");
                break;
            case SQLITE:
                sb.append("<!-- sqlitejdbc -->\n<dependency>\n    <groupId>sqlitejdbc</groupId>\n    <artifactId>sqlitejdbc</artifactId>\n</dependency>\n");
                break;
            default:
                break;
        }
        sb.append("\n\n        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-test</artifactId>\n            <scope>test</scope>\n        </dependency>\n\n\n        <dependency>\n            <groupId>com.alibaba.boot</groupId>\n            <artifactId>velocity-spring-boot-starter</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>org.apache.commons</groupId>\n            <artifactId>commons-lang3</artifactId>\n        </dependency>\n\n        <!-- dubbo -->\n        <dependency>\n            <groupId>org.apache.dubbo</groupId>\n            <artifactId>dubbo-spring-boot-starter</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.apache.dubbo</groupId>\n            <artifactId>dubbo</artifactId>\n        </dependency>\n\n\n        <!--curator-->\n        <dependency>\n            <groupId>org.apache.curator</groupId>\n            <artifactId>curator-framework</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.apache.curator</groupId>\n            <artifactId>curator-recipes</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>com.alibaba</groupId>\n            <artifactId>fastjson</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>org.apache.dubbo</groupId>\n            <artifactId>dubbo-registry-nacos</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>com.alibaba.nacos</groupId>\n            <artifactId>nacos-client</artifactId>\n        </dependency>\n\n    </dependencies>\n    <build>\n        <plugins>\n            <plugin>\n                <groupId>org.springframework.boot</groupId>\n                <artifactId>spring-boot-maven-plugin</artifactId>\n                <version>${spring-boot.version}</version>\n                <configuration>\n                    <mainClass>indi.uhyils.").append(dbInformation.getBigProjectName()).append("Application</mainClass>\n                </configuration>\n                <executions>\n                    <execution>\n                        <goals>\n                            <goal>repackage</goal>\n                        </goals>\n                    </execution>\n                </executions>\n            </plugin>\n            <plugin>\n                <groupId>org.apache.maven.plugins</groupId>\n                <artifactId>maven-compiler-plugin</artifactId>\n                <version>${maven.compiler.plugin.version}</version>\n                <configuration>\n                    <source>1.8</source>\n                    <target>1.8</target>\n                </configuration>\n            </plugin>\n        </plugins>\n    </build>\n</project>\n");
        result.put(filePath, sb.toString());
        return result;
    }

    private static HashMap<String, String> createApiPom(String apiPath, DbInformation dbInformation) {
        String filePath = apiPath + "/pom.xml";
        StringBuilder sb = new StringBuilder().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n    <parent>\n        <artifactId>my-api</artifactId>\n        <groupId>indi.uhyils</groupId>\n        <version>0.0.7-my-SNAPSHOT</version>\n    </parent>\n    <modelVersion>4.0.0</modelVersion>\n    <version>0.0.7-my-SNAPSHOT</version>\n    <artifactId>my-api-provider-").append(dbInformation.getSmallProjectName()).append("</artifactId>\n    <dependencies>\n        <dependency>\n            <groupId>indi.uhyils</groupId>\n            <artifactId>my-common-base</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>indi.uhyils</groupId>\n            <artifactId>my-common-service</artifactId>\n        </dependency>\n\n    </dependencies>\n\n</project>\n");
        HashMap<String, String> result = new HashMap<>(1);
        result.put(filePath, sb.toString());
        return result;
    }

    private static HashMap<String, String> createServiceImpl(String filepath, String className, TableInfo tableInfo, String dateFormat) {
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/serviceImpl/" + className + "ServiceImpl.java";
        StringBuilder sb = new StringBuilder().append("package indi.uhyils.serviceImpl;\n\nimport org.apache.dubbo.config.annotation.Service;\nimport indi.uhyils.annotation.NoToken;\nimport indi.uhyils.content.Content;\nimport indi.uhyils.dao.").append(className).append("Dao;\nimport indi.uhyils.enum_.ServiceCode;\nimport indi.uhyils.pojo.model.*;\nimport indi.uhyils.service.").append(className).append("Service;\nimport org.springframework.beans.factory.annotation.Autowired;\n\n/**\n * @author uhyils <247452312@qq.com>\n * @date 文件创建日期 ").append(dateFormat).append("\n */\n@Service(group = \"${spring.profiles.active}\")\npublic class ").append(className).append("ServiceImpl extends BaseDefaultServiceImpl<").append(className).append("Entity> implements ").append(className).append("Service {\n\n    @Autowired\n    private ").append(className).append("Dao dao;\n\n\n    public ").append(className).append("Dao getDao() {\n        return dao;\n    }\n\n    public void setDao(").append(className).append("Dao dao) {\n        this.dao = dao;\n    }\n}\n");
        result.put(fileAllName, sb.toString());
        return result;
    }

    private static HashMap<String, String> createService(String filepath, String className, TableInfo tableInfo, String dateFormat) {
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/service/" + className + "Service.java";
        StringBuilder sb = new StringBuilder();
        sb.append("package indi.uhyils.service;\n\nimport indi.uhyils.pojo.model.*;\nimport indi.uhyils.service.base.DefaultEntityService;\n\n/**\n *\n * @author uhyils <247452312@qq.com>\n * @date 文件创建日期 ").append(dateFormat).append("\n */\npublic interface ").append(className).append("Service extends DefaultEntityService<").append(className).append("Entity> {\n\n\n}\n");
        result.put(fileAllName, sb.toString());
        return result;
    }

    private static HashMap<String, String> createDao(String filepath, String className, TableInfo tableInfo, String dateFormat, DbTypeEnum prase) {
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/dao/" + className + "Dao.java";
        StringBuilder sb = new StringBuilder();
        sb.append("package indi.uhyils.dao;\n\n");
        sb.append("import indi.uhyils.pojo.model.*;\n");
        sb.append("import indi.uhyils.dao.base.DefaultDao;\n");
        sb.append("import org.apache.ibatis.annotations.Mapper;\n\n");
        sb.append("/**\n *\n * @author uhyils <247452312@qq.com>\n * @date 文件创建日期 ").append(dateFormat).append("\n */\n");
        sb.append("@Mapper\n");
        sb.append("public interface ");
        sb.append(className);
        sb.append("Dao extends DefaultDao<");
        sb.append(className);
        sb.append("Entity> {\n\n\n");
        sb.append("}");
        result.put(fileAllName, sb.toString());
        return result;
    }

    private static HashMap<String, String> createMapper(String filepath, String className, TableInfo tableInfo, String dateFormat) {
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/mapper/" + className + "DaoMapper.xml";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n\n<mapper namespace=\"indi.uhyils.dao.").append(className).append("Dao\">\n\n\n    <sql id=\"columnList\">\n");
        for (Map.Entry<String, ColumnInfo> entry : tableInfo.getColums().entrySet()) {
            String columnInfo = entry.getKey();
            sb.append("\t\ta.").append(columnInfo).append(" as ").append(entry.getValue().getSmallName()).append(",\n");
        }
        // 删除最后一个逗号
        sb.delete(sb.length() - 2, sb.length());
        sb.append("\n    </sql>\n\n    <select id=\"getById\" resultType=\"indi.uhyils.pojo.model.").append(className).append("Entity\">\n        select\n        <include refid=\"columnList\"/>\n        from ");
        sb.append(tableInfo.getTableName()).append(" a\n        where delete_flag = 0\n        and ").append(tableInfo.getOnlyKey().getName());
        sb.append(" = #{").append(tableInfo.getOnlyKey().getSmallName()).append("}\n    </select>\n\n    <select id=\"getByArgsNoPage\" resultType=\"indi.uhyils.pojo.model.").append(className);
        sb.append("Entity\">\n        select\n        <include refid=\"columnList\"/>\n        from ").append(tableInfo.getTableName());
        sb.append(" a\n        where delete_flag = 0\n        <foreach item=\"item\" index=\"index\" collection=\"list\">\n            and ${item.name} ${item.symbol} #{item.data}\n        </foreach>\n    </select>\n\n    <select id=\"getByArgs\" resultType=\"indi.uhyils.pojo.model.").append(className);
        sb.append("Entity\">\n        select\n        <include refid=\"columnList\"/>\n        from ").append(tableInfo.getTableName());
        sb.append(" a\n        where delete_flag = 0\n\n        <foreach item=\"item\" index=\"index\" collection=\"args\">\n            and ${item.name} ${item.symbol} #{item.data}\n        </foreach>\n        <if test=\"page != null and page != ''\">\n            limit ${(page-1) * size},${page * size}\n        </if>\n\n    </select>\n\n\n    <insert id=\"insert\" parameterType=\"indi.uhyils.pojo.model.").append(className);
        sb.append("Entity\">\n        insert ").append(tableInfo.getTableName()).append("(");

        for (Map.Entry<String, ColumnInfo> entry : tableInfo.getColums().entrySet()) {
            sb.append(entry.getValue().getName()).append(",\n\t\t");
        }
        // 删除最后一个逗号
        sb.delete(sb.length() - 4, sb.length());

        sb.append(")\n" +
                "        values (");
        for (Map.Entry<String, ColumnInfo> entry : tableInfo.getColums().entrySet()) {
            sb.append("#{").append(entry.getValue().getSmallName()).append("},\n\t\t");
        }
        // 删除最后一个逗号
        sb.delete(sb.length() - 4, sb.length());
        sb.append(")\n    </insert>\n\n    <update id=\"update\" parameterType=\"indi.uhyils.pojo.model.").append(className);
        sb.append("Entity\">\n        update ").append(tableInfo.getTableName()).append(" set\n");

        for (Map.Entry<String, ColumnInfo> entry : tableInfo.getColums().entrySet()) {
            // 不是主键
            if (entry.getValue().getKeyType() != KeyTypeEnum.KEY.getType()) {
                sb.append("        <if test=\"").append(entry.getValue().getSmallName());
                sb.append(" != null and ").append(entry.getValue().getSmallName());
                sb.append(" != ''\">\n            ,").append(entry.getValue().getName());
                sb.append(" = #{").append(entry.getValue().getSmallName());
                sb.append("}\n        </if>\n");
            }

        }
        sb.append("        where ").append(tableInfo.getOnlyKey().getName()).append(" = #{").append(tableInfo.getOnlyKey().getSmallName()).append("}\n    </update>\n\n    <select id=\"count\" resultType=\"java.lang.Integer\">\n        select count(*)\n        from ").append(tableInfo.getTableName()).append(" a\n        where delete_flag = 0;\n    </select>\n\n    <select id=\"countByArgs\" resultType=\"java.lang.Integer\">\n        select count(*)\n        from ").append(tableInfo.getTableName()).append(" a\n        where delete_flag = 0\n        <foreach item=\"arg\" index=\"index\" collection=\"args\">\n            and ${arg.name} ${arg.symbol} #{arg.data}\n        </foreach>\n    </select>\n\n    <select id=\"checkRepeat\" resultType=\"java.lang.Integer\">\n        select count(*)\n        from ").append(tableInfo.getTableName()).append(" a\n        where ${columnName} = #{value}\n    </select>\n\n\n</mapper>\n");
        result.put(fileAllName, sb.toString());
        return result;
    }

    private static Map<String, String> createPojo(String filepath, String className, TableInfo tableInfo, String dateFormat, DbTypeEnum typeEnum) {
        TypeConvertor typeConvertor = TypeConvertorFactory.getTypeConvertor(typeEnum);
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/pojo/model/" + className + "Entity.java";
        StringBuilder sb = new StringBuilder();
        List<JavaPojoFileGetAndSet> list = new ArrayList();

        //遍历字段,获取字段的类型,名称,是否主键
        Map<String, ColumnInfo> colums = tableInfo.getColums();
        for (Map.Entry<String, ColumnInfo> stringColumnInfoEntry : colums.entrySet()) {
            // 字段名称
            String filedName = stringColumnInfoEntry.getKey();
            if (fatherFiled.contains(filedName)) {
                continue;
            }
            ColumnInfo value = stringColumnInfoEntry.getValue();
            // 字段类型
            String dataType = value.getDataType();
            // 转换后的字段类型
            String type = typeConvertor.databaseType2JavaType(dataType);
            JavaPojoFileGetAndSet javaPojoFileGetAndSet = new JavaPojoFileGetAndSet(filedName, type, stringColumnInfoEntry.getValue().getRemark());
            list.add(javaPojoFileGetAndSet);
        }
        //package
        sb.append("package indi.uhyils.pojo.model;\n\n");

        //import
        sb.append("import java.util.*;\n\n");
        sb.append("import indi.uhyils.pojo.model.base.BaseVoEntity;\n\n");
        sb.append("/**\n" +
                " *\n" +
                " * @author uhyils <247452312@qq.com>\n" +
                " * @date 文件创建日期 " + dateFormat + "\n" +
                " */\n");
        sb.append("public class ");
        sb.append(className);
        sb.append("Entity extends BaseVoEntity {\n\n");
        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String filedString = javaPojoFileGetAndSet.getFieldString();
            sb.append("\t");
            sb.append(filedString);
            sb.append("\n\n");
        }
        sb.append("\n");

        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String getMethod = javaPojoFileGetAndSet.getGetString();
            sb.append("\t");
            sb.append(getMethod);
            sb.append("\n\n");
        }
        sb.append("\n");

        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String setMethod = javaPojoFileGetAndSet.getSetString();
            sb.append("\t");
            sb.append(setMethod);
            sb.append("\n\n");
        }
        sb.append("\n}");
        result.put(fileAllName, sb.toString());
        return result;
    }

    /**
     * 获取oracle项目
     *
     * @param dbInformation 数据库信息
     * @param dateFormat
     * @return oracle生成的文件
     */
    public static HashMap<String, String> getOracleKpro(DbInformation dbInformation, String dateFormat) {
        return null;
    }

    /**
     * 获取sqlite项目
     *
     * @param dbInformation 数据库信息
     * @param dateFormat
     * @return sqlite生成的文件
     */
    public static HashMap<String, String> getSqliteKpro(DbInformation dbInformation, String dateFormat) {
        return null;
    }

    /**
     * 获取项目所有其他的东西
     *
     * @return
     */
    public static Map<String, String> getOtherKpro() {
        return new HashMap<>(16);
    }

    /**
     * 获取table们的信息
     *
     * @param dbInformation 数据库信息
     * @param conn          数据库连接
     * @return 需要的表信息
     * @throws SQLException
     */
    private static HashMap<String, TableInfo> getStringTableInfoHashMap(DbInformation dbInformation, Connection conn) throws SQLException {
        DatabaseMetaData dmd;
        HashMap<String, TableInfo> stringTableInfoHashMap = new HashMap<>(16);
        /*获取数据库表们的信息*/
        dmd = conn.getMetaData();
        // 这里table可以使用正则,只要getTables允许
        for (String table : dbInformation.getTables()) {
            ResultSet rs = dmd.getTables(dbInformation.getDbName(), "%", table, new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = (String) rs.getObject("TABLE_NAME");
                String tableComment = (String) rs.getObject("REMARKS");
                if (stringTableInfoHashMap.containsKey(tableName)) {
                    continue;
                }
                /*获取table信息*/
                TableInfo ti = new TableInfo(tableName, tableComment, new HashMap<>(16), new ColumnInfo());
                stringTableInfoHashMap.put(tableName, ti);
                ResultSet rs2 = dmd.getColumns(null, "%", tableName, "%");
                while (rs2.next()) {
                    ColumnInfo ci = new ColumnInfo(rs2.getString("TYPE_NAME"), 0, rs2.getString("COLUMN_NAME"));
                    String bigName = KproStringUtil.dealDbNameToJavaFileName(ci.getName());
                    String smallName = bigName.substring(0, 1).toLowerCase() + bigName.substring(1);
                    ci.setBigName(bigName);
                    ci.setSmallName(smallName);
                    ci.setRemark(rs2.getString("REMARKS"));
                    ti.getColums().put(rs2.getString("COLUMN_NAME"), ci);
                }
                rs2.close();

                /*获取唯一字段信息*/
                ResultSet rs3 = dmd.getPrimaryKeys(dbInformation.getDbName(), null, tableName);
                while (rs3.next()) {
                    ColumnInfo ci2 = ti.getColums().get(rs3.getString("COLUMN_NAME"));
                    ci2.setKeyType(1);
                    ti.setOnlyKey(ci2);
                }
                rs3.close();
            }
            rs.close();

        }
        return stringTableInfoHashMap;
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分");
        String dateFormat = simpleDateFormat.format(date);
        DbInformation dbInformation = new DbInformation();
        dbInformation.setUrl("jdbc:mysql://192.168.1.101:3306/my_log");
        dbInformation.setDbName("my_log");
        dbInformation.setProjectName("log");
        dbInformation.setType(1);
        dbInformation.setPort(8080);
        dbInformation.setUserName("root");
        dbInformation.setPassword("123456");
        ArrayList<String> tables = new ArrayList<>();
        tables.add("sys_%");
        dbInformation.setTables(tables);

        HashMap<String, String> mySqlKpro = getMySqlKpro(dbInformation, dateFormat);
    }
}
