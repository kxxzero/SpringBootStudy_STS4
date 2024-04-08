package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BoardDAO;
import com.sist.web.entity.Board;

@RestController
@CrossOrigin(origins = "*") // Port가 다르기 때문에 모든 IP를 허용하겠다는 뜻으로 작성
public class BoardRestController {
	// DAO 객체 가져오기
	@Autowired
	private BoardDAO bDao;
	
	
	// 목록 => @GetMapping => axios.get
	@GetMapping("/board/list/{page}")
	public ResponseEntity<Map> boardListData(@PathVariable("page") int page) {
		Map map=new HashMap();		
		try {
			int rowSize=10;
			int start=(rowSize*page)-rowSize; // LIMIT => 0번부터 시작하기 때문에 -10을 해야 함
			List<Board> list=bDao.boardListData(start);
			int totalpage=(int)(Math.ceil(bDao.count()/10.0)); // 전제 개수 / 10 => 총 페이지
			map.put("bList", list);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	// 상세보기 => @GetMapping => axios.get
	@GetMapping("/board/detail/{no}")
	public ResponseEntity<Board> boardDetailData(@PathVariable("no") int no) {
		Board board=new Board();
		try {
			board=bDao.findByNo(no);
			board.setHit(board.getHit()+1); // 조회수 증가
			bDao.save(board); // 변경된 내용 다시 저장
			board=bDao.findByNo(no); // 변경된 내용 저장 후 값 부여
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	
	// 작성 => @PostMapping => axios.post
	@PostMapping("/board/insert")
	public ResponseEntity<Map> boardInsert(@RequestBody Board board) {
		Map map=new HashMap();
		try {
			Board _board=bDao.save(board); // _board : 입력된 값을 그대로 받아옴
			map.put("board", _board);
			map.put("msg", "yes");
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 수정 => @PutMapping => axios.put
	@GetMapping("/board/update/{no}")
	public ResponseEntity<Board> boardUpdateData(@PathVariable("no") int no) {
		Board board=null;
		try {
			board=bDao.findByNo(no);
			
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@PutMapping("/board/update_ok/{no}")
	public ResponseEntity<Map> boardUpdateDataOk(@PathVariable("no") int no, @RequestBody Board board){
		Map map=new HashMap();
		
		try {
			Board dbBoard=bDao.findByNo(no);
			if(dbBoard.getPwd().equals(board.getPwd())) {
				board.setNo(no);
				board.setHit(dbBoard.getHit());
				Board b=bDao.save(board);
				map.put("board", b);
				map.put("msg", "yes");
			} else {
				map.put("msg", "no");
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 삭제 => @DeleteMapping => axios.delete
	@DeleteMapping("/board/delete/{no}/{pwd}")
	public ResponseEntity<Map> boardDelete(@PathVariable("no") int no, @PathVariable("pwd") String pwd) {
		Map map=new HashMap();
		try {
			
			Board board=bDao.findByNo(no);
			if(pwd.equals(board.getPwd())) {
				bDao.delete(board);
				map.put("msg", "yes");
			} else {
				map.put("msg", "no");
			}
			
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// => RESTful
}
