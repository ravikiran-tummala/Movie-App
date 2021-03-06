//
//  ServiceFactory.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 08/02/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import Foundation
import Alamofire


class ServiceFactory {
    static let urlScheme = "https"
    static let baseURL = "api.themoviedb.org"
    static let popularPath = "/3/movie/popular"
    static let topRatedPath = "/3/movie/top_rated"
    static let langauageValue = "en-US"
    static let apiKeyTag = "api_key"
    static let languageTag = "language"
    static let pageTag = "page"
    
    class func getTotalPagesAndResults(completion: @escaping (_ response: PaginationModel)->Void, failure: @escaping (_ error:Error) ->Void){
        let urlComponents = NSURLComponents()
        urlComponents.host = baseURL
        urlComponents.scheme = urlScheme
        urlComponents.path = popularPath
        
        let apiKeyQuery = URLQueryItem(name: apiKeyTag, value: API_KEY)
        let languageQuery = URLQueryItem(name: languageTag, value: langauageValue)
        let pageQuery = URLQueryItem(name: pageTag, value: String(1))
        
        urlComponents.queryItems = [apiKeyQuery,languageQuery,pageQuery]
        
        let url = urlComponents.url
        Alamofire.request(url!).responseJSON { response in
            if let json = response.result.value{
                print("JSON: \(json)")
                let paginationModel = PaginationModel(fromJSONResponse: (json as? NSDictionary)!)
                completion(paginationModel)
            }else{
                failure(response.result.error!)
            }
        }
    }
    
    
    class func getPopularMovieList(forPage page:Int, completion: @escaping (_ response: MovieListModel)->Void, failure: @escaping (_ error:Error) ->Void){
        let urlComponents = NSURLComponents()
        urlComponents.host = baseURL
        urlComponents.scheme = urlScheme
        urlComponents.path = popularPath
        
        let apiKeyQuery = URLQueryItem(name: apiKeyTag, value: API_KEY)
        let languageQuery = URLQueryItem(name: languageTag, value: langauageValue)
        let pageQuery = URLQueryItem(name: pageTag, value: String(page))

        urlComponents.queryItems = [apiKeyQuery,languageQuery,pageQuery]
        
        let url = urlComponents.url
        Alamofire.request(url!).responseJSON { response in
            if let json = response.result.value{
                print("JSON: \(json)")
                let movieListModel = MovieListModel(fromJSONResponse: (json as? NSDictionary)!)
                completion(movieListModel)
            }else{
                failure(response.result.error!)
            }
        }
    }
    
    class func getTopRatedMovieList(forPage page:Int, completion: @escaping (_ response: MovieListModel)->Void, failure: @escaping (_ error:Error) ->Void){
        let urlComponents = NSURLComponents()
        urlComponents.host = baseURL
        urlComponents.scheme = urlScheme
        urlComponents.path = topRatedPath
        
        let apiKeyQuery = URLQueryItem(name: apiKeyTag, value: API_KEY)
        let languageQuery = URLQueryItem(name: languageTag, value: langauageValue)
        let pageQuery = URLQueryItem(name: pageTag, value: String(page))
        
        urlComponents.queryItems = [apiKeyQuery,languageQuery,pageQuery]
        
        let url = urlComponents.url
        Alamofire.request(url!).responseJSON { response in
            if let json = response.result.value{
                print("JSON: \(json)")
                let movieListModel = MovieListModel(fromJSONResponse: (json as? NSDictionary)!)
                completion(movieListModel)
            }else{
                failure(response.result.error!)
            }
        }
    }
}
