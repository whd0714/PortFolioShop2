package portfolioshop.page;

import lombok.Data;

@Data
public class PageList {
    /** 한 페이지당 게시글 수 **/
    private int pageSize;

    /** 한 블럭(range)당 페이지 수 **/
    private int rangeSize;

    /** 현재 페이지 **/
    private int curPage;

    /** 현재 블럭(range) **/
    private int curRange;

    /** 총 게시글 수 **/
    private int listCnt;

    /** 총 페이지 수 **/
    private int pageCnt;

    /** 총 블럭(range) 수 **/
    private int rangeCnt;

    /** 시작 페이지 **/
    private int startPage;

    /** 끝 페이지 **/
    private int endPage;

    /** 시작 index **/
    private int startIndex;

    /** 이전 페이지 **/
    private int prevPage;

    /** 다음 페이지 **/
    private int nextPage;
}
