package cn.kinglian.uaas.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dcy.model.BootStrapTable;
import cn.kinglian.uaas.model.entity.User;
import cn.kinglian.uaas.mapper.UserMapper;
import cn.kinglian.uaas.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.dcy.utils.lang.StringUtils;

import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenhy
 * @since 2019-07-02
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


@Autowired
private UserMapper userMapper;

@Override
public Page<User> selectPage(BootStrapTable<User> bootStrapTable, User user) {
		EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
		getEntityWrapper(entityWrapper,user);
		return super.selectPage(bootStrapTable.getPagePlus(),entityWrapper);
		}

@Override
public List<User> selectList(User user) {
		EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
		getEntityWrapper(entityWrapper,user);
		return super.selectList(entityWrapper);
		}

/**
 *  公共查询条件
 * @param entityWrapper
 * @return
 */
public EntityWrapper<User> getEntityWrapper(EntityWrapper<User> entityWrapper,User user){
		//条件拼接
                        				if (StringUtils.isNotBlank(user.${getprefix}Account())){
				entityWrapper.like(User.ACCOUNT,user.${getprefix}Account());
				}
                    				if (StringUtils.isNotBlank(user.${getprefix}Username())){
				entityWrapper.like(User.USERNAME,user.${getprefix}Username());
				}
                    				if (StringUtils.isNotBlank(user.${getprefix}Password())){
				entityWrapper.like(User.PASSWORD,user.${getprefix}Password());
				}
                    				if (StringUtils.isNotBlank(user.${getprefix}Phone())){
				entityWrapper.like(User.PHONE,user.${getprefix}Phone());
				}
                    				if (StringUtils.isNotBlank(user.${getprefix}Email())){
				entityWrapper.like(User.EMAIL,user.${getprefix}Email());
				}
                    				if (StringUtils.isNotBlank(user.${getprefix}Status())){
				entityWrapper.like(User.STATUS,user.${getprefix}Status());
				}
                    				if (StringUtils.isNotBlank(user.${getprefix}DeptId())){
				entityWrapper.like(User.DEPT_ID,user.${getprefix}DeptId());
				}
                    				if (StringUtils.isNotBlank(user.${getprefix}Remark())){
				entityWrapper.like(User.REMARK,user.${getprefix}Remark());
				}
            		return entityWrapper;
		}
}
