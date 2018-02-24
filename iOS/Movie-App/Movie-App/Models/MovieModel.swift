//
//  MovieModel.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 08/02/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import Foundation
class MovieModel {
    init(fromJSONResponse dictionary: NSDictionary) {
        self.posterPath = (dictionary[POSTER_PATH_KEY] as? String)!
        self.isAdultRated = (dictionary[ADULT_KEY] as? Bool)!
        self.overView = (dictionary[OVERVIEW_KEY] as? String)!
        self.releaseDate = (dictionary[RELEASE_DATE_KEY] as? String)!
        self.id = (dictionary[ID_KEY] as? NSNumber)!
        self.originalTitle = (dictionary[ORIGINAL_TITLE_KEY] as? String)!
        self.originalLanguage = (dictionary[ORIGINAL_LANGUAGE_KEY] as? String)!
        self.title = (dictionary[TITLE_KEY] as? String)!
        self.backDropPath = (dictionary[BACKDROP_PATH_KEY] as? String)!
        self.popularity = (dictionary[POPULARITY_KEY] as? NSNumber)!
        self.voteCount = (dictionary[VOTE_COUNT_KEY] as? NSNumber)!
        self.isVideo = (dictionary[VIDEO_KEY] as? Bool)!
        self.voteAverage = (dictionary[VOTE_AVERAGE_KEY] as? NSNumber)!
        self.genreIds = (dictionary[GENRE_IDS_KEY] as? Array)!
    }
    private(set) var posterPath:String
    private(set) var isAdultRated:Bool
    private(set) var overView:String
    private(set) var releaseDate:String
    private(set) var genreIds:[NSNumber]
    private(set) var id:NSNumber
    private(set) var originalTitle:String
    private(set) var originalLanguage:String
    private(set) var title:String
    private(set) var backDropPath:String
    private(set) var popularity:NSNumber
    private(set) var voteCount:NSNumber
    private(set) var isVideo:Bool
    private(set) var voteAverage:NSNumber
}
