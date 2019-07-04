package cn.kinglian.uaas.service;

import com.baomidou.mybatisplus.plugins.Page;
import cn.kinglian.uaas.model.entity.User;
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
public interface IUserService extends IService<User> {

		/**
		 *  分页查询
		 * @param bootStrapTable
		 * @param user
		 * @return
		 */
		Page<User> selectPage(BootStrapTable<User> bootStrapTable,User user);




		List<AppCertificate> selectList(User user);
}
