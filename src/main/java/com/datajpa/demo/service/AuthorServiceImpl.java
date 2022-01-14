package com.datajpa.demo.service;

import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.Book;
import com.datajpa.demo.model.ZipCode;
import com.datajpa.demo.model.dto.AuthorDto;
import com.datajpa.demo.model.exception.*;
import com.datajpa.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final ZipcodeService zipcodeService;


    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;

    }
    @Transactional
    @Override
    public Author addAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        if (authorDto.getZipCodeId() == null) {
            throw new AuthorNeedAnZipcodeException();
        }
        ZipCode zipCode = zipcodeService.getZipCode(authorDto.getZipCodeId());
        author.setZipCode(zipCode);
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthors() {
        return StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));
    }

    @Override
    public Author deleteAuthor(Long id) {
        Author author = getAuthor(id);
        authorRepository.delete(author);
        return author;
    }

    @Transactional
    @Override
    public Author editAuthor(Long id, AuthorDto authorDto) {
        Author authorToEdit = getAuthor(id);
        authorToEdit.setName(authorDto.getName());
        if (authorDto.getZipCodeId() != null) {
            ZipCode zipCode = zipcodeService.getZipCode(authorDto.getZipCodeId());
            authorToEdit.setZipCode(zipCode);
        }

        return authorToEdit;
    }

    @Transactional
    @Override
    public Author addZipCodeToAuthor(Long authorId, Long zipCodeId) {
        Author author = getAuthor(authorId);
        ZipCode zipCode = zipcodeService.getZipCode(zipCodeId);
        if(Objects.nonNull(author.getZipCode())){
            throw new ZipCodeIsAlreadyAssignedToAuthorException(authorId,
                    author.getZipCode().getId());
        }
        author.setZipCode(zipCode);
        return author;
    }

    @Transactional
    @Override
    public Author removeZipCodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipCode(null);
        return author;
    }


}
