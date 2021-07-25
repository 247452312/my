package indi.uhyils.log;

import indi.uhyils.log.filter.db.DbLogFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月25日 14时24分
 */
@Configurable
@Import(DbLogFilter.class)
public class LogConfigurable {

}
