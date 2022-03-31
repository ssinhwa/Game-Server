package com.ssinhwa.gameserver.chatserver.service;

import com.ssinhwa.gameserver.chatserver.dto.ChatRoomDto;
import com.ssinhwa.gameserver.chatserver.entity.ChatRoom;
import com.ssinhwa.gameserver.chatserver.repository.ChatRoomRepository;
import com.ssinhwa.gameserver.chatserver.repository.ChatRoomRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomRepositorySupport chatRoomRepositorySupport;

    private final ModelMapper modelMapper;

    @Override
    public List<ChatRoomDto> findAll() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        List<ChatRoomDto> chatRoomDtos = chatRoomList.stream()
                .map(p -> modelMapper.map(p, ChatRoomDto.class))
                .collect(Collectors.toList());
        return chatRoomDtos;
    }

    @Override
    public ChatRoomDto findChatRoomById(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(id);
        return modelMapper.map(chatRoom, ChatRoomDto.class);
    }

    @Override
    public ChatRoomDto findChatRoomByRoomId(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId);
        return modelMapper.map(chatRoom, ChatRoomDto.class);
    }

    @Override
    public ChatRoomDto createChatRoom(String name) {
        ChatRoom chatRoom = chatRoomRepositorySupport.createChatRoom(name);
        chatRoomRepository.save(chatRoom);
        ChatRoomDto dto = modelMapper.map(chatRoom, ChatRoomDto.class);
        return dto;
    }
}
