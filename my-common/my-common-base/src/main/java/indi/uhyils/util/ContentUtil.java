package indi.uhyils.util;

import com.alibaba.nacos.common.utils.Objects;
import indi.uhyils.pojo.model.ContentDO;

/**
 * Content表工具
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 08时31分
 */
public final class ContentUtil {

    private ContentUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getContentVarByTitle(ContentDO entity, String title) {
        if (Objects.equals(title, entity.getTitle1())) {
            return entity.getVar1();
        }

        if (Objects.equals(title, entity.getTitle2())) {
            return entity.getVar2();
        }

        if (Objects.equals(title, entity.getTitle3())) {
            return entity.getVar3();
        }

        if (Objects.equals(title, entity.getTitle4())) {
            return entity.getVar4();
        }

        if (Objects.equals(title, entity.getTitle5())) {
            return entity.getVar5();
        }

        if (Objects.equals(title, entity.getTitle6())) {
            return entity.getVar6();
        }
        return null;
    }
}
