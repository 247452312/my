package indi.uhyils.exception;

import indi.uhyils.pojo.DTO.base.ServiceResult;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月21日 09时32分
 */
public class ServiceResultException extends RuntimeException {

    private final ServiceResult<?> sr;

    public ServiceResultException(ServiceResult<?> sr) {
        super(sr.getServiceMessage());
        this.sr = sr;
    }

    public ServiceResult<?> getSr() {
        return sr;
    }
}
