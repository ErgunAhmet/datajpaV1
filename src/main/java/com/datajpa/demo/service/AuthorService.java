package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.dto.request.AuthorDto;
import com.datajpa.demo.model.dto.response.AuthorResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public AuthorResponseDto addAuthor(AuthorDto authorDto);
    public List<AuthorResponseDto> getAuthors();
    public AuthorResponseDto getAuthorById(Long id);
    public Author getAuthor(Long id);
    public AuthorResponseDto deleteAuthor(Long id);
    public AuthorResponseDto editAuthor(Long id, AuthorDto authorDto);
    public AuthorResponseDto addZipCodeToAuthor(Long authorId, Long zipCodeId);
    public AuthorResponseDto removeZipCodeFromAuthor(Long authorId);

}
