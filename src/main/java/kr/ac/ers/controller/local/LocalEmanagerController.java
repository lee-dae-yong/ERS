package kr.ac.ers.controller.local;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.ac.ers.command.SearchCriteria;
import kr.ac.ers.dto.AddressVO;
import kr.ac.ers.dto.CenterEmanagerVO;
import kr.ac.ers.dto.ManagerVO;
import kr.ac.ers.service.AddressService;
import kr.ac.ers.service.CenterEmanagerService;

@Controller
@RequestMapping("/ers/local/emanager")
public class LocalEmanagerController {

	@Autowired
	private CenterEmanagerService centerEmanagerService;
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/list")
	public void list(Model model, SearchCriteria cri, String dong, String status, HttpSession session) {
		ManagerVO manager = (ManagerVO)session.getAttribute("loginManager");
		
		if(cri.getPage()<1) cri.setPage(１);
		if(cri.getPerPageNum()<1) cri.setPerPageNum(10);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("dong", dong);
		returnMap.put("status", status);
		returnMap.put("cri", cri);
		returnMap.put("gu", manager.getDepart());
		
		Map<String, Object> dataMap = centerEmanagerService.getEmanagerList(cri,returnMap);
		
		List<AddressVO> dongList = null;
			 dongList = addressService.getDongList(manager.getDepart());
		
		model.addAttribute("dongList",dongList);
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public CenterEmanagerVO detail(@RequestParam int wcode) {
		CenterEmanagerVO emanager = centerEmanagerService.getEmanagerByWcode(wcode);
		
		return emanager;
	}
}
