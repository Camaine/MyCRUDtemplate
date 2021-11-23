package com.greenspring.green.controller.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greenspring.green.config.auth.PrincipalDetails;
import com.greenspring.green.dto.ResponseDTO;
import com.greenspring.green.model.Board;
import com.greenspring.green.model.CharacterBoard;
import com.greenspring.green.service.BoardService;
import com.greenspring.green.service.CharacterBoardService;
import org.springframework.beans.factory.annotation.Autowired;
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
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}

	@PostMapping("/api/getCharacterList")
	public String getCharacterList(@RequestBody CharacterBoard characterBoard) {

		List<CharacterBoard> searchValList = characterBoardService.characterList(characterBoard);

		JsonObject obj = new JsonObject();
		obj.addProperty("title", "검색");

		JsonArray jsonArray = new JsonArray();
		for (CharacterBoard cb : searchValList) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", cb.getId());
			jsonObject.addProperty("characterName", cb.getCharacterName());
			jsonObject.addProperty("creatorName", cb.getCreatorName());
			jsonObject.addProperty("ownerName", cb.getOwnerName());
			jsonObject.addProperty("spices", cb.getSpices());
			jsonObject.addProperty("primaryColor", cb.getPrimaryColor());
			jsonObject.addProperty("secondaryColor", cb.getSecondaryColor());
			jsonObject.addProperty("birthDay", cb.getBirthDay());
			jsonObject.addProperty("characteristic", cb.getCharacteristic());
			jsonObject.addProperty("gender", cb.getGender());
			jsonObject.addProperty("bio", cb.getBio());
			jsonObject.addProperty("profileImageUrl", cb.getProfileImageUrl());
			jsonObject.addProperty("refImageUrl", cb.getRefImageUrl());
			jsonArray.add(jsonObject);
		}
		obj.add("data", jsonArray);

		return obj.toString();
	}

	@GetMapping("/api/getCharacterList/{id}")
	public String getCharacterDetailInfo(@PathVariable int id) {

		CharacterBoard cb = characterBoardService.characterSingleInfo(id);

		JsonObject obj = new JsonObject();
		obj.addProperty("title", "캐릭터정보");

		JsonArray jsonArray = new JsonArray();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", cb.getId());
		jsonObject.addProperty("ownerUid", cb.getOwnerUid());
		jsonObject.addProperty("characterName", cb.getCharacterName());
		jsonObject.addProperty("creatorName", cb.getCreatorName());
		jsonObject.addProperty("ownerName", cb.getOwnerName());
		jsonObject.addProperty("spices", cb.getSpices());
		jsonObject.addProperty("primaryColor", cb.getPrimaryColor());
		jsonObject.addProperty("secondaryColor", cb.getSecondaryColor());
		jsonObject.addProperty("birthDay", cb.getBirthDay());
		jsonObject.addProperty("characteristic", cb.getCharacteristic());
		jsonObject.addProperty("gender", cb.getGender());
		jsonObject.addProperty("bio", cb.getBio());
		jsonObject.addProperty("profileImageUrl", cb.getProfileImageUrl());
		jsonObject.addProperty("refImageUrl", cb.getRefImageUrl());
		jsonArray.add(jsonObject);
		obj.add("data", jsonArray);

		return obj.toString();
	}

	@PutMapping("/api/updateCharacter/{id}")
	public ResponseDTO<Integer> updateCharacter(@PathVariable int id, @RequestBody CharacterBoard characterBoard){
		characterBoardService.updateCharacter(id,characterBoard);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/api/deleteCharacter/{id}")
	public ResponseDTO<Integer> deleteCharacter(@PathVariable int id){
		characterBoardService.deleteCharacter(id);
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
 