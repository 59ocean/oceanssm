package cn.kinglian.uaas.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.dcy.constant.Constant;
import com.dcy.model.BootStrapTable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import cn.kinglian.common.util.ApiResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.kinglian.uaas.service.IRoleService;
import cn.kinglian.uaas.model.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chenhy
 * @since 2019-07-02
 */
@RestController
@RequestMapping("/a/role")
public class RoleController {
private final Logger logger = LoggerFactory.getLogger(RoleController.class);

@Autowired
public IRoleService RoleService;



/**
 * 分页查询数据
 *
 * @param bootStrapTable  分页信息
 * @param role   查询条件
 * @return
 */

@GetMapping("/getRolePageList")
public Map<String, Object> getRoleList(BootStrapTable<Role> bootStrapTable,Role role) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
		result = bootStrapTable.setRows(iRoleService.selectPage(bootStrapTable,role));
		} catch (Exception e) {
		logger.error("getRoleList -=- {}",e.toString());
		result.put(Constant.BOOTSTAP_TABLE_ROWS, new ArrayList<>());
		result.put(Constant.BOOTSTRAP_TABLE_TOTAL, 0);
		}
		return result;
		}



/**
 * 跳转修改页面
 * @param request
 * @param id  实体ID
 * @return
 */

@RequestMapping(method=RequestMethod.GET,value="/roleUpdate")
public ApiResult<Role> roleUpdate(HttpServletRequest request,Long id) {
		try {
    Role role = RoleService.selectById(id);
		request.setAttribute("role",role);
		}catch (Exception ex){
		logger.error("roleUpdate -=- {}",ex.toString());
		}
		return "roleUpd";
		}

/**
 * 保存和修改公用的
 * @param role  传递的实体
 * @return  0 失败  1 成功
 */

@RequestMapping(method=RequestMethod.POST,value="/roleSave")
public int roleSave(Role role) {
		int count = 0;
		try {
		count = RoleService.insertOrUpdate(role) ? 1 : 0;
		} catch (Exception e) {
		logger.error("roleSave -=- {}",e.toString());
		}
		return count;
		}

/**
 * 根据id删除对象
 * @param id  实体ID
 * @return 0 失败  1 成功
 */

@RequestMapping(method= RequestMethod.POST,value="/roleDelete")
public int roleDelete(Long id){
		int count = 0;
		try {
		count = RoleService.deleteById(id) ? 1 : 0;
		}catch (Exception e){
		logger.error("roleDelete -=- {}",e.toString());
		}
		return count;
		}

/**
 * 批量删除对象
 * @param item 实体集合ID
 * @return  0 失败  1 成功
 */

@RequestMapping(method= RequestMethod.POST,value="/roleBatchDelete")
public int deleteBatchIds(String item){
		int count = 0;
		try {
		List<Long> ids = (List<Long>) JSON.parse(item);
		count = RoleService.deleteBatchIds(ids) ? 1 : 0;
		}catch (Exception e){
		logger.error("roleBatchDelete -=- {}",e.toString());
		}
		return count;
		}


}