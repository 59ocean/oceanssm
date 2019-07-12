package com.ocean.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.entity.BaseEntity;
import com.ocean.query.UserQuery;
import com.ocean.utils.AjaxResponse;
import com.ocean.utils.MD5;
import com.ocean.utils.PageResult;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ocean.service.IUserService;
import com.ocean.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chenhy
 * @since 2019-06-25
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
private final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	public IUserService iUserService;
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value = {"/login"})
	public AjaxResponse login(String account,String password){
		AjaxResponse result = null;
		try {
			Map<String,Object> params = new HashMap<>();
			 params.put("account",account);
			List<User> userList = iUserService.listByMap(params);
			if(userList!=null && userList.size() > 0){
				User user = userList.get(0);
				if(MD5.verify(user.getAccount(),password,user.getPassword())){
				    getSession().setAttribute("user",user);
                    System.out.println("登录成功");
				    result = AjaxResponse.okMsg("登录成功！");
                }else {
                    result = AjaxResponse.errorMsg("账号或密码错误");
                }
			}else{
                    result = AjaxResponse.errorMsg("账号或密码错误");
			}
		}catch (Exception e){
            result = AjaxResponse.errorMsg("服务器错误");
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(method= RequestMethod.GET,value = {"/home"})
	public String home(){

		return "index";
	}

	/**
	 * 跳转列表页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET,value = {"/userIndex"})
	public String index(HttpServletRequest request, Model model) {
			return "userListIndex";
			}

	/**
	 * 分页查询数据
	 *
	 * @param query  分页信息
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
	public PageResult getUserList(UserQuery query) {
	    Page<User> page = iUserService.selectPage(query);
		return PageResult.ok(page.getTotal(),page.getRecords());
	}

	/**
	 * 跳转添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/toAdd")
	public String userAdd(HttpServletRequest request,HttpServletResponse response) {
		try {


		}catch (Exception ex){
		    logger.error("userAdd -=- {}",ex.toString());
		}
		return "user/user_add";
	}

	/**
	 * 跳转修改页面
	 * @param request
	 * @param id  实体ID
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/toEdit")
	public String toEdit(HttpServletRequest request,String id) {
		try {
            User user = iUserService.getById(id);
		    request.setAttribute("u",user);
		}catch (Exception ex){

		    logger.error("userUpdate -=- {}",ex.toString());
		}
		return "user/user_edit";
	}

	/**
	 * 跳转修改页面
	 * @param request
	 * @param id  实体ID
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/view")
	public String view(HttpServletRequest request,String id) {
		try {
			User user = iUserService.getById(id);
			request.setAttribute("u",user);
		}catch (Exception ex){
			logger.error("userUpdate -=- {}",ex.toString());
		}
		return "user/user_view";
	}
	/**
	 * 更新操作
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/update")
	public AjaxResponse userUpdate(HttpServletRequest request,User user) {
		AjaxResponse ajaxResponse = null;
		try {
			updateBaseEntity(user);
			iUserService.updateById(user);
			ajaxResponse = AjaxResponse.ok();
		}catch (Exception ex){
			ajaxResponse = AjaxResponse.errorMsg(ex.toString());
			logger.error("userUpdate -=- {}",ex.toString());
		}
		return ajaxResponse;
	}


	/**
	 * 保存和修改公用的
	 * @param user  传递的实体
	 * @return  0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/save")
	public AjaxResponse userSave(User user) {
		AjaxResponse result = null;
		try {
			saveBaseEntity(user);
			user.setStatus(1);
			user.setPassword(MD5.md5(user.getAccount(),user.getPassword()));
			iUserService.save(user);
			System.out.println("+++++============"+user.getId());
			result = AjaxResponse.ok();
		} catch (Exception e) {
		    logger.error("userSave -=- {}",e.toString());
			result = AjaxResponse.errorMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 根据id删除对象
	 * @param id  实体ID
	 * @return 0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value="/delete")
	public AjaxResponse userDelete(String id){
		AjaxResponse result = null;
		try {
		    iUserService.delete(id);
			result = AjaxResponse.ok();
		}catch (Exception e){
			e.printStackTrace();
			logger.error("userDelete -=- {}",e.toString());
			result = AjaxResponse.errorMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 批量删除对象
	 * @param item 实体集合ID
	 * @return  0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value="/userBatchDelete")
	public int deleteBatchIds(String item){

		int count = 0;
		try {
			List<String> ids = (List<String>) JSON.parse(item);
		count = iUserService.deleteByListId(ids) ? 1 : 0;
		}catch (Exception e){
			e.printStackTrace();
		    logger.error("userBatchDelete -=- {}",e.toString());
		}
		return count;
	}


}