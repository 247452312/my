package indi.uhyils.util.kpro;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.request.DbInformation;
import indi.uhyils.pojo.tool.ColumnInfo;
import indi.uhyils.pojo.tool.TableInfo;
import indi.uhyils.util.LogUtil;

import java.sql.*;
import java.util.*;

/**
 * 项目生成模块功能实现
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 07时19分
 */
public class KproUtil {
    /**
     * 生成mysql项目
     *
     * @param dbInformation 数据库信息
     * @param dateFormat
     * @return mysql生成的文件
     */
    public static HashMap<String, String> getMySqlKpro(DbInformation dbInformation, String dateFormat) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = dbInformation.getUrl();
            String userName = dbInformation.getUserName();
            String password = dbInformation.getPassword();
            Connection conn = DriverManager.getConnection(url, userName, password);
            HashMap<String, TableInfo> tableInfos = getStringTableInfoHashMap(dbInformation, conn);
            conn.close();
            return getFileHashMapByTableInfos(tableInfos, dbInformation, dateFormat, DbTypeEnum.MYSQL);
        } catch (Exception e) {
            LogUtil.error(KproUtil.class, e);
        }
        return new HashMap<>(0);
    }

    /**
     * 获取最终hashMap
     *
     * @param tableInfos    <table名称,table数据>
     * @param dbInformation
     * @param dateFormat
     * @return
     */
    private static HashMap<String, String> getFileHashMapByTableInfos(HashMap<String, TableInfo> tableInfos, DbInformation dbInformation, String dateFormat, DbTypeEnum typeEnum) {
        HashMap<String, String> result = new HashMap<>();
        String apiPath = "my-api/my-api-provider-" + dbInformation.getSmallProjectName();
        String servicePath = "my-service/my-service-" + dbInformation.getSmallProjectName();
        for (Map.Entry<String, TableInfo> stringTableInfoEntry : tableInfos.entrySet()) {
            result.putAll(Objects.requireNonNull(createPojo(apiPath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat, typeEnum)));
            result.putAll(Objects.requireNonNull(createDao(servicePath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat, typeEnum)));
            result.putAll(Objects.requireNonNull(createMapper(servicePath + "/src/main/resources", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat)));
            result.putAll(Objects.requireNonNull(createService(apiPath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat)));
            result.putAll(Objects.requireNonNull(createServiceImpl(servicePath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), dateFormat)));
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

    private static Map<String, String> createStartJvmParam(String servicePath, DbInformation dbInformation) {
        String fileName = servicePath + "/jvmStartParam.txt";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("-XX:+UseG1GC ").append("-XX:+PrintGCTimeStamps ").append("-XX:+HeapDumpOnOutOfMemoryError ").append("-XX:HeapDumpPath=d:/my/logs/").append(dbInformation.getSmallProjectName()).append("/JVM_DOWN.dump ").append("-Xms512m ").append("-Xmx512m ").append("-XX:NewRatio=1 ").append("-Xloggc:d:/my/logs/").append(dbInformation.getSmallProjectName()).append("/JVM.log ");
        sb.append("--spring.profiles.active=dev");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createApplication(String servicePath, DbInformation dbInformation, String dateFormat) {
        String fileName = servicePath + "/src/main/java/indi/uhyils/" + dbInformation.getBigProjectName() + "Application.java";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("package indi.uhyils;\n").append("\n").append("import indi.uhyils.util.SpringUtil;\n").append("import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;\n").append("import org.springframework.boot.SpringApplication;\n").append("import org.springframework.boot.autoconfigure.SpringBootApplication;\n").append("import org.springframework.context.ApplicationContext;\n").append("import org.springframework.transaction.annotation.EnableTransactionManagement;\n").append("\n").append("/**\n").append(" * @author uhyils <247452312@qq.com>\n").append(" * @date 文件创建日期 " + dateFormat + "\n").append(" */\n").append("\n").append("\n").append("@SpringBootApplication\n").append("@EnableDubbo\n").append("@EnableTransactionManagement\n").append("public class ").append(dbInformation.getBigProjectName()).append("Application {\n").append("    public static void main(String[] args) {\n").append("        ApplicationContext act = SpringApplication.run(").append(dbInformation.getBigProjectName()).append("Application.class, args);\n").append("        SpringUtil.setApplicationContext(act);\n").append("    }\n").append("}\n");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createLogBack(String servicePath) {
        String fileName = servicePath + "/src/main/resources/logback-spring.xml";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n").append("<!-- 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出 -->\n").append("<!-- scan:当此属性设置为true时，配置文档如果发生改变，将会被重新加载，默认值为true -->\n").append("<!-- scanPeriod:设置监测配置文档是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。\n").append("                 当scan为true时，此属性生效。默认的时间间隔为1分钟。 -->\n").append("<!-- debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->\n").append("<configuration scan=\"true\" scanPeriod=\"10 seconds\">\n").append("    <contextName>logback</contextName>\n").append("\n").append("    <!-- 获取yml中的log地址 -->\n").append("    <springProperty scope=\"context\" name=\"log.path\" source=\"log-out.path\" defaultValue=\"/usr/my/logs\"/>\n").append("    <springProperty scope=\"context\" name=\"log.dir.name\" source=\"log-out.dir-name\" defaultValue=\"defaultDir\"/>\n").append("\n").append("    <!--0. 日志格式和颜色渲染 -->\n").append("    <!-- 彩色日志依赖的渲染类 -->\n").append("    <conversionRule conversionWord=\"clr\" converterClass=\"org.springframework.boot.logging.logback.ColorConverter\"/>\n").append("    <conversionRule conversionWord=\"wex\"\n").append("                    converterClass=\"org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter\"/>\n").append("    <conversionRule conversionWord=\"wEx\"\n").append("                    converterClass=\"org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter\"/>\n").append("    <!-- 彩色日志格式 -->\n").append("    <property name=\"CONSOLE_LOG_PATTERN\"\n").append("              value=\"${CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}\"/>\n").append("\n").append("    <!--1. 输出到控制台-->\n").append("    <appender name=\"CONSOLE\" class=\"ch.qos.logback.core.ConsoleAppender\">\n").append("        <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->\n").append("        <filter class=\"ch.qos.logback.classic.filter.ThresholdFilter\">\n").append("            <level>debug</level>\n").append("        </filter>\n").append("        <encoder>\n").append("            <Pattern>${CONSOLE_LOG_PATTERN}</Pattern>\n").append("            <!-- 设置字符集 -->\n").append("            <charset>UTF-8</charset>\n").append("        </encoder>\n").append("    </appender>\n").append("\n").append("    <!--2. 输出到文档-->\n").append("    <!-- 2.1 level为 DEBUG 日志，时间滚动输出  -->\n").append("    <appender name=\"DEBUG_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n").append("        <!-- 正在记录的日志文档的路径及文档名 -->\n").append("        <file>${log.path}/${log.dir.name}/debug.log</file>\n").append("        <!--日志文档输出格式-->\n").append("        <encoder>\n").append("            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n").append("            <charset>UTF-8</charset> <!-- 设置字符集 -->\n").append("        </encoder>\n").append("        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n").append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n").append("            <!-- 日志归档 -->\n").append("            <fileNamePattern>${log.path}/${log.dir.name}/debug-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n").append("            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n").append("                <maxFileSize>100MB</maxFileSize>\n").append("            </timeBasedFileNamingAndTriggeringPolicy>\n").append("            <!--日志文档保留天数-->\n").append("            <maxHistory>15</maxHistory>\n").append("        </rollingPolicy>\n").append("        <!-- 此日志文档只记录debug级别的 -->\n").append("        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n").append("            <level>debug</level>\n").append("            <onMatch>ACCEPT</onMatch>\n").append("            <onMismatch>DENY</onMismatch>\n").append("        </filter>\n").append("    </appender>\n").append("\n").append("    <!-- 2.2 level为 INFO 日志，时间滚动输出  -->\n").append("    <appender name=\"INFO_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n").append("        <!-- 正在记录的日志文档的路径及文档名 -->\n").append("        <file>${log.path}/${log.dir.name}/info.log</file>\n").append("        <!--日志文档输出格式-->\n").append("        <encoder>\n").append("            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n").append("            <charset>UTF-8</charset>\n").append("        </encoder>\n").append("        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n").append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n").append("            <!-- 每天日志归档路径以及格式 -->\n").append("            <fileNamePattern>${log.path}/${log.dir.name}/info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n").append("            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n").append("                <maxFileSize>100MB</maxFileSize>\n").append("            </timeBasedFileNamingAndTriggeringPolicy>\n").append("            <!--日志文档保留天数-->\n").append("            <maxHistory>15</maxHistory>\n").append("        </rollingPolicy>\n").append("        <!-- 此日志文档只记录info级别的 -->\n").append("        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n").append("            <level>info</level>\n").append("            <onMatch>ACCEPT</onMatch>\n").append("            <onMismatch>DENY</onMismatch>\n").append("        </filter>\n").append("    </appender>\n").append("\n").append("    <!-- 2.3 level为 WARN 日志，时间滚动输出  -->\n").append("    <appender name=\"WARN_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n").append("        <!-- 正在记录的日志文档的路径及文档名 -->\n").append("        <file>${log.path}/${log.dir.name}/warn.log</file>\n").append("        <!--日志文档输出格式-->\n").append("        <encoder>\n").append("            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n").append("            <charset>UTF-8</charset> <!-- 此处设置字符集 -->\n").append("        </encoder>\n").append("        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n").append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n").append("            <fileNamePattern>${log.path}/${log.dir.name}/warn-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n").append("            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n").append("                <maxFileSize>100MB</maxFileSize>\n").append("            </timeBasedFileNamingAndTriggeringPolicy>\n").append("            <!--日志文档保留天数-->\n").append("            <maxHistory>15</maxHistory>\n").append("        </rollingPolicy>\n").append("        <!-- 此日志文档只记录warn级别的 -->\n").append("        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n").append("            <level>warn</level>\n").append("            <onMatch>ACCEPT</onMatch>\n").append("            <onMismatch>DENY</onMismatch>\n").append("        </filter>\n").append("    </appender>\n").append("\n").append("    <!-- 2.4 level为 ERROR 日志，时间滚动输出  -->\n").append("    <appender name=\"ERROR_FILE\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n").append("        <!-- 正在记录的日志文档的路径及文档名 -->\n").append("        <file>${log.path}/${log.dir.name}/error.log</file>\n").append("        <!--日志文档输出格式-->\n").append("        <encoder>\n").append("            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n").append("            <charset>UTF-8</charset> <!-- 此处设置字符集 -->\n").append("        </encoder>\n").append("        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->\n").append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n").append("            <fileNamePattern>${log.path}/${log.dir.name}/error-%d{yyyy-MM-dd}.%i.log</fileNamePattern>\n").append("            <timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\n").append("                <maxFileSize>100MB</maxFileSize>\n").append("            </timeBasedFileNamingAndTriggeringPolicy>\n").append("            <!--日志文档保留天数-->\n").append("            <maxHistory>15</maxHistory>\n").append("        </rollingPolicy>\n").append("        <!-- 此日志文档只记录ERROR级别的 -->\n").append("        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n").append("            <level>ERROR</level>\n").append("            <onMatch>ACCEPT</onMatch>\n").append("            <onMismatch>DENY</onMismatch>\n").append("        </filter>\n").append("    </appender>\n").append("\n").append("    <!--\n").append("        <logger>用来设置某一个包或者具体的某一个类的日志打印级别、\n").append("        以及指定<appender>。<logger>仅有一个name属性，\n").append("        一个可选的level和一个可选的addtivity属性。\n").append("        name:用来指定受此logger约束的某一个包或者具体的某一个类。\n").append("        level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，\n").append("              还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。\n").append("              如果未设置此属性，那么当前logger将会继承上级的级别。\n").append("        addtivity:是否向上级logger传递打印信息。默认是true。\n").append("        <logger name=\"org.springframework.web\" level=\"info\"/>\n").append("        <logger name=\"org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor\" level=\"INFO\"/>\n").append("    -->\n").append("\n").append("    <!--\n").append("        使用mybatis的时候，sql语句是debug下才会打印，而这里我们只配置了info，所以想要查看sql语句的话，有以下两种操作：\n").append("        第一种把<root level=\"info\">改成<root level=\"DEBUG\">这样就会打印sql，不过这样日志那边会出现很多其他消息\n").append("        第二种就是单独给dao下目录配置debug模式，代码如下，这样配置sql语句会打印，其他还是正常info级别：\n").append("        【logging.level.org.mybatis=debug logging.level.dao=debug】\n").append("     -->\n").append("\n").append("    <!--\n").append("        root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性\n").append("        level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，\n").append("        不能设置为INHERITED或者同义词NULL。默认是DEBUG\n").append("        可以包含零个或多个元素，标识这个appender将会添加到这个logger。\n").append("    -->\n").append("\n").append("    <!-- 4. 最终的策略 -->\n").append("    <!-- 4.1 开发环境:打印控制台-->\n").append("    <springProfile name=\"dev\">\n").append("        <logger name=\"com.sdcm.pmp\" level=\"debug\"/>\n").append("    </springProfile>\n").append("\n").append("    <root level=\"info\">\n").append("        <appender-ref ref=\"CONSOLE\"/>\n").append("        <appender-ref ref=\"DEBUG_FILE\"/>\n").append("        <appender-ref ref=\"INFO_FILE\"/>\n").append("        <appender-ref ref=\"WARN_FILE\"/>\n").append("        <appender-ref ref=\"ERROR_FILE\"/>\n").append("    </root>\n").append("\n").append("    <!-- 4.2 生产环境:输出到文档\n").append("    <springProfile name=\"pro\">\n").append("        <root level=\"info\">\n").append("            <appender-ref ref=\"CONSOLE\" />\n").append("            <appender-ref ref=\"DEBUG_FILE\" />\n").append("            <appender-ref ref=\"INFO_FILE\" />\n").append("            <appender-ref ref=\"ERROR_FILE\" />\n").append("            <appender-ref ref=\"WARN_FILE\" />\n").append("        </root>\n").append("    </springProfile> -->\n").append("\n").append("</configuration>\n");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createBanner(String servicePath) {
        String fileName = servicePath + "/src/main/resources/banner.txt";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("                        _           _ _\n").append("                        | |         (_) |\n").append(" _ __ ___  _   _   _   _| |__  _   _ _| |___\n").append("| '_ ` _ \\| | | | | | | | '_ \\| | | | | / __|\n").append("| | | | | | |_| | | |_| | | | | |_| | | \\__ \\\n").append("|_| |_| |_|\\__, |  \\__,_|_| |_|\\__, |_|_|___/\n").append("            __/ |               __/ |\n").append("           |___/               |___/\n");
        result.put(fileName, sb.toString());
        return result;
    }

    private static Map<String, String> createApplicationYml(String servicePath, DbInformation dbInformation) {
        String filePathDev = servicePath + "/src/main/resources/application-dev.yml";
        String filePathTest = servicePath + "/src/main/resources/application-test.yml";
        String filePathRelease = servicePath + "/src/main/resources/application-release.yml";
        String filePathProd = servicePath + "/src/main/resources/application-prod.yml";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("server:\n").append("  port: 8003\n").append("dubbo:\n").append("  metadata-report:\n").append("    address: nacos://192.168.1.101:8848\n").append("  registry:\n").append("    client: curator\n").append("    address: nacos://192.168.1.101:8848\n").append("  protocol:\n").append("    name: dubbo\n").append("    port: 20803\n").append("    threadpool: cached\n").append("  application:\n").append("    name: provider-").append(dbInformation.getSmallProjectName()).append("\n").append("  consumer:\n").append("    timeout: 3000\n").append("    group: ${spring.profiles.active}\n").append("  provider:\n").append("    timeout: 3000\n").append("    group: ${spring.profiles.active}\n").append("\n").append("log-out:\n").append("  path: D:/my/logs\n").append("  dir-name: ").append(dbInformation.getSmallProjectName()).append("\n").append("\n").append("mybatis:\n").append("  mapper-locations: classpath:mapper/*.xml\n").append("  type-aliases-package: indi.uhyils.dao\n").append("logging:\n").append("  level:\n").append("    indi.uhyils.dao: DEBUG\n").append("token:\n").append("  salt: my\n").append("  encodeRules: my\n").append("spring:\n").append("  datasource:\n").append("    driver-class-name: com.mysql.jdbc.Driver\n").append("    url: ").append(dbInformation.getUrl()).append("\n").append("    username: ").append(dbInformation.getUserName()).append("\n").append("    password: ").append(dbInformation.getPassword()).append("\n").append("    type: com.alibaba.druid.pool.DruidDataSource\n").append("    # 初始化物理连接个数\n").append("    initial-size: 1\n").append("    # 最大连接池数量\n").append("    max-active: 20\n").append("    # 最小连接池数量\n").append("    min-idle: 5\n").append("    # 获取连接时最大等待时间(ms)\n").append("    max-wait: 60000\n").append("    # 开启缓存preparedStatement(PSCache)\n").append("    pool-prepared-statements: true\n").append("    # 启用PSCache后，指定每个连接上PSCache的大小\n").append("    max-pool-prepared-statement-per-connection-size: 20\n").append("    # 用来检测连接是否有效的sql\n").append("    validation-query: select 'x'\n").append("    # 申请连接时不检测连接是否有效\n").append("    test-on-borrow: false\n").append("    # 归还连接时不检测连接是否有效\n").append("    test-on-return: false\n").append("    # 申请连接时检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效（不影响性能）\n").append("    test-while-idle: true\n").append("    # 检测连接的间隔时间，若连接空闲时间 >= minEvictableIdleTimeMillis，则关闭物理连接\n").append("    time-between-eviction-runs-millis: 60000\n").append("    # 连接保持空闲而不被驱逐的最小时间(ms)\n").append("    min-evictable-idle-time-millis: 300000\n").append("    # 配置监控统计拦截的filters（不配置则监控界面sql无法统计），监控统计filter:stat，日志filter:log4j，防御sql注入filter:wall\n").append("    filters: stat,log4j,wall\n").append("    # 支持合并多个DruidDataSource的监控数据\n").append("    use-global-data-source-stat: true\n").append("    # 通过connectProperties属性来打开mergeSql(Sql合并)功能；慢SQL记录(配置超过5秒就是慢，默认是3秒)\n").append("    connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\n").append("  output:\n").append("    ansi:\n").append("      enabled: ALWAYS\n").append("  devtools:\n").append("    restart:\n").append("      enabled: true  #设置开启热部署\n").append("      additional-paths: src/main/java #重启目录\n").append("      exclude: WEB-INF/**\n").append("    freemarker:\n").append("      cache: false    #页面不加载缓存，修改即时生效\n").append("\n").append("redis:\n").append("  ip: 192.168.1.101\n").append("  port: 6379\n").append("  password: uhyils\n").append("\n").append("rabbit:\n").append("  host: 192.168.1.101\n").append("  port: 5672\n").append("  username: uhyils\n").append("  password: 123456\n");
        result.put(filePathDev, sb.toString());
        result.put(filePathTest, sb.toString());
        result.put(filePathRelease, sb.toString());
        result.put(filePathProd, sb.toString());
        return result;
    }

    private static Map<String, String> createDockerfile(String servicePath, DbInformation dbInformation) {
        String filePath = servicePath + "/Dockerfile";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder();
        sb.append("# Docker image for {tomcat? springBoot?}\n").append("# VERSION {版本号}\n").append("# Author: uhyils\n").append("# docker stop prod-my-service-").append(dbInformation.getSmallProjectName()).append("\n").append("# docker rm prod-my-service-").append(dbInformation.getSmallProjectName()).append("\n").append("# docker rmi my-service/prod-my-service-").append(dbInformation.getSmallProjectName()).append(":latest\n").append("# docker build --build-arg version=0.0.5 --build-arg runEnv=prod -t my-service/prod-my-service-").append(dbInformation.getSmallProjectName()).append(" .\n").append("# docker run -it -d  -p 8053:8053 -p 20853:20853 -v /my/logs:/my/logs --name prod-my-service-").append(dbInformation.getSmallProjectName()).append(" my-service/prod-my-service-").append(dbInformation.getSmallProjectName()).append(":latest\n").append("\n").append("FROM java:8\n").append("\n").append("ARG runEnv\n").append("ARG version\n").append("\n").append("#作者\n").append("MAINTAINER uhyils <uhyils@qq.com>\n").append("\n").append("#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar\n").append("ADD target/my-service-").append(dbInformation.getSmallProjectName()).append("-$version.jar /app.jar\n").append("\n").append("#启动容器时的进程\n").append("CMD [\"java\",\"-jar\",\"/app.jar\",\"-XX:+UseG1GC -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/my/logs/" + dbInformation.getSmallProjectName() + "/JVM_DOWN.dump -Xms512m -Xmx512m -XX:NewRatio=1 -Xloggc:/my/logs/" + dbInformation.getSmallProjectName() + "/JVM.log\",\"--spring.profiles.active=$runEnv\"]\n");
        result.put(filePath, sb.toString());
        return result;
    }

    private static Map<String, String> createServicePom(String servicePath, DbInformation dbInformation, DbTypeEnum typeEnum) {
        String filePath = servicePath + "/pom.xml";
        HashMap<String, String> result = new HashMap<>(1);
        StringBuilder sb = new StringBuilder().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n").append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n").append("         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n").append("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n").append("    <parent>\n").append("        <artifactId>my-service</artifactId>\n").append("        <groupId>indi.uhyils</groupId>\n").append("        <version>0.0.5</version>\n").append("    </parent>\n").append("    <modelVersion>4.0.0</modelVersion>\n").append("    <version>0.0.5</version>\n").append("    <artifactId>my-service-").append(dbInformation.getSmallProjectName()).append("</artifactId>\n").append("    <packaging>jar</packaging>\n").append("\n").append("    <dependencyManagement>\n").append("        <dependencies>\n").append("            <dependency>\n").append("                <!-- Import dependency management from Spring Boot -->\n").append("                <groupId>org.springframework.boot</groupId>\n").append("                <artifactId>spring-boot-dependencies</artifactId>\n").append("                <version>${spring-boot.version}</version>\n").append("                <type>pom</type>\n").append("                <scope>import</scope>\n").append("            </dependency>\n").append("        </dependencies>\n").append("    </dependencyManagement>\n").append("\n").append("    <dependencies>\n").append("\n").append("        <dependency>\n").append("            <groupId>indi.uhyils</groupId>\n").append("            <artifactId>my-common-base</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>indi.uhyils</groupId>\n").append("            <artifactId>my-common-service</artifactId>\n").append("        </dependency>\n").append("\n").append("        <dependency>\n").append("            <groupId>indi.uhyils</groupId>\n").append("            <artifactId>my-api-provider-").append(dbInformation.getSmallProjectName()).append("</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>indi.uhyils</groupId>\n").append("            <artifactId>my-api-provider-user</artifactId>\n").append("        </dependency>\n").append("\n").append("        <dependency>\n").append("            <groupId>org.springframework.boot</groupId>\n").append("            <artifactId>spring-boot-starter-web</artifactId>\n").append("        </dependency>\n").append("\n");
        switch (typeEnum) {
            case MYSQL:
                sb.append("        <dependency>\n            <groupId>mysql</groupId>\n            <artifactId>mysql-connector-java</artifactId>\n        </dependency>\n");
                break;
            case ORACLE:
                sb.append("<dependency>\n").append("    <groupId>com.oracle</groupId>\n").append("    <artifactId>ojdbc6</artifactId>\n").append("</dependency>");
                break;
            case SQLITE:
                sb.append("<!-- sqlitejdbc -->\n").append("<dependency>\n").append("    <groupId>sqlitejdbc</groupId>\n").append("    <artifactId>sqlitejdbc</artifactId>\n").append("</dependency>\n");
                break;
            default:
                break;
        }
        sb.append("\n").append("\n").append("        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->\n").append("        <dependency>\n").append("            <groupId>org.springframework.boot</groupId>\n").append("            <artifactId>spring-boot-starter-test</artifactId>\n").append("            <scope>test</scope>\n").append("        </dependency>\n").append("\n").append("\n").append("        <dependency>\n").append("            <groupId>com.alibaba.boot</groupId>\n").append("            <artifactId>velocity-spring-boot-starter</artifactId>\n").append("        </dependency>\n").append("\n").append("        <dependency>\n").append("            <groupId>org.apache.commons</groupId>\n").append("            <artifactId>commons-lang3</artifactId>\n").append("        </dependency>\n").append("\n").append("        <!-- dubbo -->\n").append("        <dependency>\n").append("            <groupId>org.apache.dubbo</groupId>\n").append("            <artifactId>dubbo-spring-boot-starter</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.apache.dubbo</groupId>\n").append("            <artifactId>dubbo</artifactId>\n").append("        </dependency>\n").append("\n").append("\n").append("        <!--curator-->\n").append("        <dependency>\n").append("            <groupId>org.apache.curator</groupId>\n").append("            <artifactId>curator-framework</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.apache.curator</groupId>\n").append("            <artifactId>curator-recipes</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>com.alibaba</groupId>\n").append("            <artifactId>fastjson</artifactId>\n").append("        </dependency>\n").append("\n").append("        <dependency>\n").append("            <groupId>org.apache.dubbo</groupId>\n").append("            <artifactId>dubbo-registry-nacos</artifactId>\n").append("        </dependency>\n").append("\n").append("        <dependency>\n").append("            <groupId>com.alibaba.nacos</groupId>\n").append("            <artifactId>nacos-client</artifactId>\n").append("        </dependency>\n").append("\n").append("    </dependencies>\n").append("    <build>\n").append("        <plugins>\n").append("            <plugin>\n").append("                <groupId>org.springframework.boot</groupId>\n").append("                <artifactId>spring-boot-maven-plugin</artifactId>\n").append("                <version>${spring-boot.version}</version>\n").append("                <configuration>\n").append("                    <mainClass>indi.uhyils.").append(dbInformation.getSmallProjectName()).append("Application</mainClass>\n").append("                </configuration>\n").append("                <executions>\n").append("                    <execution>\n").append("                        <goals>\n").append("                            <goal>repackage</goal>\n").append("                        </goals>\n").append("                    </execution>\n").append("                </executions>\n").append("            </plugin>\n").append("            <plugin>\n").append("                <groupId>org.apache.maven.plugins</groupId>\n").append("                <artifactId>maven-compiler-plugin</artifactId>\n").append("                <version>${maven.compiler.plugin.version}</version>\n").append("                <configuration>\n").append("                    <source>1.8</source>\n").append("                    <target>1.8</target>\n").append("                </configuration>\n").append("            </plugin>\n").append("        </plugins>\n").append("    </build>\n").append("</project>\n");
        result.put(filePath, sb.toString());
        return result;
    }

    private static HashMap<String, String> createApiPom(String apiPath, DbInformation dbInformation) {
        String filePath = apiPath + "/pom.xml";
        StringBuilder sb = new StringBuilder().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n").append("<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n").append("         xmlns=\"http://maven.apache.org/POM/4.0.0\"\n").append("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n").append("    <parent>\n").append("        <artifactId>my-api</artifactId>\n").append("        <groupId>indi.uhyils</groupId>\n").append("        <version>0.0.5</version>\n").append("    </parent>\n").append("    <modelVersion>4.0.0</modelVersion>\n").append("    <version>0.0.5</version>\n").append("    <artifactId>my-api-provider-").append(dbInformation.getSmallProjectName()).append("</artifactId>\n").append("    <dependencies>\n").append("        <dependency>\n").append("            <groupId>indi.uhyils</groupId>\n").append("            <artifactId>my-common-base</artifactId>\n").append("        </dependency>\n").append("\n").append("        <dependency>\n").append("            <groupId>indi.uhyils</groupId>\n").append("            <artifactId>my-common-service</artifactId>\n").append("        </dependency>\n").append("\n").append("    </dependencies>\n").append("\n").append("</project>\n");
        HashMap<String, String> result = new HashMap<>(1);
        result.put(filePath, sb.toString());
        return result;
    }

    private static HashMap<String, String> createServiceImpl(String filepath, String className, TableInfo tableInfo, String dateFormat) {
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/serviceImpl/" + className + "ServiceImpl.java";
        StringBuilder sb = new StringBuilder().append("package indi.uhyils.serviceImpl;\n").append("\n").append("import org.apache.dubbo.config.annotation.Service;\n").append("import indi.uhyils.annotation.NoToken;\n").append("import indi.uhyils.content.Content;\n").append("import indi.uhyils.dao.").append(className).append("Dao;\n").append("import indi.uhyils.enum_.ServiceCode;\n").append("import indi.uhyils.pojo.model.*;\n").append("import indi.uhyils.service.").append(className).append("Service;\n").append("import org.springframework.beans.factory.annotation.Autowired;\n").append("\n").append("/**\n").append(" * @author uhyils <247452312@qq.com>\n").append(" * @date 文件创建日期 ").append(dateFormat).append("\n").append(" */\n").append("@Service(group = \"${spring.profiles.active}\")\n").append("public class ").append(className).append("ServiceImpl extends BaseDefaultServiceImpl<").append(className).append("Entity> implements ").append(className).append("Service {\n").append("\n").append("    @Autowired\n").append("    private ").append(className).append("Dao dao;\n").append("\n").append("\n").append("    public ").append(className).append("Dao getDao() {\n").append("        return dao;\n").append("    }\n").append("\n").append("    public void setDao(").append(className).append("Dao dao) {\n").append("        this.dao = dao;\n").append("    }\n").append("}\n");
        result.put(fileAllName, sb.toString());
        return result;
    }

    private static HashMap<String, String> createService(String filepath, String className, TableInfo tableInfo, String dateFormat) {
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/service/" + className + "Service.java";
        StringBuilder sb = new StringBuilder();
        sb.append("package indi.uhyils.service;\n").append("\n").append("import indi.uhyils.pojo.model.*;\n").append("import indi.uhyils.service.base.DefaultEntityService;\n").append("\n").append("/**\n").append(" *\n").append(" * @author uhyils <247452312@qq.com>\n").append(" * @date 文件创建日期 ").append(dateFormat).append("\n").append(" */\n").append("public interface ").append(className).append("Service extends DefaultEntityService<").append(className).append("Entity> {\n").append("\n").append("\n").append("}\n");
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
        sb.append("/**\n").append(" *\n").append(" * @author uhyils <247452312@qq.com>\n").append(" * @date 文件创建日期 ").append(dateFormat).append("\n").append(" */\n");
        sb.append("@Mapper\n");
        sb.append("public interface ");
        sb.append(className);
        sb.append("Dao extends DefaultDao<");
        sb.append(className);
        sb.append("> {\n\n\n");
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
        sb.append("\n    </sql>\n").append("\n    <select id=\"getById\" resultType=\"indi.uhyils.pojo.model.").append(className).append("Entity\">\n        select\n        <include refid=\"columnList\"/>\n        from ");
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
            // 不是主键 TODO 修改为enum
            if (entry.getValue().getKeyType() != 1) {
                sb.append("        <if test=\"").append(entry.getValue().getSmallName());
                sb.append(" != null and ").append(entry.getValue().getSmallName());
                sb.append(" != ''\">\n            ,").append(entry.getValue().getName());
                sb.append(" = #{").append(entry.getValue().getSmallName());
                sb.append("}\n        </if>\n");
            }

        }
        sb.append("        where ").append(tableInfo.getOnlyKey().getName()).append(" = #{").append(tableInfo.getOnlyKey().getSmallName()).append("}\n").append("    </update>\n").append("\n").append("    <select id=\"count\" resultType=\"java.lang.Integer\">\n").append("        select count(*)\n").append("        from ").append(tableInfo.getTableName()).append(" a\n").append("        where delete_flag = 0;\n").append("    </select>\n").append("\n").append("    <select id=\"countByArgs\" resultType=\"java.lang.Integer\">\n").append("        select count(*)\n").append("        from ").append(tableInfo.getTableName()).append(" a\n").append("        where delete_flag = 0\n").append("        <foreach item=\"arg\" index=\"index\" collection=\"args\">\n").append("            and ${arg.name} ${arg.symbol} #{arg.data}\n").append("        </foreach>\n").append("    </select>\n").append("\n").append("    <select id=\"checkRepeat\" resultType=\"java.lang.Integer\">\n").append("        select count(*)\n").append("        from ").append(tableInfo.getTableName()).append(" a\n").append("        where ${columnName} = #{value}\n").append("    </select>\n").append("\n").append("\n").append("</mapper>\n");
        result.put(fileAllName, sb.toString());
        return result;
    }

    private static Map<String, String> createPojo(String filepath, String className, TableInfo tableInfo, String dateFormat, DbTypeEnum typeEnum) {
        TypeConvertor typeConvertor = TypeConvertorFactory.getTypeConvertor(typeEnum);
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/pojo/model/" + className + ".java";
        StringBuilder sb = new StringBuilder();
        List<JavaPojoFileGetAndSet> list = new ArrayList();

        //遍历字段,获取字段的类型,名称,是否主键
        Map<String, ColumnInfo> colums = tableInfo.getColums();
        for (Map.Entry<String, ColumnInfo> stringColumnInfoEntry : colums.entrySet()) {
            // 字段名称
            String filedName = stringColumnInfoEntry.getKey();
            ColumnInfo value = stringColumnInfoEntry.getValue();
            // 字段类型
            String dataType = value.getDataType();
            // 转换后的字段类型
            String type = typeConvertor.databaseType2JavaType(dataType);
            JavaPojoFileGetAndSet javaPojoFileGetAndSet = new JavaPojoFileGetAndSet(filedName, type);
            list.add(javaPojoFileGetAndSet);
        }
        //package
        sb.append("package indi.uhyils.pojo.model;\n\n");

        //import
        sb.append("import java.util.*;\n\n");
        sb.append("/**\n" +
                " *\n" +
                " * @author uhyils <247452312@qq.com>\n" +
                " * @date 文件创建日期 " + dateFormat + "\n" +
                " */\n");
        sb.append("public class ");
        sb.append(className);
        sb.append("{\n\n");
        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String filedString = javaPojoFileGetAndSet.getFieldString();
            sb.append("\t");
            sb.append(filedString);
            sb.append("\n");
        }
        sb.append("\n");

        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String getMethod = javaPojoFileGetAndSet.getGetString();
            sb.append("\t");
            sb.append(getMethod);
            sb.append("\n");
        }
        sb.append("\n");

        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String setMethod = javaPojoFileGetAndSet.getSetString();
            sb.append("\t");
            sb.append(setMethod);
            sb.append("\n");
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
        return new HashMap<>();
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
        HashMap<String, TableInfo> stringTableInfoHashMap = new HashMap<>();
        /*获取数据库表们的信息*/
        dmd = conn.getMetaData();
        // 这里table可以使用正则,只要getTables允许
        for (String table : dbInformation.getTables()) {
            ResultSet rs = dmd.getTables(dbInformation.getDbName(), "%", table, new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = (String) rs.getObject("TABLE_NAME");
                if (stringTableInfoHashMap.containsKey(tableName)) {
                    continue;
                }
                /*获取table信息*/
                TableInfo ti = new TableInfo(tableName, new HashMap<>(), new ColumnInfo());
                stringTableInfoHashMap.put(tableName, ti);
                ResultSet rs2 = dmd.getColumns(null, "%", tableName, "%");
                while (rs2.next()) {
                    ColumnInfo ci = new ColumnInfo(rs2.getString("TYPE_NAME"), 0, rs2.getString("COLUMN_NAME"));
                    String bigName = KproStringUtil.dealDbNameToJavaFileName(ci.getName());
                    String smallName = bigName.substring(0, 1).toUpperCase() + bigName.substring(1);
                    ci.setBigName(bigName);
                    ci.setSmallName(smallName);
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
}
