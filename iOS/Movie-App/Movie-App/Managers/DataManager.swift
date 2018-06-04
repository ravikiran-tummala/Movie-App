//
//  DataManager.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 25/04/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import Foundation

class DataManager {
    class func getTopRatedMovieList(forPage page:Int, completion: @escaping (_ response: MovieListModel)->Void, failure: @escaping (_ error:Error) ->Void){
        // Always chedk DB else check if you are online and then try
        
    }
    
    class func getPopularMovieList(forPage page:Int, completion: @escaping (_ response: MovieListModel)->Void, failure: @escaping (_ error:Error) ->Void){
        // Always chedk DB else check if you are online and then try
    }
}
