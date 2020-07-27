package indi.uhyils.util.kpro;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 08时19分
 */
public class JavaPojoFileGetAndSet {

    /**
     * 字段名称
     */
    private String fieldString;
    /**
     * get方法名称
     */
    private String getString;
    /**
     * set方法名称
     */
    private String setString;

    public JavaPojoFileGetAndSet(String fieldName, String fieldType) {
        String big = KproStringUtil.dealDbNameToJavaFileName(fieldName);
        String smallFieldName = big.substring(0, 1).toLowerCase() + big.substring(1);
        StringBuilder sb = new StringBuilder();

        // 字段string
        sb.append("private ");
        sb.append(fieldType);
        sb.append(" ");
        sb.append(smallFieldName);
        sb.append(";");
        this.fieldString = sb.toString();
        sb.delete(0, sb.length());


        // get方法string
        sb.append("public ");
        sb.append(fieldType);
        sb.append(" get");
        sb.append(big);
        sb.append("(){\n\t\treturn ");
        sb.append(smallFieldName);
        sb.append("\n\t}");
        this.getString = sb.toString();
        sb.delete(0, sb.length());

        // set方法string
        sb.append("public void set");
        sb.append(big);
        sb.append("(");
        sb.append(fieldType);
        sb.append(" ");
        sb.append(smallFieldName);
        sb.append("){\n\t\tthis.");
        sb.append(fieldType);
        sb.append(" = ");
        sb.append(smallFieldName);
        sb.append(";\n\t}");
        this.setString = sb.toString();

    }

    public String getFieldString() {
        return fieldString;
    }

    public void setFieldString(String fieldString) {
        this.fieldString = fieldString;
    }

    public String getGetString() {
        return getString;
    }

    public void setGetString(String getString) {
        this.getString = getString;
    }

    public String getSetString() {
        return setString;
    }

    public void setSetString(String setString) {
        this.setString = setString;
    }
}
