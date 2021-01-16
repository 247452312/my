package indi.uhyils.rpc.netty.pojo;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.NoMyRpcException;
import indi.uhyils.rpc.netty.exception.RpcTypeNotSupportedException;
import indi.uhyils.rpc.netty.exception.RpcVersionNotSupportedException;
import indi.uhyils.rpc.netty.pojo.request.RpcRequestFactory;
import indi.uhyils.rpc.netty.pojo.response.RpcResponseFactory;
import indi.uhyils.rpc.netty.util.BytesUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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
     * 状态
     */
    protected byte status;

    /**
     * 唯一标识
     */
    protected Long unique;

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

    protected AbstractRpcData(final byte[] data) throws RpcException, ClassNotFoundException {
        doInit(data);
    }

    protected AbstractRpcData() {
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

    /**
     * 填充size
     *
     * @param data
     * @param readIndex
     */
    protected void initSize(byte[] data, AtomicInteger readIndex) {
        final int sizeSize = MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_SIZE_INDEX];
        final int startIndex = readIndex.get();
        byte[] sizeBytes = Arrays.copyOfRange(data, startIndex, startIndex + sizeSize);
        this.size = BytesUtils.changeByteToInteger(sizeBytes);
        readIndex.addAndGet(sizeSize);
    }

    /**
     * 获取rpc全部
     *
     * @return
     */
    @Override
    public byte[] toBytes() {
        //头部
        byte[] previousBytes = new byte[Arrays.stream(MyRpcContent.RPC_DATA_ITEM_SIZE).sum()];
        // 写索引
        AtomicInteger writeIndex = new AtomicInteger(0);
        // 写入mark头
        System.arraycopy(
                MyRpcContent.AGREEMENT_START,
                0,
                previousBytes,
                writeIndex.getAndAdd(MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_MARK_INDEX]),
                MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_MARK_INDEX]);

        // 写入version and type
        byte[] src = {(byte) ((rpcVersion() << 2) + (type() << 1))};
        int andAdd = writeIndex.getAndAdd(MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_VERSION_REQ_RES_INDEX]);
        System.arraycopy(src, 0, previousBytes, andAdd, MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_VERSION_REQ_RES_INDEX]);

        //写入size,并获取head 和 content 的数组
        final String s = contentString();
        byte[] headAndContent = s.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(BytesUtils.changeIntegerToByte(headAndContent.length), 0, previousBytes, writeIndex.getAndAdd(MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_SIZE_INDEX]), MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_SIZE_INDEX]);

        // 写入状态
        previousBytes[writeIndex.getAndAdd(1)] = getStatus();

        //写入唯一标示
        byte[] uniqueBytes = BytesUtils.changeLongToByte(getUnique());
        System.arraycopy(uniqueBytes,
                0,
                previousBytes,
                writeIndex.getAndAdd(MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_UNIQUE_INDEX]),
                MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_UNIQUE_INDEX]);

        byte[] result = new byte[previousBytes.length + headAndContent.length];
        System.arraycopy(previousBytes, 0, result, 0, previousBytes.length);
        System.arraycopy(headAndContent, 0, result, previousBytes.length, headAndContent.length);

        return result;
    }

    private void doInit(final byte[] data) throws RpcException, ClassNotFoundException {
        AtomicInteger readIndex = new AtomicInteger(0);
        // 判断是不是myRpc的协议
        isMyRpc(data, readIndex);

        // 确定版本以及类型是否兼容(正确)
        initVersionAndType(data, readIndex);

        // 填充size
        initSize(data, readIndex);

        //填充状态
        initStatus(data, readIndex);

        //填充唯一标识
        initUnique(data, readIndex);

        // 获取header
        initHeader(data, readIndex);

        // 获取内容字符串
        initContentArray(data, readIndex);

        // 处理内容
        initContent();
    }

    private void initUnique(byte[] data, AtomicInteger readIndex) {
        final int uniqueSize = MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_UNIQUE_INDEX];
        final int startIndex = readIndex.get();
        byte[] uniqueBytes = Arrays.copyOfRange(data, startIndex, startIndex + uniqueSize);
        this.unique = BytesUtils.changeByteToLong(uniqueBytes);
        readIndex.addAndGet(uniqueSize);
    }

    private void initStatus(byte[] data, AtomicInteger readIndex) {
        final int statusSize = MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_STATUS_INDEX];
        assert statusSize == 1;
        final int startIndex = readIndex.get();
        byte[] status = Arrays.copyOfRange(data, startIndex, startIndex + statusSize);
        // 先这么搞..
        this.status = status[0];
        readIndex.addAndGet(statusSize);
    }

    private void initContentArray(byte[] data, AtomicInteger readIndex) {
        StringBuilder contentStr = new StringBuilder();
        for (int i = readIndex.get(); i < data.length; i++) {
            contentStr.append((char) data[i]);
        }
        this.contentArray = contentStr.toString().split("\n");
    }

    private void initHeader(byte[] data, AtomicInteger readIndex) {
        boolean lastByteIsEnter = false;
        List<RpcHeader> rpcHeaders = new ArrayList<>();
        StringBuilder headerStr = new StringBuilder();
        int headerEnd = 0;
        for (int i = readIndex.get(); i < data.length; i++) {
            headerEnd++;
            if (Objects.equals(data[i], ENTER)) {
                if (lastByteIsEnter) {
                    break;
                }
                lastByteIsEnter = true;
                RpcHeader rpcHeader = RpcHeaderFactory.newInstance(headerStr.toString());
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
        readIndex.addAndGet(headerEnd);
    }

    /**
     * 确定版本以及类型是否兼容(正确)
     *
     * @param data
     * @param readIndex
     * @throws RpcVersionNotSupportedException
     */
    private void initVersionAndType(byte[] data, AtomicInteger readIndex) throws RpcException {
        int version = (data[readIndex.get()] >> 2) & 0b111111;
        if (version > MAX_VERSION) {
            throw new RpcVersionNotSupportedException(version, MAX_VERSION);
        }
        int type = (data[readIndex.get()] & 0b10) >> 1;
        if (!Objects.equals(type, type())) {
            throw new RpcTypeNotSupportedException(type, type());
        }
        this.version = version;
        readIndex.addAndGet(MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_VERSION_REQ_RES_INDEX]);

    }

    /**
     * 判断是不是myRpc的协议
     *
     * @param data
     * @param readIndex
     * @throws NoMyRpcException
     */
    private void isMyRpc(byte[] data, AtomicInteger readIndex) throws NoMyRpcException {
        int from = readIndex.get();
        byte[] bytes = Arrays.copyOfRange(data, from, from + MyRpcContent.AGREEMENT_START.length);
        boolean startByteEquals = Arrays.equals(bytes, MyRpcContent.AGREEMENT_START);
        if (!startByteEquals) {
            throw new NoMyRpcException();
        }
        readIndex.addAndGet(MyRpcContent.RPC_DATA_ITEM_SIZE[MyRpcContent.RPC_DATA_MARK_INDEX]);
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

    @Override
    public String[] contentArray() {
        return contentArray;
    }

    public RpcContent getContent() {
        return content;
    }

    public void setContent(RpcContent content) {
        this.content = content;
    }

    @Override
    public Long unique() {
        return unique;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Long getUnique() {
        return unique;
    }

    public void setUnique(Long unique) {
        this.unique = unique;
    }

    public String[] getContentArray() {
        return contentArray;
    }

    public void setContentArray(String[] contentArray) {
        this.contentArray = contentArray;
    }
}
