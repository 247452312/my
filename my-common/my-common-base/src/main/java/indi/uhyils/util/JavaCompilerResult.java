package indi.uhyils.util;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月07日 12时36分
 */
public class JavaCompilerResult {

    /**
     * 结果
     */
    private Object result;

    /**
     * 编译时间
     */
    private long compilerTime;

    /**
     * 执行时长
     */
    private long runTime;

    /**
     * System.out输出
     */
    private String outPrint;


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getCompilerTime() {
        return compilerTime;
    }

    public void setCompilerTime(long compilerTime) {
        this.compilerTime = compilerTime;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public String getOutPrint() {
        return outPrint;
    }

    public void setOutPrint(String outPrint) {
        this.outPrint = outPrint;
    }


    public static JavaCompilerResult build(Object result, long compilerTime, long runTime, String outPrint){
        JavaCompilerResult build = new JavaCompilerResult();
        build.setResult(result);
        build.setCompilerTime(compilerTime);
        build.setRunTime(runTime);
        build.setOutPrint(outPrint);
        return build;
    }
}
