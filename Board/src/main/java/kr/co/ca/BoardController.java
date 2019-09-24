package kr.co.ca;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;
import kr.co.utils.UploadFileUtils;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Inject
	private BoardService bService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	
	@ResponseBody
	@RequestMapping(value="/deletefile", method = RequestMethod.POST)
	public ResponseEntity<String> deletefile(String filename, int bno){
		System.out.println(filename+";;;;;;;;deletefile;;;;;;;;;;;;;;;;;");
		System.out.println(bno+";;;;;;;;deletefile;;;;;;;;;;;;;;;;;");
		bService.deleteAttach(filename, bno);
		
		 return UploadFileUtils.deletefile(uploadPath, filename);
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getattach/{bno}")
	public List<String> getAttach(@PathVariable("bno") int bno){
		return bService.getAttach(bno);
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createUI() {
		
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(BoardVO vo) throws Exception {
		bService.create(vo);
		
		return "redirect:/board/list";
	}
	
	
	@RequestMapping("/listall")
	public String listall(Model model) throws Exception{
		List<BoardVO> list = bService.listall();
		
		model.addAttribute("list", list);
		
		return "/board/listall";
	}
	
	
	@RequestMapping("/read")
	public void read(int bno, PageTO<BoardVO> to ,Model model) throws Exception{
		BoardVO vo = bService.read(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("to", to);
	}
	
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(int bno, PageTO<BoardVO> to, Model model) throws Exception{
		bService.del(bno);
		
		
		return "redirect:/board/list?curPage="+to.getCurPage()+"&perPage="+to.getPerPage();
	}
	
	
	@RequestMapping(value = "/modify", method=RequestMethod.GET)
	public void modifyUI(int bno,PageTO<BoardVO> to ,Model model) {
		BoardVO vo= bService.modifyUI(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("to", to);
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String modify(BoardVO vo, PageTO<BoardVO> to) {
		bService.modify(vo);
		

		return "redirect:/board/read?bno="+vo.getBno()
		+"&curPage="+to.getCurPage()+"&perPage="+to.getPerPage();
	}
	
	
	@RequestMapping("/list")
	public void list(PageTO<BoardVO> to, Model model) {
		PageTO<BoardVO> dbTO= bService.list(to);
		model.addAttribute("to", dbTO);
	}
	
	@RequestMapping("/amount/{perPage}")
	@ResponseBody
	public int list(@PathVariable("perPage")  int perPage) {
		int amount= bService.amount();
		return (amount-1)/perPage +1;
	}
	
	
	
	
	
	

}
