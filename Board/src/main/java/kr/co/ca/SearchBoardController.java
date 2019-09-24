package kr.co.ca;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;
import kr.co.service.SBoardService;

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {
	
	@Inject
	private SBoardService sbService;
	
	@RequestMapping("/list")
	public void list(SPageTO sto, Model model) {
		SPageTO dbsto = sbService.list(sto);
		model.addAttribute("to", dbsto);
		
	}
	
	
	@RequestMapping("/amount/{perPage}")
	@ResponseBody
	public int list(@PathVariable("perPage") int perPage, SPageTO sto) {
		int amount= sbService.amount(sto);
		int totalPage=  (amount-1)/perPage +1;
		return totalPage;
	}
	
	
	
	@RequestMapping("/read")
	public void read(int bno, SPageTO sto, Model model) {
		BoardVO svo = sbService.read(bno);
		model.addAttribute("to", sto);
		model.addAttribute("vo", svo);
		
	}
	
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(int bno, SPageTO sto) throws Exception{
		sbService.del(bno);
		
		
		StringBuffer sb=new StringBuffer();
		sb.append("redirect:/sboard/list?curPage=");
		sb.append(sto.getCurPage());
		sb.append("&perPage=");
		sb.append(sto.getPerPage());
		sb.append("&searchType=");
		sb.append(sto.getSearchType());
		sb.append("&keyword=");
		sb.append(sto.getKeyword());
		return sb.toString();
	}
	
	
	@RequestMapping(value = "/modify", method=RequestMethod.GET)
	public void modifyUI(int bno,SPageTO sto ,Model model) {
		BoardVO vo= sbService.modifyUI(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("to", sto);
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String modify(BoardVO vo, SPageTO sto) {
		sbService.modify(vo);
		

		StringBuffer sb = new StringBuffer();
		sb.append("redirect:/sboard/read?bno=");
		sb.append(vo.getBno());
		sb.append("&curPage=");
		sb.append(sto.getCurPage());
		sb.append("&perPage=");
		sb.append(sto.getPerPage());
		sb.append("&searchType=");
		sb.append(sto.getSearchType());
		sb.append("&keyword=");
		sb.append(sto.getKeyword());
		
		return sb.toString();
	}
	
	
	
	

}
