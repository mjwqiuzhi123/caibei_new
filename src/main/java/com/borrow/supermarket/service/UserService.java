package com.borrow.supermarket.service;

import com.borrow.supermarket.dao.UserDao;
import com.borrow.supermarket.model.UserModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.request.dto.CheckIsPhoneRequestDTO;
import com.borrow.supermarket.request.dto.LoginOnDTO;
import com.borrow.supermarket.request.dto.UserLoginRequestDTO;
import com.borrow.supermarket.request.dto.UserRegisterRequestDTO;
import com.borrow.supermarket.response.result.UseVeriCodeResultDTO;
import com.borrow.supermarket.response.result.UserLoginDTOResult;
import com.borrow.supermarket.util.CommonUtil;
import com.borrow.supermarket.util.DateUtil;
import com.borrow.supermarket.util.PasswordHash;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	  @Autowired
	  private UserDao userDaoI;

	  public ResponseEntity searchUserByNameAndPwd(UserLoginRequestDTO userLoginRequestDTO, StringBuilder userIdentifier, StringBuilder cellphone) { ResponseEntity responseEntity = new ResponseEntity();
	    try {
	      UserModel userModel = this.userDaoI.searcUserByPhone(new UserModel(userLoginRequestDTO));

	      if (userModel == null) {
	        UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(0), false, false);
	        responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_ONE);
	        responseEntity.addProperty(resultDTO);
	        return responseEntity;
	      }

	      if (userModel.getLastfailedsignintime() != null)
	      {
	        if ((DateUtil.GetDate(userModel
	          .getLastfailedsignintime())
	          .equals(DateUtil.GetDate(new Date()))) && 
	          (userModel.getLoginfailedcount() >= 4)) {
	          UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(0), true, true);
	          responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_THREE);
	          responseEntity.addProperty(resultDTO);
	          return responseEntity;
	        }

	      }

	      String encryptPassword = PasswordHash.createHash(userLoginRequestDTO.getPassword(), userModel.getSalt());
	      if (!userModel.getPassword().equals(encryptPassword))
	      {
	        if (userModel.getLastfailedsignintime() != null)
	        {
	          if (DateUtil.GetDate(userModel.getLastfailedsignintime()).equals(DateUtil.GetDate(new Date())))
	          {
	            userModel.setLoginfailedcount(userModel.getLoginfailedcount() + 1); 
	            //break label220; MJW
	          }
	        }
	        userModel.setLoginfailedcount(1);

	        label220: userModel.setLastfailedsignintime(new Date());
	        this.userDaoI.updateByEntity(userModel);

	        UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(5 - userModel.getLoginfailedcount()), true, false);
	        responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_TWO);
	        responseEntity.addProperty(resultDTO);
	        return responseEntity;
	      }

	      userModel.setLastsuccesssignintime(new Date());
	      userModel.setLoginfailedcount(0);
	      this.userDaoI.updateByEntity(userModel);
	      UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(5), true, false);
	      userIdentifier.append(userModel.getUseridentifier());
	      cellphone.append(userModel.getPhone());
	      responseEntity.addProperty(resultDTO);
	      return responseEntity;
	    } catch (Exception e) {
	      logger.error(new StringBuilder().append("根据用户名和密码查询用户失败失败的原因是:").append(e.getMessage()).toString());
	      logger.error(new StringBuilder().append("参数phone = ").append(userLoginRequestDTO).toString() == null ? null : userLoginRequestDTO.getPhone());
	      logger.error(new StringBuilder().append("参数password = ").append(userLoginRequestDTO).toString() == null ? null : userLoginRequestDTO.getPassword());
	      responseEntity.setMsg(ServiceCode.EXCEPTION);
	    }return responseEntity;
	  }

	  public UserModel searchAdminByNameAndPwd(LoginOnDTO loginOnDTO)
	  {
	    UserModel userModel = null;
	    try {
	      userModel = this.userDaoI.searcUserByPhone(new UserModel(loginOnDTO));//mjw
	      String encryptPassword = PasswordHash.createHash(loginOnDTO.getAdminPwd(), userModel.getSalt());
	      if (!userModel.getPassword().equals(encryptPassword))
	        return null;
	    }
	    catch (Exception e) {
	      logger.error(new StringBuilder().append("管路员登陆异常-----------error--------").append(e.getMessage()).toString());
	    }
	    return userModel;
	  }

	  public boolean saveUser(UserRegisterRequestDTO userRegisterRequestDTO, UseVeriCodeResultDTO useVeriCodeResultDTO) {
	    try {
	      String salt = CommonUtil.getUUID();
	      String userIdentifier = CommonUtil.getUUID();
	      String password = userRegisterRequestDTO.getPassword();
	      String saltPassword = PasswordHash.createHash(password, salt);
	      return this.userDaoI.saveUser(new UserModel(useVeriCodeResultDTO, salt, userIdentifier, saltPassword));
	    } catch (Exception e) {
	      logger.error(new StringBuilder().append("用户注册失败失败的原因是:").append(e.getMessage()).toString());
	      logger.error(new StringBuilder().append("参数phone = ").append(userRegisterRequestDTO).toString() == null ? null : useVeriCodeResultDTO.getPhone());
	      logger.error(new StringBuilder().append("参数password = ").append(userRegisterRequestDTO).toString() == null ? null : userRegisterRequestDTO.getPassword());
	    }return false;
	  }

	  public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
	  {
	    String salt = CommonUtil.getUUID();
	    String userIdentifier = CommonUtil.getUUID();
	    String saltPassword = PasswordHash.createHash("guo543427301", salt);
	    System.out.println(userIdentifier);
	    System.out.println(salt);
	    System.out.println(saltPassword);
	  }

	  public boolean searcUserByPhone(CheckIsPhoneRequestDTO checkIsPhoneRequestDTO) {
	    try {
	      return this.userDaoI.searcUserByPhone(new UserModel(checkIsPhoneRequestDTO)) == null;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error(new StringBuilder().append("用户手机号检查失败失败的原因是:").append(e.getMessage()).toString());
	      logger.error(new StringBuilder().append("参数phone = ").append(checkIsPhoneRequestDTO).toString() == null ? null : checkIsPhoneRequestDTO.getPhone());
	    }return false;
	  }

	  public ResponseEntity resetLoginPassword(String phone, String password, String token)
	  {
	    ResponseEntity messageResult = new ResponseEntity();
	    UserModel user = this.userDaoI.searcUserByPhone(new UserModel(phone));

	    if (user == null) {
	      messageResult.setMsg(ServiceCode.SING_IN_REPONSE_ONE);
	      return messageResult;
	    }

	    String encryptedpassword = null;
	    try {
	      encryptedpassword = PasswordHash.createHash(password, user
	        .getSalt());
	    } catch (Exception e) {
	      logger.error(new StringBuilder().append("【注册】用户密码加密失败;操作类是VericodesServiceI操作方法是SignUpAsync失败的原因是:")
	        .append(e
	        .getMessage()).toString());
	      messageResult.setMsg(ServiceCode.SIGNUPASYNC_ONE);
	      return messageResult;
	    }

	    user.setPassword(encryptedpassword);
	    user.setLoginfailedcount(0);

	    Integer returnCode = this.userDaoI.updateByEntity(user);
	    if (returnCode.intValue() <= 0) {
	      logger.error(new StringBuilder().append("交易密码保存失败,调用DAO的方法是:update操作接口是UsersDao是,入参是:")
	        .append(user
	        .toString()).toString());
	      messageResult.setMsg(ServiceCode.DATABASE_UPDATE_ERROR);
	      return messageResult;
	    }

	    return messageResult;
	  }

	  public List<UserModel> selectUserList(UserModel userModel, PageParameter parameter)
	  {
	    try {
	      Map map = new HashMap();
	      map.put("t", userModel);
	      map.put("page", parameter);
	      return this.userDaoI.getByPage(map);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      logger.error(new StringBuilder().append("用户列表展示操作异常---原因是-----:").append(e.getMessage()).toString());
	    }return null;
	  }

	  public UserModel searchUserByUserIdentifier(String userIdentifier)
	  {
	    try
	    {
	      return this.userDaoI.searchUserByUserIdentifier(userIdentifier);
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error(new StringBuilder().append("按照用户编号查询用户信息操作异常---原因是-----:").append(e.getMessage()).toString());
	    }return null;
	  }
}