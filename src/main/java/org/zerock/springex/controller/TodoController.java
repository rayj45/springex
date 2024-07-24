package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO,
                     BindingResult bindingResult,
                     Model model){
//        log.info("todo list...");

        log.info(pageRequestDTO);

        if (bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }

//        model.addAttribute("dtoList", todoService.getAll()); //model영역에 dtoList라는 이름으로 service getall메서드 결과(dtoList)를 저장
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
        //pageRequestDTO를 매개변수로 리스트출력한 결과를 responseDTO라는 이름으로 보냄
//        todoService.getAll();
    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void registerGET(){
        log.info("todo register...");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //@Valid로 dto에서 사용한 valid어노테이션을 적용, BindingResult는 valid검증 오류를 보관하는 객체
        //RedirectAttributes는 리디렉션을 수행 시 컨트롤러에서 다른 컨트롤러 메서드로 attributes를 전달하는데 사용
        log.info("POST todo register...");

        if (bindingResult.hasErrors()){
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, Model model){

        TodoDTO todoDTO = todoService.getOne(tno);

        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){

        log.info("--------------------remove--------------------");
        log.info("tno : " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list";
    }

    @PostMapping("modify")
    public String modify(@Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno()); //받은 dto객체의 tno를 따서

            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }

} //class
