package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.ZipCode;
import com.datajpa.demo.model.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public Author addAuthor(AuthorDto authorDto);
    public List<Author> getAuthors();
    public Author getAuthor(Long id);
    public Author deleteAuthor(Long id);
    public Author editAuthor(Long id, AuthorDto authorDto);
    public Author addZipCodeToAuthor(Long authorId, Long zipCodeId);
    public Author removeZipCodeFromAuthor(Long authorId);

}
