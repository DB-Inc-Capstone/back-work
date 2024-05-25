package kr.co.dbinc.back_work.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkerVO {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String phoneNumber;
	private String email;
}