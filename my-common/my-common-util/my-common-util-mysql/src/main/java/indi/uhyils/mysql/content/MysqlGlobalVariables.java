package indi.uhyils.mysql.content;

/**
 * mysql 全局系统参数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月22日 08时41分
 */
public class MysqlGlobalVariables {

    /**
     * 指定MySQL可能的连接数量。当MySQL主线程在很短的时间内得到非常多的连接请求，该参数就起作用，之后主线程花些时间(尽管很短)检查连接并且启动一个新线程。
     * <p>
     * back_log参数的值指出在MySQL暂时停止响应新请求之前的短时间内多少个请求可以被存在堆栈中。如果系统在一个短时间内有很多连接，则需要增大该参数的值，该参数值指定到来的TCP/IP连接的侦听队列的大小。不同的操作系统在这个队列大小上有它自己的限制。 试图设定back_log高于你的操作系统的限制将是无效的。
     * <p>
     * 当观察MySQL进程列表，发现大量 264084 | unauthenticated user | xxx.xxx.xxx.xxx | NULL | Connect | NULL | login | NULL 的待连接进程时，就要加大 back_log 的值。back_log默认值为50。
     */
    private Integer backLog = 50;

    /**
     * MySQL主程序所在路径，即：--basedir参数的值。
     */
    private String basedir = "/usr/";

    /**
     * 为binary log指定在查询请求处理过程中SQL 查询语句使用的缓存大小。如果频繁应用于大量、复杂的SQL表达式处理，则应该加大该参数值以获得性能提升。
     */
    private Integer binlogCacheSize = 32768;

    /**
     * MySQL的默认字符集。
     */
    private String characterSet = "utf8mb4";


    /**
     * 指定MySQL服务等待应答一个连接报文的最大秒数，超出该时间，MySQL向客户端返回 bad handshake。
     */
    private Integer connectTimeout = 10;

    /**
     * 指定数据库路径。即为 --datadir 选项的值。
     */
    private String datadir;

    /**
     * 该参数只对 MyISAM 类型数据表有效。有如下的取值种类：
     * <p>
     * off: 如果在建表语句中使用 CREATE TABLE ... DELAYED_KEY_WRITES，则全部忽略
     * <p>
     * DELAYED_KEY_WRITES;
     * <p>
     * on: 如果在建表语句中使用 CREATE TABLE ... DELAYED_KEY_WRITES，则使用该选项(默认);
     * <p>
     * all: 所有打开的数据表都将按照 DELAYED_KEY_WRITES 处理。
     * <p>
     * 如果 DELAYED_KEY_WRITES 开启，对于已经打开的数据表而言，在每次索引更新时都不刷新带有
     * <p>
     * DELAYED_KEY_WRITES 选项的数据表的key buffer，除非该数据表关闭。该参数会大幅提升写入键值的速
     * <p>
     * 度。如果使用该参数，则应该检查所有数据表：myisamchk --fast --force。
     */
    private String delayKeyWrite = "ON";

    /**
     * 在插入delayed_insert_limit行后，INSERT DELAYED处理模块将检查是否有未执行的SELECT语句。如果有，在继续处理前执行允许这些语句。
     */
    private Integer delayedInsertLimit = 100;

    /**
     * 一个INSERT DELAYED线程应该在终止之前等待INSERT语句的时间。
     */
    private Integer delayedInsertTimeout = 300;

    /**
     * 为处理INSERT DELAYED分配的队列大小(以行为单位)。如果排队满了，任何进行INSERT DELAYED的客户必须等待队列空间释放后才能继续。
     */
    private Integer delayedQueueSize = 1000;

    /**
     * 在启动MySQL时加载 --flush 参数打开该功能。
     */
    private String flush = "OFF";

    /**
     * 如果该设置为非0值，那么每flush_time秒，所有打开的表将被关，以释放资源和sync到磁盘。注意：只建议在使用 Windows9x/Me 或者当前操作系统资源严重不足时才使用该参数!
     */
    private Integer flushTime = 0;

    /**
     * YES: MySQL支持InnoDB类型数据表; DISABLE: 使用 --skip-innodb 关闭对InnoDB类型数据表的支持。
     */
    private String haveInnodb = "YES";

    /**
     * 用户输出报错信息的语言。
     */
    private String language = "简体中文";


    /**
     * 开启 binary log。
     */
    private String logBin = "ON";

    /**
     * 一个查询语句包的最大尺寸。消息缓冲区被初始化为net_buffer_length字节，但是可在需要时增加到max_allowed_packet个字节。该值太小则会在处理大包时产生错误。如果使用大的BLOB列，必须增加该值。
     */
    private Long maxAllowedPacket = 67108864L;

    /**
     * 允许同时连接MySQL服务器的客户数量。如果超出该值，MySQL会返回Too many connections错误，但通常情况下，MySQL能够自行解决。
     */
    private Integer maxConnections = 151;

    /**
     * 指定来自同一用户的最多连接数。设置为0则代表不限制。
     */
    private Integer maxUserConnections = 0;

    public Integer getBackLog() {
        return backLog;
    }

    public void setBackLog(Integer backLog) {
        this.backLog = backLog;
    }

    public String getBasedir() {
        return basedir;
    }

    public void setBasedir(String basedir) {
        this.basedir = basedir;
    }

    public Integer getBinlogCacheSize() {
        return binlogCacheSize;
    }

    public void setBinlogCacheSize(Integer binlogCacheSize) {
        this.binlogCacheSize = binlogCacheSize;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getDatadir() {
        return datadir;
    }

    public void setDatadir(String datadir) {
        this.datadir = datadir;
    }

    public String getDelayKeyWrite() {
        return delayKeyWrite;
    }

    public void setDelayKeyWrite(String delayKeyWrite) {
        this.delayKeyWrite = delayKeyWrite;
    }

    public Integer getDelayedInsertLimit() {
        return delayedInsertLimit;
    }

    public void setDelayedInsertLimit(Integer delayedInsertLimit) {
        this.delayedInsertLimit = delayedInsertLimit;
    }

    public Integer getDelayedInsertTimeout() {
        return delayedInsertTimeout;
    }

    public void setDelayedInsertTimeout(Integer delayedInsertTimeout) {
        this.delayedInsertTimeout = delayedInsertTimeout;
    }

    public Integer getDelayedQueueSize() {
        return delayedQueueSize;
    }

    public void setDelayedQueueSize(Integer delayedQueueSize) {
        this.delayedQueueSize = delayedQueueSize;
    }

    public String getFlush() {
        return flush;
    }

    public void setFlush(String flush) {
        this.flush = flush;
    }

    public Integer getFlushTime() {
        return flushTime;
    }

    public void setFlushTime(Integer flushTime) {
        this.flushTime = flushTime;
    }

    public String getHaveInnodb() {
        return haveInnodb;
    }

    public void setHaveInnodb(String haveInnodb) {
        this.haveInnodb = haveInnodb;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLogBin() {
        return logBin;
    }

    public void setLogBin(String logBin) {
        this.logBin = logBin;
    }

    public Long getMaxAllowedPacket() {
        return maxAllowedPacket;
    }

    public void setMaxAllowedPacket(Long maxAllowedPacket) {
        this.maxAllowedPacket = maxAllowedPacket;
    }

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
    }

    public Integer getMaxUserConnections() {
        return maxUserConnections;
    }

    public void setMaxUserConnections(Integer maxUserConnections) {
        this.maxUserConnections = maxUserConnections;
    }
}
