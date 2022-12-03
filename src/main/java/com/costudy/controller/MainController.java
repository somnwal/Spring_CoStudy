package com.costudy.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.costudy.entity.GroupReg;
import com.costudy.entity.StudyGroup;
import com.costudy.entity.User;
import com.costudy.service.GroupRegService;
import com.costudy.service.StudyGroupService;
import com.costudy.service.UserService;
import com.costudy.vo.UserVO;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupRegService groupRegService;
	
	@Autowired
	private StudyGroupService studyGroupService;
	
	@GetMapping
	public String index(Model m, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user != null) {
			return "redirect:/dashboard";
		}
		
		return "index";
	}
	
	@GetMapping("/index")
	public String index2(Model m, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user != null) {
			return "redirect:/dashboard";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	@GetMapping("/login")
	public String login(Model m, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		String error = (String) session.getAttribute("error");
		String message = (String) session.getAttribute("message");
		
		if(user != null) {
			return "redirect:/dashboard";
		}
		
		if(error != null) {
			m.addAttribute("error", error);
			session.removeAttribute("error");
		}
		
		if(message != null) {
			m.addAttribute("message", message);
			session.removeAttribute("message");
		}
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginAction(Model m, HttpServletRequest req, UserVO userVO) {
		
		HttpSession session = req.getSession();
		
		Optional<User> user = userService.findById(userVO.getUserID());
		
		if(user.isPresent()) {
			if(user.get().getUserID().equals(userVO.getUserID()) && user.get().getUserPassword().equals(userVO.getUserPassword())) {
				System.out.println("로그인 성공 :::");
				
				session.setAttribute("user", user.get());
				
				return "redirect:/dashboard";
			} else {
				m.addAttribute("error", "아이디 혹은 비밀번호가 일치하지 않습니다.");
				
				return "login";
			}
		} else {
			m.addAttribute("error", "아이디 혹은 비밀번호가 일치하지 않습니다.");
			
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		User user = (User) req.getAttribute("user");
		
		if(user != null) {
			session.removeAttribute("user");
		}
		
		session.invalidate();
		
		return "redirect:/index";
	}
	
	@GetMapping("/register")
	public String regsiter(Model m, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user != null) {
			return "redirect:/dashboard";
		}
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerAction(Model m, UserVO userVO) {
		
		System.out.println("/registerAction :::");
		System.out.println(userVO.toString());
		
		if(userService.findById(userVO.getUserID()).isPresent()) {
			
			m.addAttribute("error", "이미 존재하는 아이디입니다.");
			
			return "register";
		}
		
		if(!userVO.getUserPassword().equals(userVO.getUserPassword2())) {
			
			m.addAttribute("error", "1차 비밀번호와 2차 비밀번호가 일치하지 않습니다.");
			
			return "register";
		}
		
		if(userService.findByUserNick(userVO.getUserNick()).isPresent()) {
			m.addAttribute("error", "이미 존재하는 닉네임입니다.");
			
			return "register";
		}
		
		User user = new User(userVO.getUserID(), userVO.getUserPassword(), userVO.getUserNick(), userVO.getUserEmail(), userVO.getUserBorn());
		
		userService.save(user);
		
		return "redirect:/login";
	}
	
	// =======================================================
	// 여기서 부터 회원 페이지 !!
	// =======================================================
	// =======================================================
	// 대시보드
	// =======================================================
	@GetMapping("/dashboard")
	public String dashboard(Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		List<GroupReg> registered_tmp = groupRegService.findRegisteredGroupByUserID(user.getUserID());
		List<GroupReg> waiting_tmp = groupRegService.findRegisterWaitingGroupByUserID(user.getUserID());
		
		List<StudyGroup> registered = new ArrayList<>();
		List<StudyGroup> waiting = new ArrayList<>();
		
		for (GroupReg reg : registered_tmp) {
			registered.add(studyGroupService.findById(reg.getGroupID()).get());
		}
		
		for (GroupReg reg : waiting_tmp) {
			waiting.add(studyGroupService.findById(reg.getGroupID()).get());
		}
		
		// 유저 세팅
		m.addAttribute("user", user);
		
		// 가입한 목록
		m.addAttribute("registered", registered);
		
		// 대기 중인 목록
		m.addAttribute("waiting", waiting);
		
		System.out.println(user.toString());
		System.out.println(registered.toString());
		System.out.println(waiting.toString());
		
		return "dashboard";
	}
	
	@GetMapping("/group/list")
	public String groupList(Model m, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		String error = (String) session.getAttribute("error");
		String message = (String) session.getAttribute("message");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		if(error != null) {
			m.addAttribute("error", error);
			session.removeAttribute("error");
		}
		
		if(message != null) {
			m.addAttribute("message", message);
			session.removeAttribute("message");
		}
		
		List<StudyGroup> groupList = studyGroupService.findAll();
		
		// 유저 세팅
		m.addAttribute("user", user);
		
		// 그룹 리스트 세팅
		m.addAttribute("groupList", groupList);
		
		return "group_list";
	}
	
	@GetMapping("/group/add")
	public String groupAdd(Model m, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		// 유저 세팅
		m.addAttribute("user", user);
		
		return "group_add";
	}
	
	@PostMapping("/group/add")
	public String groupAddAction(Model m, HttpServletRequest req, StudyGroup studyGroup) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		// 유저 세팅
		m.addAttribute("user", user);
				
		
		if(studyGroupService.findByStudyName(studyGroup.getStudyName()).isPresent()) {
			m.addAttribute("error", "이미 존재하는 스터디그룹 명입니다.");
			
			return "group_add";
		}
		
		System.out.println("스터디그룹 생성 :::");
		System.out.println(studyGroup.toString());
		
		StudyGroup tmp = studyGroupService.save(studyGroup);
		
		if(tmp != null) {
			groupRegService.applyUser(tmp.getGroupMaster(), tmp.getGroupID());
			groupRegService.acceptUser(tmp.getGroupMaster(), tmp.getGroupID());
		}
		
		session.setAttribute("message", "스터디 그룹 생성이 완료되었습니다.");
		
		return "redirect:/group/view?id=" + tmp.getGroupID();
	}
	
	@GetMapping("/group/view")
	public String groupView(Model m, HttpServletRequest req, @RequestParam(value="id") long groupID) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		String error = (String) session.getAttribute("error");
		String message = (String) session.getAttribute("message");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		if(error != null) {
			m.addAttribute("error", error);
			session.removeAttribute("error");
		}
		
		if(message != null) {
			m.addAttribute("message", message);
			session.removeAttribute("message");
		}
		
		String regState = groupRegService.getRegState(user.getUserID(), groupID);
		
		Optional<StudyGroup> studyGroup = studyGroupService.findById(groupID);
		
		// 유저 세팅
		m.addAttribute("user", user);
		
		// 유저 가입여부 세팅
		m.addAttribute("regState", regState);
		
		// 그룹 정보 세팅
		m.addAttribute("studyGroup", studyGroup.get());
		
		return "group_view";
	}
	
	@PostMapping("/group/register")
	public String groupRegister(Model m, HttpServletRequest req, @RequestParam(value="userID") String userID, @RequestParam(value="groupID") long groupID) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		
		groupRegService.applyUser(userID, groupID);
		
		session.setAttribute("message", "스터디 그룹 가입 신청이 완료 되었습니다. 그룹장이 승인하면 최종 가입됩니다.");
		
		return "redirect:/group/view?id=" + groupID;
	}
	
	@PostMapping("/group/unregister")
	public String groupUnregister(Model m, HttpServletRequest req, @RequestParam(value="userID") String userID, @RequestParam(value="groupID") long groupID) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		groupRegService.denyUser(userID, groupID);
		studyGroupService.decreaseGroupMemCount(groupID);
		
		session.setAttribute("message", "스터디 그룹에서 탈퇴되었습니다.");
		
		return "redirect:/group/view?id=" + groupID;
	}
	
	@PostMapping("/group/cancel")
	public String groupCancel(Model m, HttpServletRequest req, @RequestParam(value="userID") String userID, @RequestParam(value="groupID") long groupID) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		groupRegService.denyUser(userID, groupID);
		
		session.setAttribute("message", "스터디 그룹 가입 신청이 취소되었습니다.");
		
		return "redirect:/group/view?id=" + groupID;
	}
	
	
	@GetMapping("/group/accept")
	public String groupAccept(Model m, HttpServletRequest req, @RequestParam(value="id") long groupID) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		String error = (String) session.getAttribute("error");
		String message = (String) session.getAttribute("message");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		if(error != null) {
			m.addAttribute("error", error);
			session.removeAttribute("error");
		}
		
		if(message != null) {
			m.addAttribute("message", message);
			session.removeAttribute("message");
		}
		
		String regState = groupRegService.getRegState(user.getUserID(), groupID);
		
		Optional<StudyGroup> studyGroup = studyGroupService.findById(groupID);
		
		
		List<GroupReg> groupRegList = groupRegService.findRegisterWaitingGroupByGroupID(groupID);
		
		// 유저 세팅
		m.addAttribute("user", user);
		
		// 유저 가입여부 세팅
		m.addAttribute("regState", regState);
		
		// 그룹 정보 세팅
		m.addAttribute("studyGroup", studyGroup.get());
		
		
		// 그룹 가입 대기 리스트 출력
		m.addAttribute("groupRegList", groupRegList);
		
		return "group_accept";
	}
	
	@PostMapping("/group/accept")
	public String groupAcceptAction(Model m, HttpServletRequest req, @RequestParam(value="userID") String userID, @RequestParam(value="groupID") long groupID) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		groupRegService.acceptUser(userID, groupID);
		studyGroupService.increaseGroupMemCount(groupID);
		
		session.setAttribute("message", "해당 회원 ( " + userID +" )의 스터디 그룹 가입이 승인되었습니다.");
		
		return "redirect:/group/accept?id=" + groupID;
	}
	
	@PostMapping("/group/deny")
	public String groupDenyAction(Model m, HttpServletRequest req, @RequestParam(value="userID") String userID, @RequestParam(value="groupID") long groupID) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		groupRegService.denyUser(userID, groupID);
		
		session.setAttribute("error", "해당 회원 ( " + userID +" )의 스터디 그룹 가입이 거절되었습니다.");
		
		return "redirect:/group/accept?id=" + groupID;
	}
	
	@GetMapping("/group/modify")
	public String groupModify(Model m, HttpServletRequest req, @RequestParam(value="id") long groupID) {
		
		System.out.println("그룹 수정 :::");
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		String regState = groupRegService.getRegState(user.getUserID(), groupID);
		
		Optional<StudyGroup> studyGroup = studyGroupService.findById(groupID);
		
		// 유저 세팅
		m.addAttribute("user", user);
		
		// 유저 가입여부 세팅
		m.addAttribute("regState", regState);
		
		// 그룹 정보 세팅
		m.addAttribute("studyGroup", studyGroup.get());
		
		return "group_modify";
	}
	
	@PostMapping("/group/modify")
	public String groupModifyAction(Model m, HttpServletRequest req, StudyGroup studyGroup) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		studyGroupService.save(studyGroup);
		
		return "redirect:/group/view?id=" + studyGroup.getGroupID();
	}

	@PostMapping("/group/delete")
	public String groupModifyAction(Model m, HttpServletRequest req, @RequestParam(value="groupID") long id) {
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		session.setAttribute("error", "그룹이 폐쇄되었습니다.");
		
		studyGroupService.deleteById(id);
		
		return "redirect:/group/list";
	}
	
	@GetMapping("/user/settings")
	public String userSettings(Model m, HttpServletRequest req) {
		
		System.out.println("유저 수정 :::");
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		String error = (String) session.getAttribute("error");
		String message = (String) session.getAttribute("message");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		if(error != null) {
			m.addAttribute("error", error);
			session.removeAttribute("error");
		}
		
		if(message != null) {
			m.addAttribute("message", message);
			session.removeAttribute("message");
		}
		
		// 유저 세팅
		m.addAttribute("user", user);
		
		return "user_settings";
	}
	
	@PostMapping("/user/settings")
	public String userSettingsAction(Model m, HttpServletRequest req, UserVO userVO) {
		
		System.out.println("유저 수정 :::");
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			
			return "redirect:/login";
		}
		
		System.out.println("/registerAction :::");
		System.out.println(userVO.toString());
		
		if(!userVO.getUserPassword().equals(userVO.getUserPassword2())) {
			
			session.setAttribute("error", "1차 비밀번호와 2차 비밀번호가 일치하지 않습니다.");
			
			return "redirect:/user/settings";
		}
		
		if((!user.getUserNick().equals(userVO.getUserNick())) && userService.findByUserNick(userVO.getUserNick()).isPresent()) {
			session.setAttribute("error", "이미 존재하는 닉네임입니다.");
			
			return "redirect:/user/settings";
		}
		
		User tmp = new User(userVO.getUserID(), userVO.getUserPassword(), userVO.getUserNick(), userVO.getUserEmail(), userVO.getUserBorn());
		
		userService.save(tmp);
		
		session.setAttribute("user", tmp);
		session.setAttribute("message", "회원 정보가 수정되었습니다.");
		
		return "redirect:/user/settings";
	}
}
