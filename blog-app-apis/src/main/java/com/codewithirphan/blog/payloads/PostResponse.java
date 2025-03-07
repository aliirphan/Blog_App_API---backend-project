package com.codewithirphan.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private  int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPages;



}
