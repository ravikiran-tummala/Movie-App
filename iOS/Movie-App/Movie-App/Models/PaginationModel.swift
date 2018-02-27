//
//  PaginationModel.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 27/02/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import Foundation

class PaginationModel {
    init(fromJSONResponse dictionary:NSDictionary) {
        self.totalPages = (dictionary[TOTAL_PAGES_KEY] as? NSNumber)!
        self.totalResults = (dictionary[TOTAL_RESULTS_KEY] as? NSNumber)!
    }
    private(set) var totalPages:NSNumber
    private(set) var totalResults:NSNumber
}
