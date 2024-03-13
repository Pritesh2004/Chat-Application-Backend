package com.chatApp.group.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequestDto {

	private String groupAdminUsername;

	private String username;
	
	private String groupName;

}
