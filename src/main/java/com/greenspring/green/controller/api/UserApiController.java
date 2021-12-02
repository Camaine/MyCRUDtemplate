package com.greenspring.green.controller.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greenspring.green.model.Board;
import com.greenspring.green.model.CharacterBoard;
import com.greenspring.green.model.TwtUser;
import com.greenspring.green.service.ServiceLogService;
import com.greenspring.green.service.TwitterService;
import com.greenspring.green.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.greenspring.green.dto.ResponseDTO;
import com.greenspring.green.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private TwitterService twitterService;

	@Autowired
	private ServiceLogService serviceLogService;

	@PostMapping("/auth/doJoin")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("Call UserApiController : save");
		userService.userRegister(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}

	@PostMapping("/auth/twtJoin")
	public ResponseDTO<Integer> twtSave(@RequestBody TwtUser twtUser) {
		System.out.println("Call UserApiController : twtsave");
		twitterService.twtUserRegister(twtUser);
		serviceLogService.saveServiceLog("AFI0000001", twtUser.getUid(), "tweet id:"+twtUser.getScreenName(), 200);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}

	@PutMapping("/auth/twtUpdate/{uid}")
	public ResponseDTO<Integer> twtUpdate(@PathVariable String uid, @RequestBody TwtUser twtUser){
		twitterService.twtUserUpdate(uid,twtUser);
		serviceLogService.saveServiceLog("AFI0000002", twtUser.getUid(), "tweet id:"+twtUser.getScreenName(), 200);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}

	@PutMapping("/auth/twtAccountUpdate/{uid}")
	public ResponseDTO<Integer> twtAccountUpdate(@PathVariable String uid, @RequestBody TwtUser twtUser){
		twitterService.twtUserUpdateDisplayNameLang(uid,twtUser);
		serviceLogService.saveServiceLog("AFI0000003", twtUser.getUid(), twtUser.getDisplayName()+","+twtUser.getLang(), 200);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}

	@PostMapping("/auth/twtUserInfo")
	public String twtUserInfo(@RequestBody TwtUser twtUser) {

		TwtUser tu = twitterService.twtUserInfo(twtUser.getUid());

		JsonObject obj = new JsonObject();
		obj.addProperty("title", "캐릭터정보");

		JsonArray jsonArray = new JsonArray();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", tu.getUid());
		jsonObject.addProperty("displayName", tu.getDisplayName());
		jsonObject.addProperty("role", tu.getRole());
		jsonObject.addProperty("lang", tu.getLang());
		jsonObject.addProperty("characterCnt", tu.getCharacterCnt());
		jsonObject.addProperty("photoURL", tu.getPhotoURL());
		jsonArray.add(jsonObject);
		obj.add("data", jsonArray);

		serviceLogService.saveServiceLog("AFI0000004", twtUser.getUid(), "get user info", 200);
		return obj.toString();
	}
}
 