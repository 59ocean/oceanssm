package cn.kinglian.uaas.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dcy.model.BootStrapTable;
import cn.kinglian.uaas.model.entity.Role;
import cn.kinglian.uaas.mapper.RoleMapper;
import cn.kinglian.uaas.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


@Autowired
private RoleMapper roleMapper;

@Override
public Page<Role> selectPage(BootStrapTable<Role> bootStrapTable, Role role) {
		EntityWrapper<Role> entityWrapper = new EntityWrapper<Role>();
		getEntityWrapper(entityWrapper,role);
		return super.selectPage(bootStrapTable.getPagePlus(),entityWrapper);
		}

@Override
public List<Role> selectList(Role role) {
		EntityWrapper<Role> entityWrapper = new EntityWrapper<Role>();
		getEntityWrapper(entityWrapper,role);
		return super.selectList(entityWrapper);
		}

/**
 *  公共查询条件
 * @param entityWrapper
 * @return
 */
public EntityWrapper<Role> getEntityWrapper(EntityWrapper<Role> entityWrapper,Role role){
		//条件拼接
                        				if (StringUtils.isNotBlank(role.${getprefix}RoleName())){
				entityWrapper.like(Role.ROLE_NAME,role.${getprefix}RoleName());
				}
                    				if (StringUtils.isNotBlank(role.${getprefix}Remark())){
				entityWrapper.like(Role.REMARK,role.${getprefix}Remark());
				}
            		return entityWrapper;
		}
}
