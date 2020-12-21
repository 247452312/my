package indi.uhyils.rpc.netty.pojo;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.NoMyRpcException;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.exception.VersionNotSupportedException;
import indi.uhyils.rpc.netty.pojo.request.RpcRequestFactory;
import indi.uhyils.rpc.netty.pojo.response.RpcResponseFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 11时16分
 */
public abstract class AbstractRpcData implements RpcData {
    /**
     * 换行符
     */
    protected static final byte ENTER = '\n';
    /**
     * 此类支持的最大版本
     */
    private static final Integer MAX_VERSION = 1;
    /**
     * 版本
     */
    protected Integer version;

    /**
     * 数据体类型 0->请求 1->响应
     */
    protected Integer type;

    /**
     * 内容大小
     */
    protected Integer size;

    /**
     * head
     */
    protected RpcHeader[] headers;

    /**
     * 拆出来的content字符串
     */
    protected String[] contentArray;

    /**
     * 内容
     */
    protected RpcContent content;

    public AbstractRpcData(byte[] data) throws RpcException, ClassNotFoundException {
        doInit(data);
        initContent();
    }

    public AbstractRpcData() {
    }

    public static RpcData createByBytes(byte[] data) throws RpcException, ClassNotFoundException {
        int type = (data[2] & 0b10) >> 1;
        RpcTypeEnum parse = RpcTypeEnum.parse(type);
        assert parse != null;
        RpcFactory rpcFactory;
        switch (parse) {
            case REQUEST:
                rpcFactory = RpcRequestFactory.getInstance();
                break;
            case RESPONSE:
                rpcFactory = RpcResponseFactory.getInstance();
                break;
            default:
                throw new RuntimeException();
        }
        return rpcFactory.createByBytes(data);
    }

    /**
     * 初始化内容
     *
     * @throws ClassNotFoundException 初始化时如果对应方法未找到
     * @throws RpcException           rpc错误
     */
    protected abstract void initContent() throws RpcException, ClassNotFoundException;

    protected abstract int getSizeStartIndex();

    private void doInit(byte[] data) throws RpcException {
        // 判断是不是myRpc的协议
        byte[] bytes = Arrays.copyOfRange(data, 0, MyRpcContent.AGREEMENT_START.length);
        boolean startByteEquals = Arrays.equals(bytes, MyRpcContent.AGREEMENT_START);
        if (!startByteEquals) {
            throw new NoMyRpcException();
        }

        // 确定版本是否兼容
        int version = (data[2] >> 2) & 0b111111;
        if (version > MAX_VERSION) {
            throw new VersionNotSupportedException(version, MAX_VERSION);
        }
        this.version = version;
        initSize(data);

        // 获取header
        boolean lastByteIsEnter = false;
        List<RpcHeader> rpcHeaders = new ArrayList<>();
        StringBuilder headerStr = new StringBuilder();
        int headerEnd = 0;
        for (int i = getSizeStartIndex(); i < data.length; i++) {
            if (Objects.equals(data[i], ENTER)) {
                if (lastByteIsEnter) {
                    headerEnd = i;
                    break;
                }
                lastByteIsEnter = true;
                RpcHeader rpcHeader = RpcHeaderFactory.newInstance(headerStr.toString());
                headerEnd = i;
                headerStr.delete(0, headerStr.length());
                if (rpcHeader != null) {
                    rpcHeaders.add(rpcHeader);
                }
            } else {
                headerStr.append((char) data[i]);
                lastByteIsEnter = false;
            }
        }
        this.headers = rpcHeaders.toArray(new RpcHeader[]{new RpcHeader()});

        // 获取剩余部分
        StringBuilder contentStr = new StringBuilder();
        for (int i = headerEnd + 1; i < data.length; i++) {
            contentStr.append((char) data[i]);
        }
        this.contentArray = contentStr.toString().split("\n");
        // 处理其他事情
        dealOtherThing(data);
    }


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public RpcHeader[] getHeaders() {
        return headers;
    }

    public void setHeaders(RpcHeader[] headers) {
        this.headers = headers;
    }

    public String[] getContentArray() {
        return contentArray;
    }

    public void setContentArray(String[] contentArray) {
        this.contentArray = contentArray;
    }

    public RpcContent getContent() {
        return content;
    }

    public void setContent(RpcContent content) {
        this.content = content;
    }
}
