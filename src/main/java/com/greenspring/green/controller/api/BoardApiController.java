package com.greenspring.green.controller.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greenspring.green.config.auth.PrincipalDetails;
import com.greenspring.green.dto.ResponseDTO;
import com.greenspring.green.model.Board;
import com.greenspring.green.model.CharacterBoard;
import com.greenspring.green.service.BoardService;
import com.greenspring.green.service.CharacterBoardService;
import com.greenspring.green.service.CodeService;
import com.greenspring.green.service.ServiceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private CharacterBoardService characterBoardService;

	@Autowired
	private CodeService codeService;

	@Autowired
	private ServiceLogService serviceLogService;

	@PostMapping("/api/board")
	public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.post(board,principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDTO<Integer> deleteById(@PathVariable int id){
		boardService.deletePost(id);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDTO<Integer> update(@PathVariable int id, @RequestBody Board board){
		System.out.println("id : "+id);
		boardService.updatePost(id,board);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}

	@PostMapping("/api/saveCharacterInfo")
	public ResponseDTO<Integer> saveCharacterInfo(@RequestBody CharacterBoard characterBoard) {
		System.out.println("Call BoardApiController : characterInfo");
		characterBoardService.post(characterBoard);
		serviceLogService.saveServiceLog("AFI0000011", characterBoard.getOwnerUid(), characterBoard.getId()+","+characterBoard.getCharacterName(), 200);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}

	@PostMapping("/api/checkAuthPost")
	public ResponseDTO<Integer> checkAuthPost(@RequestBody CharacterBoard characterBoard) {
		if(characterBoardService.postAuthCheck(characterBoard) == 0){
			serviceLogService.saveServiceLog("AFI0000012", characterBoard.getOwnerUid(), characterBoard.getCharacterName(), 200);
			return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
		}

		if(characterBoardService.postAuthCheck(characterBoard) == 1){
			serviceLogService.saveServiceLog("AFI0000012", characterBoard.getOwnerUid(), characterBoard.getCharacterName(), 205);
			return new ResponseDTO<Integer>(HttpStatus.RESET_CONTENT.value(),1); // change Java Object to JSON
		}

		serviceLogService.saveServiceLog("AFI0000012", characterBoard.getOwnerUid(), characterBoard.getCharacterName(), 203);
		return new ResponseDTO<Integer>(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(),1); // change Java Object to JSON
	}

	@PostMapping("/api/getCharacterList/{lang}")
	public String getCharacterList(@PathVariable String lang,@RequestBody CharacterBoard characterBoard, @PageableDefault(size = 24) Pageable pageable) {

		List<CharacterBoard> searchValList = characterBoardService.characterList(characterBoard, pageable);

		JsonObject obj = new JsonObject();
		obj.addProperty("title", "검색");

		String speices = "";
		String primaryColor = "";
		String secondaryColor = "";
		String gender = "";

		JsonArray jsonArray = new JsonArray();
		for (CharacterBoard cb : searchValList) {
			speices = codeService.getLocalizedString(cb.getSpices(),lang);
			primaryColor = codeService.getLocalizedString(cb.getPrimaryColor(),lang);
			secondaryColor = codeService.getLocalizedString(cb.getSecondaryColor(),lang);
			gender = codeService.getLocalizedString(cb.getGender(),lang);

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", cb.getId());
			jsonObject.addProperty("characterName", cb.getCharacterName());
			jsonObject.addProperty("creatorName", cb.getCreatorName());
			jsonObject.addProperty("ownerName", cb.getOwnerName());
			jsonObject.addProperty("spices", speices);
			jsonObject.addProperty("primaryColor", primaryColor);
			jsonObject.addProperty("secondaryColor", secondaryColor);
			jsonObject.addProperty("birthDay", cb.getBirthDay());
			jsonObject.addProperty("characteristic", cb.getCharacteristic());
			jsonObject.addProperty("gender", gender);
			jsonObject.addProperty("bio", cb.getBio());
			jsonObject.addProperty("profileImageUrl", cb.getProfileImageUrl());
			jsonObject.addProperty("refImageUrl", cb.getRefImageUrl());
			jsonArray.add(jsonObject);
		}
		obj.add("data", jsonArray);

		serviceLogService.saveServiceLog("AFI0000013", "unknown", "get character List", 200);
		return obj.toString();
	}

	@GetMapping("/api/getCharacterList/{id}/{lang}")
	public String getCharacterDetailInfo(@PathVariable int id,@PathVariable String lang) {

		CharacterBoard cb = characterBoardService.characterSingleInfo(id);

		JsonObject obj = new JsonObject();
		obj.addProperty("title", "캐릭터정보");

		String speices = "";
		String primaryColor = "";
		String secondaryColor = "";
		String gender = "";

		speices = codeService.getLocalizedString(cb.getSpices(),lang);
		primaryColor = codeService.getLocalizedString(cb.getPrimaryColor(),lang);
		secondaryColor = codeService.getLocalizedString(cb.getSecondaryColor(),lang);
		gender = codeService.getLocalizedString(cb.getGender(),lang);

		JsonArray jsonArray = new JsonArray();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", cb.getId());
		jsonObject.addProperty("ownerUid", cb.getOwnerUid());
		jsonObject.addProperty("characterName", cb.getCharacterName());
		jsonObject.addProperty("creatorName", cb.getCreatorName());
		jsonObject.addProperty("ownerName", cb.getOwnerName());
		jsonObject.addProperty("spices", speices);
		jsonObject.addProperty("primaryColor", primaryColor);
		jsonObject.addProperty("secondaryColor", secondaryColor);
		jsonObject.addProperty("birthDay", cb.getBirthDay());
		jsonObject.addProperty("characteristic", cb.getCharacteristic());
		jsonObject.addProperty("gender", gender);
		jsonObject.addProperty("bio", cb.getBio());
		jsonObject.addProperty("profileImageUrl", cb.getProfileImageUrl());
		jsonObject.addProperty("refImageUrl", cb.getRefImageUrl());
		jsonArray.add(jsonObject);
		obj.add("data", jsonArray);

		serviceLogService.saveServiceLog("AFI0000014", "unknown", String.valueOf(id), 200);
		return obj.toString();
	}

	@PutMapping("/api/updateCharacter/{id}")
	public ResponseDTO<Integer> updateCharacter(@PathVariable int id, @RequestBody CharacterBoard characterBoard){
		characterBoardService.updateCharacter(id,characterBoard);
		serviceLogService.saveServiceLog("AFI0000015", characterBoard.getOwnerUid(), String.valueOf(id), 200);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/api/deleteCharacter/{id}")
	public ResponseDTO<Integer> deleteCharacter(@PathVariable int id){
		characterBoardService.deleteCharacter(id);
		serviceLogService.saveServiceLog("AFI0000016", "unknown", String.valueOf(id), 200);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}

	@GetMapping("/api/latestCharacterId")
	public String getLatestCharacterId(){
		JsonObject obj = new JsonObject();
		obj.addProperty("title", "maxId");

		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("maxId",  characterBoardService.getLatestCharacterId()+1);
		jsonArray.add(jsonObject);
		obj.add("data",jsonArray);

		return obj.toString();
	}
}
 