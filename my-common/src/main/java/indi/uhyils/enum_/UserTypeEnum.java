package indi.uhyils.enum_;

import indi.uhyils.exception.EnumParseNoHaveException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 15时44分
 */
public enum UserTypeEnum {

    ADMIN(0, "管理员"),

    USER(1, "用户"),

    MERCHANT(2, "商户"),

    TOURIST(3, "游客");


    private Integer userType;
    private String typeName;


    UserTypeEnum(Integer userType, String typeName) {
        this.userType = userType;
        this.typeName = typeName;
    }

    UserTypeEnum() {
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public static UserTypeEnum parse(Integer userType) throws EnumParseNoHaveException {
        switch (userType) {
            case 0:
                return ADMIN;
            case 1:
                return USER;
            case 2:
                return MERCHANT;
            case 3:
                return TOURIST;
            default:
                throw new EnumParseNoHaveException("错误的用户类型!");
        }
    }
}
