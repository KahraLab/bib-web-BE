package pro.techdict.bib.bibserver.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.techdict.bib.bibserver.entities.UserDetails;
import pro.techdict.bib.bibserver.services.TencentCOSService;
import pro.techdict.bib.bibserver.services.UserService;
import pro.techdict.bib.bibserver.utils.COSUploadResultWithKey;
import pro.techdict.bib.bibserver.utils.COSUtils;
import pro.techdict.bib.bibserver.utils.HttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/details")
public class UserDetailsController {

  final UserService userService;
  final TencentCOSService cosService;

  public UserDetailsController(
      UserService userService,
      TencentCOSService cosService
  ) {
    this.userService = userService;
    this.cosService = cosService;
  }

  @GetMapping("/")
  public HttpResponse getUserDetails(
      @RequestParam(value = "uid", required = false) Long uid,
      @RequestParam(value = "userName", required = false) String userName
  ) {
    UserDetails userDetails = uid != null
        ? userService.getUserDetailsById(uid)
        : userService.getUserDetailsByName(userName);
    if (userDetails != null) {
      return HttpResponse.success("获取用户详细信息成功！", userDetails);
    } else return HttpResponse.fail("未找到该用户的详细信息！");
  }

  @PostMapping("/")
  public HttpResponse updateUserDetails(@RequestBody Map<String, String> requestBody) {
    UserDetails newDetails = userService.updateUserDetails(requestBody);
    if (newDetails != null) {
      return HttpResponse.success("修改用户资料成功！", newDetails);
    }
    return HttpResponse.fail("修改用户资料失败！");
  }

  @PostMapping("/uploadAvatar")
  public HttpResponse updateAvatar(
      @RequestParam("userId") String userId,
      @RequestParam("uploadImages") MultipartFile[] uploadFiles
  ) {
    List<COSUploadResultWithKey> uploadResults =
        cosService.uploadObjects(COSUtils.getPrefixWithUserId(
            "bibweb/user-avatars/", Long.parseLong(userId)
        ), uploadFiles);
    Map<String, Object> data = new HashMap<>();
    data.put("newAvatar", uploadResults);

    return HttpResponse.success("上传新头像成功！", data);
  }
}
