package indi.uhyils.util;

import indi.uhyils.pojo.model.ContentEntity;

/**
 * Content表工具
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 08时31分
 */
public class ContentUtil {

    public static String getContentVarByTitle(ContentEntity entity, String title) {
        assert title != null;
        if (title.equals(entity.getTitle1())) {
            return entity.getVar1();
        }

        if (title.equals(entity.getTitle2())) {
            return entity.getVar2();
        }

        if (title.equals(entity.getTitle3())) {
            return entity.getVar3();
        }

        if (title.equals(entity.getTitle4())) {
            return entity.getVar4();
        }

        if (title.equals(entity.getTitle5())) {
            return entity.getVar5();
        }

        if (title.equals(entity.getTitle6())) {
            return entity.getVar6();
        }
        return null;
    }
}
