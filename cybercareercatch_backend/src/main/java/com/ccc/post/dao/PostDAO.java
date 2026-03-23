package com.ccc.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.post.dto.CommentDTO;
import com.ccc.post.dto.CommentViewDTO;
import com.ccc.post.dto.CommentWriteDTO;
import com.ccc.post.dto.NoticeDTO;
import com.ccc.post.dto.PostDTO;
import com.ccc.post.dto.PostDetailDTO;
import com.ccc.post.dto.PostListDTO;
import com.ccc.post.dto.PostWriteDTO;

public class PostDAO {

    // MyBatis와 연결되는 SqlSession 객체
    // SQL 실행(select, insert, update, delete)을 담당
    private SqlSession sqlSession;

    // 생성자
    // DAO 객체가 생성될 때 sqlSession을 자동으로 열어둠
    // openSession(true) : 자동 커밋 설정
    public PostDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    // =========================================================
    // 자유게시판 공지
    // =========================================================

    // 자유게시판 공지 목록 조회
    // mapper id : post.selectFreeNoticeList
    public List<NoticeDTO> selectFreeNoticeList() {
        System.out.println("자유게시판 공지 목록 조회 - selectFreeNoticeList 실행");
        List<NoticeDTO> noticeList = sqlSession.selectList("post.selectFreeNoticeList");
        System.out.println("조회 결과 : " + noticeList);
        return noticeList;
    }

    // 자유게시판 공지 상세 조회
    // parameter : postNumber
    // mapper id : post.selectFreeNoticeDetail
    public NoticeDTO selectFreeNoticeDetail(int postNumber) {
        System.out.println("자유게시판 공지 상세 조회 - selectFreeNoticeDetail 실행 : " + postNumber);
        NoticeDTO noticeDTO = sqlSession.selectOne("post.selectFreeNoticeDetail", postNumber);
        System.out.println("조회 결과 : " + noticeDTO);
        return noticeDTO;
    }

    // 자유게시판 공지 추가
    // mapper id : post.insertNotice
    public void insertNotice(NoticeDTO noticeDTO) {
        System.out.println("자유게시판 공지 작성 - insertNotice 실행");
        int result = sqlSession.insert("post.insertNotice", noticeDTO);
        System.out.println("공지 작성 결과 : " + result);
    }

    // 자유게시판 공지 삭제
    // parameter : postNumber
    // mapper id : post.deleteNotice
    public void deleteNotice(int postNumber) {
        System.out.println("자유게시판 공지 삭제 - deleteNotice 실행 : " + postNumber);
        int result = sqlSession.delete("post.deleteNotice", postNumber);
        System.out.println("공지 삭제 결과 : " + result);
    }

    // =========================================================
    // 자유게시판 일반 게시글
    // =========================================================

 // 자유게시판 일반 게시글 총 개수 조회
    public int getTotal(Map<String, Object> pageMap) {
        System.out.println("자유게시판 게시글 총 개수 조회 - getTotal 실행");
        return sqlSession.selectOne("post.getTotal", pageMap);
    }

    // 자유게시판 게시글 조회수 증가
    // 게시글 상세페이지 들어가기 전에 보통 실행
    // mapper id : post.updateReadCount
    public void updateReadCount(int postNumber) {
        System.out.println("조회수 증가 - updateReadCount 실행 : " + postNumber);
        int result = sqlSession.update("post.updateReadCount", postNumber);
        System.out.println("조회수 증가 결과 : " + result);
    }

    // 자유게시판 게시글 작성
    // mapper id : post.insert
    public void insert(PostWriteDTO postWriteDTO) {
        System.out.println("자유게시판 게시글 작성 - insert 실행");
        int result = sqlSession.insert("post.insert", postWriteDTO);
        System.out.println("게시글 작성 결과 : " + result);
    }

    // 자유게시판 게시글 삭제
    // postNumber + userNumber 둘 다 받아서
    // '내가 쓴 글만 삭제' 조건으로 처리
    // mapper id : post.delete
    public void delete(PostDTO postDTO) {
        System.out.println("자유게시판 게시글 삭제 - delete 실행");
        int result = sqlSession.delete("post.delete", postDTO);
        System.out.println("게시글 삭제 결과 : " + result);
    }

    // 자유게시판 게시글 상세 조회
    // mapper id : post.select
    public PostDetailDTO select(int postNumber) {
        System.out.println("자유게시판 게시글 상세 조회 - select 실행 : " + postNumber);
        PostDetailDTO postDetailDTO = sqlSession.selectOne("post.select", postNumber);
        System.out.println("조회 결과 : " + postDetailDTO);
        return postDetailDTO;
    }

 // 자유게시판 일반 게시글 목록 조회
    public List<PostListDTO> selectAll(Map<String, Object> pageMap) {
        System.out.println("자유게시판 전체 게시글 조회 - selectAll 실행 : " + pageMap);
        List<PostListDTO> postList = sqlSession.selectList("post.selectAll", pageMap);
        System.out.println("조회 결과 : " + postList);
        return postList;
    }


    // =========================================================
    // 댓글
    // =========================================================

    // 댓글 목록 조회
    // 특정 게시글 번호(postNumber)에 달린 댓글 전체 조회
    // mapper id : post.selectCommentList
    public List<CommentViewDTO> selectCommentList(int postNumber) {
        System.out.println("댓글 목록 조회 - selectCommentList 실행 : " + postNumber);
        List<CommentViewDTO> commentList = sqlSession.selectList("post.selectCommentList", postNumber);
        System.out.println("조회 결과 : " + commentList);
        return commentList;
    }

    // 댓글 작성
    // mapper id : post.insertComment
    public void insertComment(CommentWriteDTO commentWriteDTO) {
        System.out.println("댓글 작성 - insertComment 실행");
        int result = sqlSession.insert("post.insertComment", commentWriteDTO);
        System.out.println("댓글 작성 결과 : " + result);
    }

    // 댓글 삭제
    // commentNumber + userNumber 조건으로
    // 내가 작성한 댓글만 삭제
    // mapper id : post.deleteComment
    public void deleteComment(CommentDTO commentDTO) {
        System.out.println("댓글 삭제 - deleteComment 실행");
        int result = sqlSession.delete("post.deleteComment", commentDTO);
        System.out.println("댓글 삭제 결과 : " + result);
        
  
    }
}