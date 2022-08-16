package indi.uhyils.mysql.enums;

/**
 * mysql err错误返回代码
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 09时39分
 */
public enum MysqlErrCodeEnum {
    /**
     * 具体为啥.. 我也不知道
     */
    EE_CANTCREATEFILE(1, "无法创建文件"),
    EE_READ(2, "读错误"),
    EE_WRITE(3, "写错误"),
    EE_BADCLOSE(4, "错误的关闭"),
    EE_OUTOFMEMORY(5, "内存不足"),
    EE_DELETE(6, "删除错误"),
    EE_LINK(7, "link错误"),
    EE_EOFERR(9, "EOF错误"),
    EE_CANTLOCK(10, "不能加锁"),
    EE_CANTUNLOCK(11, "不能解锁"),
    EE_DIR(12, "文件夹错误"),
    EE_STAT(13, "状态错误"),
    EE_CANT_CHSIZE(14, "无法调整"),
    EE_CANT_OPEN_STREAM(15, "无法打开流"),
    EE_GETWD(16, "get wd错误"),
    EE_SETWD(17, "set wd错误"),
    EE_LINK_WARNING(18, "链接警告"),
    EE_OPEN_WARNING(19, "open warning"),
    EE_DISK_FULL(20, "磁盘已满"),
    EE_CANT_MKDIR(21, "不能创建文件夹"),
    EE_UNKNOWN_CHARSET(22, "未知的字符集"),
    EE_OUT_OF_FILERESOURCES(23, "文件资源不足"),
    EE_CANT_READLINK(24, "无法读取链接"),
    EE_CANT_SYMLINK(25, "无法同步链接"),
    EE_REALPATH(26, "real path"),
    EE_SYNC(27, "同步错误"),
    EE_UNKNOWN_COLLATION(28, "未知排序规则"),
    EE_FILENOTFOUND(29, "找不到文件"),
    EE_FILE_NOT_CLOSED(30, "文件未关闭"),
    EE_CHANGE_OWNERSHIP(31, "变更所有权错误"),
    EE_CHANGE_PERMISSIONS(32, "更改权限错误"),
    EE_CANT_SEEK(33, "找不到错误"),
    EE_CAPACITY_EXCEEDED(34, "超出容量"),
    EE_DISK_FULL_WITH_RETRY_MSG(35, "磁盘已满，请重试消息"),
    EE_FAILED_TO_CREATE_TIMER(36, "无法创建计时器"),
    EE_FAILED_TO_DELETE_TIMER(37, "无法删除计时器"),
    EE_FAILED_TO_CREATE_TIMER_QUEUE(38, "无法创建计时器队列"),
    EE_FAILED_TO_START_TIMER_NOTIFY_THREAD(39, "启动计时器通知线程失败"),
    EE_FAILED_TO_CREATE_TIMER_NOTIFY_THREAD_INTERRUPT_EVENT(40, "无法创建计时器通知线程中断事件"),
    EE_EXITING_TIMER_NOTIFY_THREAD(41, "正在退出计时器通知线程"),
    EE_WIN_LIBRARY_LOAD_FAILED(42, "WIN库加载失败"),
    EE_WIN_RUN_TIME_ERROR_CHECK(43, "WIN运行时错误检查"),
    EE_FAILED_TO_DETERMINE_LARGE_PAGE_SIZE(44, "无法确定大页面的大小"),
    EE_FAILED_TO_KILL_ALL_THREADS(45, "无法终止所有线程"),
    EE_FAILED_TO_CREATE_IO_COMPLETION_PORT(46, "无法创建IO完成端口"),
    EE_FAILED_TO_OPEN_DEFAULTS_FILE(47, "无法打开默认文件"),
    EE_FAILED_TO_HANDLE_DEFAULTS_FILE(48, "无法处理默认文件"),
    EE_WRONG_DIRECTIVE_IN_CONFIG_FILE(49, "配置文件中的错误指令"),
    EE_SKIPPING_DIRECTIVE_DUE_TO_MAX_INCLUDE_RECURSION(50, "由于最大包含递归而跳过指令"),
    EE_INCORRECT_GRP_DEFINITION_IN_CONFIG_FILE(51, "配置文件中的 GRP 定义不正确"),
    EE_OPTION_WITHOUT_GRP_IN_CONFIG_FILE(52, "配置文件中没有 GRP 的选项"),
    EE_CONFIG_FILE_PERMISSION_ERROR(53, "配置文件权限错误"),
    EE_IGNORE_WORLD_WRITABLE_CONFIG_FILE(54, "忽略全局可写配置文件"),
    EE_USING_DISABLED_OPTION(55, "使用禁用选项"),
    EE_USING_DISABLED_SHORT_OPTION(56, "使用禁用的短期期权"),
    EE_USING_PASSWORD_ON_CLI_IS_INSECURE(57, "在 CLI 上使用密码是不安全的"),
    EE_UNKNOWN_SUFFIX_FOR_VARIABLE(58, "变量的未知后缀"),
    EE_SSL_ERROR_FROM_FILE(59, "来自文件的 SSL 错误"),
    EE_SSL_ERROR(60, "SSL错误"),
    EE_NET_SEND_ERROR_IN_BOOTSTRAP(61, "引导程序中的 NET 发送错误"),
    EE_PACKETS_OUT_OF_ORDER(62, "数据包乱序"),
    EE_UNKNOWN_PROTOCOL_OPTION(63, "未知的协议选项"),
    EE_FAILED_TO_LOCATE_SERVER_PUBLIC_KEY(64, "找不到服务器公钥"),
    EE_PUBLIC_KEY_NOT_IN_PEM_FORMAT(65, "不是 PEM 格式的公钥"),
    EE_DEBUG_INFO(66, "调试信息"),
    EE_UNKNOWN_VARIABLE(67, "未知变量"),
    EE_UNKNOWN_OPTION(68, "未知选项"),
    EE_UNKNOWN_SHORT_OPTION(69, "未知空头期权"),
    EE_OPTION_WITHOUT_ARGUMENT(70, "没有参数的选项"),
    EE_OPTION_REQUIRES_ARGUMENT(71, "选项需要参数"),
    EE_SHORT_OPTION_REQUIRES_ARGUMENT(72, "短选项需要参数"),
    EE_OPTION_IGNORED_DUE_TO_INVALID_VALUE(73, "由于值无效，选项被忽略"),
    EE_OPTION_WITH_EMPTY_VALUE(74, "具有空值的选项"),
    EE_FAILED_TO_ASSIGN_MAX_VALUE_TO_OPTION(75, "无法为选项分配最大值"),
    EE_INCORRECT_BOOLEAN_VALUE_FOR_OPTION(76, "选项的布尔值不正确"),
    EE_FAILED_TO_SET_OPTION_VALUE(77, "无法设置选项值"),
    EE_INCORRECT_INT_VALUE_FOR_OPTION(78, "选项的 INT 值不正确"),
    EE_INCORRECT_UINT_VALUE_FOR_OPTION(79, "选项的单位值不正确"),
    EE_ADJUSTED_SIGNED_VALUE_FOR_OPTION(80, "选项的调整签名值"),
    EE_ADJUSTED_UNSIGNED_VALUE_FOR_OPTION(81, "选项的调整未签名值"),
    EE_ADJUSTED_ULONGLONG_VALUE_FOR_OPTION(82, "选项的调整 ULONGLONG 值"),
    EE_ADJUSTED_DOUBLE_VALUE_FOR_OPTION(83, "选项的调整双值"),
    EE_INVALID_DECIMAL_VALUE_FOR_OPTION(84, "选项的无效十进制值"),
    EE_COLLATION_PARSER_ERROR(85, "排序分析器错误"),
    EE_FAILED_TO_RESET_BEFORE_PRIMARY_IGNORABLE_CHAR(86, "无法在主可忽略字符之前重置"),
    EE_FAILED_TO_RESET_BEFORE_TERTIARY_IGNORABLE_CHAR(87, "无法在可忽略字符之前重置字符"),
    EE_SHIFT_CHAR_OUT_OF_RANGE(88, "字符超出范围"),
    EE_RESET_CHAR_OUT_OF_RANGE(89, "重置字符超出范围"),
    EE_UNKNOWN_LDML_TAG(90, "未知LDML标签"),
    EE_FAILED_TO_RESET_BEFORE_SECONDARY_IGNORABLE_CHAR(91, "无法在辅助字符之前重置字符"),
    EE_FAILED_PROCESSING_DIRECTIVE(92, "处理指令失败");

    private final int code;

    private final String msg;

    MysqlErrCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
