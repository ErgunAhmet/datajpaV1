package com.datajpa.demo.service;

import com.datajpa.demo.mapper.mapper;
import com.datajpa.demo.model.Author;
import com.datajpa.demo.model.ZipCode;
import com.datajpa.demo.model.dto.request.AuthorDto;
import com.datajpa.demo.model.dto.response.AuthorResponseDto;
import com.datajpa.demo.model.exception.*;
import com.datajpa.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public AuthorResponseDto addAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        if (authorDto.getZipCodeId() == null) {
            throw new AuthorNeedAnZipcodeException();
        }
        ZipCode zipCode = zipcodeService.getZipCode(authorDto.getZipCodeId());
        author.setZipCode(zipCode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        return mapper.authorToAuthorResponseDto(getAuthor(id));
    }

    @Override
    public Author getAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));
        return author;
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long id) {
        Author author = getAuthor(id);
        authorRepository.delete(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long id, AuthorDto authorDto) {
        Author authorToEdit = getAuthor(id);
        authorToEdit.setName(authorDto.getName());
        if (authorDto.getZipCodeId() != null) {
            ZipCode zipCode = zipcodeService.getZipCode(authorDto.getZipCodeId());
            authorToEdit.setZipCode(zipCode);
        }

        return mapper.authorToAuthorResponseDto(authorToEdit);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipCodeToAuthor(Long authorId, Long zipCodeId) {
        Author author = getAuthor(authorId);
        ZipCode zipCode = zipcodeService.getZipCode(zipCodeId);
        if(Objects.nonNull(author.getZipCode())){
            throw new ZipCodeIsAlreadyAssignedToAuthorException(authorId,
                    author.getZipCode().getId());
        }
        author.setZipCode(zipCode);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto removeZipCodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipCode(null);
        return mapper.authorToAuthorResponseDto(author);
    }


}
