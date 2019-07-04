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
import cn.kinglian.uaas.service.IUserService;
import cn.kinglian.uaas.model.entity.User;
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
@RequestMapping("/a/user")
public class UserController {
private final Logger logger = LoggerFactory.getLogger(UserController.class);

@Autowired
public IUserService UserService;



/**
 * 分页查询数据
 *
 * @param bootStrapTable  分页信息
 * @param user   查询条件
 * @return
 */

@GetMapping("/getUserPageList")
public Map<String, Object> getUserList(BootStrapTable<User> bootStrapTable,User user) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
		result = bootStrapTable.setRows(iUserService.selectPage(bootStrapTable,user));
		} catch (Exception e) {
		logger.error("getUserList -=- {}",e.toString());
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

@RequestMapping(method=RequestMethod.GET,value="/userUpdate")
public ApiResult<User> userUpdate(HttpServletRequest request,Long id) {
		try {
    User user = UserService.selectById(id);
		request.setAttribute("user",user);
		}catch (Exception ex){
		logger.error("userUpdate -=- {}",ex.toString());
		}
		return "userUpd";
		}

/**
 * 保存和修改公用的
 * @param user  传递的实体
 * @return  0 失败  1 成功
 */

@RequestMapping(method=RequestMethod.POST,value="/userSave")
public int userSave(User user) {
		int count = 0;
		try {
		count = UserService.insertOrUpdate(user) ? 1 : 0;
		} catch (Exception e) {
		logger.error("userSave -=- {}",e.toString());
		}
		return count;
		}

/**
 * 根据id删除对象
 * @param id  实体ID
 * @return 0 失败  1 成功
 */

@RequestMapping(method= RequestMethod.POST,value="/userDelete")
public int userDelete(Long id){
		int count = 0;
		try {
		count = UserService.deleteById(id) ? 1 : 0;
		}catch (Exception e){
		logger.error("userDelete -=- {}",e.toString());
		}
		return count;
		}

/**
 * 批量删除对象
 * @param item 实体集合ID
 * @return  0 失败  1 成功
 */

@RequestMapping(method= RequestMethod.POST,value="/userBatchDelete")
public int deleteBatchIds(String item){
		int count = 0;
		try {
		List<Long> ids = (List<Long>) JSON.parse(item);
		count = UserService.deleteBatchIds(ids) ? 1 : 0;
		}catch (Exception e){
		logger.error("userBatchDelete -=- {}",e.toString());
		}
		return count;
		}


}