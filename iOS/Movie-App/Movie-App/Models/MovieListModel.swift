//
//  MovieListModel.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 09/02/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import Foundation

class MovieListModel {
    init(fromJSONResponse dictionary:NSDictionary) {
        self.page = (dictionary[PAGE_KEY] as? NSNumber)!
        self.totalPages = (dictionary[TOTAL_PAGES_KEY] as? NSNumber)!
        self.totalResults = (dictionary[TOTAL_RESULTS_KEY] as? NSNumber)!
        let movieResultsArray:[NSDictionary] = (dictionary[RESULTS_KEY] as? Array)!
        self.movieModels = [MovieModel]()
        for movieModelDictionary:NSDictionary in movieResultsArray {
            self.movieModels.append(MovieModel(fromJSONResponse: movieModelDictionary))
        }
    }
    private(set) var page:NSNumber
    private(set) var totalPages:NSNumber
    private(set) var totalResults:NSNumber
    private(set) var movieModels:[MovieModel]
}
