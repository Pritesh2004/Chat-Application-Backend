package com.chatApp.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<ChatGroup, Long>{

	public ChatGroup findByGroupName(String groupName);
}
