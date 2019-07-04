package cn.kinglian.uaas.service;

import com.baomidou.mybatisplus.plugins.Page;
import cn.kinglian.uaas.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dcy.model.BootStrapTable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenhy
 * @since 2019-07-02
 */
public interface IRoleService extends IService<Role> {

		/**
		 *  分页查询
		 * @param bootStrapTable
		 * @param role
		 * @return
		 */
		Page<Role> selectPage(BootStrapTable<Role> bootStrapTable,Role role);




		List<AppCertificate> selectList(Role role);
}
