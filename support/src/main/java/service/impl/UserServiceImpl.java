package service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import servie.UserService;

/**
 * @author lenovo
 */

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Override
    public String test() {
        return "孔超";
    }
}
