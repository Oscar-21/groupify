package com.conder.groupify.dto.resultspage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultsPage {
    private String status;
    private Integer perPage;
    private Integer page;
    private Integer totalEntries;
}
