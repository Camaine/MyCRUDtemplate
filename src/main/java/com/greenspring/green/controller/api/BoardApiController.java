package com.greenspring.green.controller.api;

import com.greenspring.green.config.auth.PrincipalDetails;
import com.greenspring.green.dto.ResponseDTO;
import com.greenspring.green.model.Board;
import com.greenspring.green.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

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
}
 