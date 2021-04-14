package pro.techdict.bib.bibserver.services;

import pro.techdict.bib.bibserver.entities.Organization;
import pro.techdict.bib.bibserver.entities.UserAccount;
import pro.techdict.bib.bibserver.entities.UserDetails;
import pro.techdict.bib.bibserver.models.DUPLICATE_TYPES;

import java.util.List;
import java.util.Map;

public interface UserService {
  // 避免账号重复注册
  DUPLICATE_TYPES checkAndNoDuplicate(String userName, String email, String phone);

  // 用户注册
  UserAccount registerUser(String userName, String password, String phone, String email);

  // 通过邮箱找回用户
  UserAccount seekUserByEmail(String email);

  // 更改密码
  boolean changePassword(String email, String newPassword);

  // 发送手机验证码
  void sendSmsVerifyCode(String phone);

  // 获取加入的团队
  List<Organization> getJoinedOrgsByName(String userName);

  // 关注其他用户
  boolean followUser(Long srcUid, Long targetUid);

  // —————— 用户详细信息相关：

  // 获取用户详细信息
  UserDetails getUserDetailsById(Long userId);
  UserDetails getUserDetailsByName(String userName);

  // 更新用户详细信息
  UserDetails updateUserDetails(Map<String, String> newDetailsData);
}