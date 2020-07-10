package com.kh.baseball.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kh.baseball.member.model.service.MemberService;
import com.kh.baseball.member.model.vo.Member;
import com.kh.common.frontcontroller.Controller;
import com.kh.common.frontcontroller.ModelAndView;

public class MemberController implements Controller {

	public MemberService ms = new MemberService();

	public ModelAndView join(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		mav.setView("member/memberJoin");
		return mav;
	}

	public ModelAndView kakaoLoginPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		mav.setView("member/kakaoLogin");
		return mav;
	}

	public ModelAndView kakaoJoinPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("id");
		System.out.println("카카오 조인페이지로 넘어온 아이디체크 : " + id);

		request.setAttribute("id", id);
		mav.setView("member/kakaoJoin");
		System.out.println("여까진 가서 에러인건가?");
		return mav;
	}

	public ModelAndView findId(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/findId");
		return mav;
	}

	public ModelAndView deleteMember(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/deleteMember");
		return mav;
	}

	public ModelAndView findPwd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/findPwd");
		return mav;
	}

	public ModelAndView memberJoin(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		Member m = new Member();
		m.setM_id(request.getParameter("userId"));
		m.setM_password(request.getParameter("userPwd"));
		m.setM_tell(request.getParameter("m_tell"));
		m.setM_email(request.getParameter("m_email"));
		m.setTeamname(request.getParameter("my_team"));

		int res = ms.memberJoin(m);
		if (res >= 1) {
			mav.addObject("alertMsg", "회원가입을 환영합니다");
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("common/result");
		} else {
			mav.setView("member/memberJoin");
			mav.addObject("isSuccess", "false");
		}

		return mav;
	}

	public ModelAndView idCheck(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String res = ms.idCheck(request.getParameter("userId"));
		mav.setView("ajax");

		mav.addObject("userId", res);

		return mav;
	}

	public ModelAndView kakaoidCheck(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		System.out.println("카카오 아이디 체크 : " + id);
		String res = ms.idCheck(id);
		System.out.println("뭐 담겨왔냐?" + res);
		mav.setView("ajax");
		mav.addObject("id", res);

		return mav;
	}

	public ModelAndView kakaoJoin(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("userId");
		System.out.println("카카오 조인으로 넘어온 아이디체크 : " + id);

		Member m = new Member();
		m.setM_id(request.getParameter("userId"));
		m.setM_tell(request.getParameter("m_tell"));
		m.setM_email(request.getParameter("m_email"));
		m.setTeamname(request.getParameter("my_team"));

		int res = ms.memberJoin(m);
		if (res >= 1) {
			mav.addObject("alertMsg", "회원가입을 환영합니다");
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("common/result");

		} else {
			mav.setView("member/kakaoJoin");
			mav.addObject("isSuccess", "false");
		}

		return mav;
	}

	public ModelAndView logIn(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.setView("member/login");
		return mav;
	}

	public ModelAndView memberLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		Member res = ms.memberLogin(id, pwd);

		if (res != null) {
			if (res.getM_leaveyn().equals("y")) {
				mav.addObject("alertMsg", "이미 탈퇴한 회원입니다.");
				mav.addObject("url", "/semi/member/login.do");
				mav.setView("common/result");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginInfo", res);
				mav.setView("index/index");
			}

		} else {
			mav.addObject("alertMsg", "아이디나 비밀번호를 확인해주세요.");
			mav.addObject("url", "/semi/member/login.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView kakaoLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("id");
		System.out.println("카카오 로그인 메서드에 온 아이디 : " + id);

		Member res = ms.kakaoLogin(id);
		System.out.println("마지막 컨트롤러");

		if (res != null) {
			if (res.getM_leaveyn().equals("y")) {
				mav.addObject("alertMsg", "이미 탈퇴한 회원입니다.");
				mav.addObject("url", "/semi/member/login.do");
				mav.setView("common/result");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginInfo", res);
				mav.setView("index/index");
			}

		} else {
			mav.addObject("alertMsg", "카카오 로그인에 실패하였습니다. 다시시도하시거나 관리자에게 문의하세요.");
			mav.addObject("url", "/semi/member/kakaologin.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView myPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Member member = null;
		HttpSession session = request.getSession();
		member = (Member) session.getAttribute("loginInfo");

		mav.addObject("loginInfomation", member);
		mav.setView("member/mypage");

		return mav;
	}

	public ModelAndView memberLogout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		request.getSession().invalidate();
		mav.setView("index/index");

		return mav;
	}

	public ModelAndView modifyPw(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginInfo");

		String id = member.getM_id();
		String newpwd = request.getParameter("newPwd");

		int res = ms.modifyPw(id, newpwd);
		if (res >= 1) {
			mav.addObject("alertMsg", "비밀번호가 정상적으로 수정되었습니다!");
			mav.addObject("url", "/semi/member/mypage.do");
			mav.setView("common/result");
		} else {
			mav.addObject("alertMsg", "비밀번호 수정을 다시한번 시도해주세요.");
			mav.addObject("url", "/semi/member/mypage.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView modifyTeam(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginInfo");

		String id = member.getM_id();
		String newteam = request.getParameter("newTeam");

		int res = ms.modifyTeam(id, newteam);
		if (res >= 1) {
			mav.addObject("alertMsg", "팀 변경이 정상적으로 완료되었습니다.");
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("common/result");
		} else {
			mav.addObject("alertMsg", "팀 변경을 다시한번 시도해주세요.");
			mav.addObject("url", "/semi/member/mypage.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView findidImple(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String email = request.getParameter("email");
		String tell = request.getParameter("tell");

		System.out.println("이메일이랑 전화번호 잘 담겨왔냐 " + email + "///" + tell);

		ms.findidImple(email, tell);
		System.out.println("컨트롤러단 입니다 :" + email);
		mav.addObject("alertMsg", "등록하신 이메일로 ID가 발송되었습니다.");
		mav.addObject("url", "/semi/index/index.do");
		mav.setView("common/result");

		return mav;
	}

	public ModelAndView findPwdImple(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("rememberid");
		String email = request.getParameter("rememberemail");
		System.out.println("이메일이랑 아이디 " + email + "///" + id);

		ms.findPwdImple(id, email);
		System.out.println("최종으로 넘어온 컨트롤러입니다." + email);
		mav.addObject("alertMsg", "등록하신 이메일로 임시비밀번호가 발급되었습니다.");
		mav.addObject("url", "/semi/index/index.do");
		mav.setView("common/result");

		return mav;
	}

	public ModelAndView deleteMemberImple(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String deleteid = request.getParameter("deleteid");
		String deletepwd = request.getParameter("deletepwd");

		int res = ms.deleteMemberImple(deleteid, deletepwd);
		System.out.println(res);
		if (res >= 1) {
			System.out.println("최종컨트롤으로 왔어요");
			request.getSession().invalidate();
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("member/deleteResult");
		} else {
			mav.addObject("alertMsg", "아이디와 비밀번호를 확인해주세요.");
			mav.addObject("url", "/semi/member/deletemember.do");
			mav.setView("common/result");
		}

		return mav;
	}

}
