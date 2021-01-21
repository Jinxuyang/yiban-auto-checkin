package ink.verge.utils.checkin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fehead.lang.error.BusinessException;
import ink.verge.utils.checkin.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Verge
 * @since 2021-01-12
 */
public interface UserService extends IService<User> {

    /**
     * 获取未完成午间签到的人
     * @return 未完成午间签到的列表
     */
    List<User> getNoonUncheckUser();

    /**
     * 获取未完成晨间签到的人
     * @return 未完成晨间签到的列表
     */
    List<User> getMornUncheckUser();

    /**
     * 设置签到状态为true
     * @param id
     * @param status
     * @param type
     * @return
     */
    int setCheckinStatus(int id,boolean status, int type);

    /**
     * 通过手机号获取用户
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 通过openid获取用户
     * @param openId
     * @return
     */
    User getUserByOpenId(String openId);

    /**
     * 通过 account 更新用户的 openId
     * @param account
     * @param openId
     * @return
     */
    int updateOpenIdByAccount(String account, String openId) throws BusinessException;

    /**
     * 通过易班校验用户名和密码是否正确
     * @param username
     * @param password
     * @return
     */
    boolean verifyAccountByYiBan(String username,String password);

    /**
     * 校验用户名密码是否匹配
     * @param username
     * @param password
     * @return
     */
    boolean verifyAccount(String username,String password);
}
