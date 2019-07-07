package com.ocean.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.query.UserQuery;
import com.ocean.utils.AjaxResponse;
import com.ocean.utils.MD5;
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
	@GetMapping("/getUserPageList")
	public AjaxResponse getUserList(UserQuery query) {

	    AjaxResponse ajaxResponse = null;
		try {
			Page<User> page = iUserService.selectPage(query);
			ajaxResponse = AjaxResponse.ok(page);
		} catch (Exception e) {
		    logger.error("getUserList -=- {}",e.toString());
			ajaxResponse = AjaxResponse.error();
		}
		return ajaxResponse;
	}

	/**
	 * 跳转添加页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/userAdd")
	public String userAdd(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {


		}catch (Exception ex){
		    logger.error("userAdd -=- {}",ex.toString());
		}
		return "userAdd";
	}

	/**
	 * 跳转修改页面
	 * @param request
	 * @param id  实体ID
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/userUpdate")
	public String userUpdate(HttpServletRequest request,Long id) {
		try {
            User user = iUserService.getById(id);
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
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/userSave")
	public int userSave(User user) {
		int count = 0;
		try {

		    count = iUserService.updateById(user) ? 1 : 0;
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
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value="/userDelete")
	public int userDelete(String id){
		int count = 0;
		try {
		    count = iUserService.delete(id) ? 1 : 0;
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
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value="/userBatchDelete")
	public int deleteBatchIds(String item){

		int count = 0;
		try {
			List<String> ids = (List<String>) JSON.parse(item);
		count = iUserService.deleteByListId(ids) ? 1 : 0;
		}catch (Exception e){
		    logger.error("userBatchDelete -=- {}",e.toString());
		}
		return count;
	}


}