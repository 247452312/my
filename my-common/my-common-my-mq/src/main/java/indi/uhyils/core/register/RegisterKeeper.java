package indi.uhyils.core.register;

import java.util.Objects;

/**
 * 注册者管理者
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 22时02分
 * @Version 1.0
 */
public class RegisterKeeper {

    public static class Register {
        /**
         * 注册者的ip
         */
        private String ip;

        /**
         * 注册者的端口
         */
        private Integer port;

        /**
         * 注册者类型
         */
        private RegisterType type;

        public Register(String ip, Integer port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Register register = (Register) o;
            return Objects.equals(ip, register.ip) && Objects.equals(port, register.port);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ip, port);
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

}
