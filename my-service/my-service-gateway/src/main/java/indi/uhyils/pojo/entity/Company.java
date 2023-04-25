package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.mysql.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.pojo.DO.CompanyDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.repository.CompanyRepository;
import indi.uhyils.util.Asserts;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.codec.binary.Base64;

/**
 * 厂商表(Company)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class Company extends AbstractDoEntity<CompanyDO> {

    @Default
    public Company(CompanyDO data) {
        super(data);
    }

    public Company(Long id) {
        super(id, new CompanyDO());
    }

    public Company(MysqlAuthCommand command) {
        super(parseCommand(command));
    }

    /**
     * 解析登录请求
     *
     * @param command
     *
     * @return
     */
    private static CompanyDO parseCommand(MysqlAuthCommand command) {
        final CompanyDO companyDO = new CompanyDO();
        final String ak = command.getUsername();
        companyDO.setAk(ak);
        return companyDO;
    }

    /**
     * 根据ak填充
     *
     * @param companyRepository
     */
    public void completionByAk(CompanyRepository companyRepository) {
        Company company = companyRepository.findByAk(toData().map(CompanyDO::getAk).orElse(null));
        Asserts.assertTrue(company != null, "未找到ak对应的公司,请联系管理");
        company.toData().ifPresent(t -> {
            this.data = t;
            setUnique(new Identifier(t.getId()));
        });

    }

    /**
     * 验证mysql登录时 sk是否一致
     *
     * @param seed
     * @param challenge
     *
     * @return
     */
    public boolean checkSkByMysqlChallenge(byte[] seed, String challenge) {
        // 1.1 密码解密
        Password realPassword = new Password(toData().get().getSk());
        // 1.2 密码再次加密为mysql SHA-1
        String decodePassword = realPassword.decode();
        byte[] bytes = MysqlUtil.encodePassword(decodePassword.getBytes(StandardCharsets.UTF_8), seed);
        String userPassword = Base64.encodeBase64String(bytes);
        return Objects.equals(userPassword, challenge);
    }
}
