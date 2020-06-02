package com.kimtaehyun84.board.controller;

import com.kimtaehyun84.board.dto.BoardDto;
import com.kimtaehyun84.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();

        model.addAttribute("boardList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }

    @PostMapping("/post")
    public String writer(BoardDto boardDto){
        boardService.savePost(boardDto);

        return "redirect:/";
    }
}
