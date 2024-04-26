package ai.cubox.admin_ftest.admin.vo;

import java.time.LocalDateTime;

public class BoardVo {

	private Long boardId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	private String writer;
	private String deleteYn;

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public String getwriter() {
		return writer;
	}

	public void setwriter(String writer) {
		this.writer = writer;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	@Override
	public String toString() {
		return "BoardVo{" +
				"boardId=" + boardId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", createdAt=" + createdAt +
				", updateAt=" + updateAt +
				", writer='" + writer + '\'' +
				", deleteYn='" + deleteYn + '\'' +
				'}';
	}
}
